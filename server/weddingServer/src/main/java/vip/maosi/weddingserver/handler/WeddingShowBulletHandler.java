package vip.maosi.weddingserver.handler;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import vip.maosi.weddingserver.meta.SessionKeys;
import vip.maosi.weddingserver.response.DefinedCode;
import vip.maosi.weddingserver.response.RGenerator;
import vip.maosi.weddingserver.util.JsonUtils;
import vip.maosi.weddingserver.util.SessionManagerUtils;



@Component
public class WeddingShowBulletHandler implements WebSocketHandler {

    @Autowired
    SessionManagerUtils sessionManagerUtils;

    @Override
    public void afterConnectionEstablished(@NotNull WebSocketSession session) throws Exception {
        TextMessage replyTextMessage;
        replyTextMessage = new TextMessage(JsonUtils.toJson(RGenerator
                .resCustom(DefinedCode.SUCCESS.getCode(), "连接成功", null)));
        session.sendMessage(replyTextMessage);
        sessionManagerUtils.addSession(SessionKeys.WEDDING_SHOW.name(), session);
    }

    @Override
    public void handleMessage(@NotNull WebSocketSession session, @NotNull WebSocketMessage<?> message) throws Exception {
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        sessionManagerUtils.removeSession(SessionKeys.WEDDING_SHOW.name(), session);
        session.close();
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
