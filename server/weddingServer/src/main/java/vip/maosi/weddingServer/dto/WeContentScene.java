package vip.maosi.weddingServer.dto;

public enum WeContentScene {
    /**
     * 资料
     */
    DETAIL(1),
    /**
     * 评论
     */
    COMMENT(2),
    /**
     * 论坛
     */
    FORUM(3),
    /**
     * 社交日志
     */
    LOG(4);

    public final int key;

    WeContentScene(int key) {
        this.key = key;
    }
}
