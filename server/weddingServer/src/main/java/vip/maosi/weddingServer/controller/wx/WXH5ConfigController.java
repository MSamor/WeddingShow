package vip.maosi.weddingServer.controller.wx;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.maosi.weddingServer.meta.ShareConfigProp;
import vip.maosi.weddingServer.response.RGenerator;
import vip.maosi.weddingServer.response.ResEntity;
import vip.maosi.weddingServer.service.wx.WeJsApiTicket;
import vip.maosi.weddingServer.util.SignUtils;

import java.util.Map;

@RestController
@RequestMapping("/wxconfig")
@EnableConfigurationProperties(ShareConfigProp.class)
public class WXH5ConfigController {

    @Autowired
    SignUtils signUtils;
    @Autowired
    WeJsApiTicket weJsApiTicket;
    @Autowired
    ShareConfigProp shareConfigProp;

    @GetMapping("/getH5Config")
    public ResEntity<Map<String, String>> getH5Config(HttpServletRequest request, String url) {
        if (StringUtils.isEmpty(url)) return RGenerator.resCustom(-1, "完整url不能为空");
        String jsapi_ticket = weJsApiTicket.getTicket();
        Map<String, String> ret = signUtils.sign(jsapi_ticket, url);
        ret.put("link", shareConfigProp.getLink()); // 跳转链接
        ret.put("title", shareConfigProp.getTitle()); // 标题
        ret.put("desc", shareConfigProp.getDesc()); // 描述
        ret.put("imgUrl", shareConfigProp.getImageUrl()); // 图片地址
        return RGenerator.resSuccess(ret);
    }
}
