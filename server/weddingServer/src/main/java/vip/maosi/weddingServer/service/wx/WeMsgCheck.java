package vip.maosi.weddingServer.service.wx;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vip.maosi.weddingServer.dto.ParamWeMessageCheck;
import vip.maosi.weddingServer.dto.ResponseWeMessageCheck;
import vip.maosi.weddingServer.dto.WeContentScene;
import vip.maosi.weddingServer.http.HttpClient;
import vip.maosi.weddingServer.util.JsonUtils;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class WeMsgCheck {

    @Autowired
    WeAccessTokens weAccessTokens;


    private static final Map<String, String> msgLabels = new HashMap<>();

    static {
        msgLabels.put("100", "正常");
        msgLabels.put("10001", "广告");
        msgLabels.put("20001", "时政");
        msgLabels.put("20002", "色情");
        msgLabels.put("20003", "辱骂");
        msgLabels.put("20006", "违法犯罪");
        msgLabels.put("20008", "欺诈");
        msgLabels.put("20012", "低俗");
        msgLabels.put("20013", "版权");
        //msgLabels.put("21000", "其他");
    }

    public Pair<Integer, String> checkMessage(String openid, WeContentScene scene, String message) {
        var accessToken = weAccessTokens.getAccessToken();
        var param = new ParamWeMessageCheck()
                .setOpenid(openid)
                .setScene(scene.key)
                .setVersion(2)
                .setContent(message);
        Pair<Integer, String> pair = null;
        try {
            pair = messageCheck(accessToken, param);
        } catch (Exception e) {
            log.error("wechat.messageCheck, error: " + e.getMessage(), e);
        }
        return pair;
    }

    private synchronized Pair<Integer, String> messageCheck(String accessToken, ParamWeMessageCheck param) {
        final var url = "https://api.weixin.qq.com/wxa/msg_sec_check?access_token=" + accessToken;
        final var params = Map.of(
                "version", param.getVersion(),
                "openid", param.getOpenid(),
                "scene", param.getScene(),
                "content", param.getContent()
        );
        try {
            final String response = HttpClient.get().jsonPost(url, params);
            if (StringUtils.isNotEmpty(response)) {
                ResponseWeMessageCheck responseWeMessageCheck = JsonUtils.fromJson(response, ResponseWeMessageCheck.class);
                if (responseWeMessageCheck == null || responseWeMessageCheck.getErrcode() != 0) {
                    return Pair.of(-1, "内容检测出错");
                }

                var suggest = responseWeMessageCheck.getResult().getSuggest();
                if (Suggest.PASS.equals(suggest)) {
                    return Pair.of(0, "内容检查通过");
                }

                var label = responseWeMessageCheck.getResult().getLabel();
                var msgLabel = msgLabels.get(label);
                msgLabel = msgLabel != null ? msgLabel : "违规";
                var invalidMsg = String.format("内容不合规，涉及%s", msgLabel);
                return Pair.of(-1, invalidMsg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("系统异常", e);
        }
        return Pair.of(-1, "系统异常");
    }

    private interface Suggest {
        String RISKY = "risky";
        String PASS = "pass";
        String REVIEW = "review";
    }
}
