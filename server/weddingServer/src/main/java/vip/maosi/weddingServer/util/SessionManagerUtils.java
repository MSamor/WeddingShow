package vip.maosi.weddingServer.util;

import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import vip.maosi.weddingServer.response.DefinedCode;
import vip.maosi.weddingServer.response.RGenerator;

import java.io.IOException;
import java.util.*;

@Component
public class SessionManagerUtils {
    private final HashMap<String, Set<WebSocketSession>> sessionMap = new HashMap<>();

    public Pair<Integer, String> addSession(String key, WebSocketSession session) {
        int maxSessions = 10000; // 防止OOM
        if (StringUtils.isEmpty(key)) return Pair.of(-1, "key不能为空");
        if (sessionMap.get(key) == null) {
            Set<WebSocketSession> sessions = Collections.synchronizedSet(new LimitedLinkedHashSet<>(maxSessions));
            sessions.add(session);
            sessionMap.put(key, sessions);
            return Pair.of(0, "保存成功");
        }
        val socketSessions = sessionMap.get(key);
        socketSessions.add(session);
        return Pair.of(0, "保存成功");
    }

    public Pair<Integer, String> removeSession(String key, WebSocketSession session) {
        if (StringUtils.isEmpty(key)) return Pair.of(-1, "key不能为空");
        var webSocketSessions = sessionMap.get(key);
        if (webSocketSessions == null) return Pair.of(-1, "sessions为空");
        webSocketSessions.remove(session);
        return Pair.of(0, "删除成功");
    }

    public Pair<Integer, String> sendMessageToAll(String key, String msg, String data, DefinedCode states) {
        var webSocketSessions = sessionMap.get(key);
        if (webSocketSessions == null) return Pair.of(-1, "sessions为空,发送失败");

        for (WebSocketSession session : webSocketSessions) {
            try {
                session.sendMessage(new TextMessage(JsonUtils.toJson(RGenerator.resCustom(states.getCode(), msg, data))));
            } catch (IOException e) {
                e.printStackTrace();
                return Pair.of(-1, "发送失败");
            }
        }
        return Pair.of(0, "全部发送完成");
    }

    public static class LimitedLinkedHashSet<E> extends LinkedHashSet<E> {
        private final int maxSize;

        public LimitedLinkedHashSet(int maxSize) {
            super();
            this.maxSize = maxSize;
        }

        @Override
        public boolean add(E e) {
            if (size() >= maxSize) {
                Iterator<E> iterator = iterator();
                if (iterator.hasNext()) {
                    iterator.next();
                    iterator.remove();
                }
            }
            return super.add(e);
        }
    }
}