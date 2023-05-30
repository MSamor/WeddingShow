package vip.maosi.weddingserver.meta;


public enum WeddingShowBigScreenStates {
    CONNECT_SUCCESS(1),
    SHOT(2),
    CONNECT_FAILED(-1),
    TRANSMIT_FAILED(-2),
    CLOSE_CONNECT(0);

    private int code;

    public int getCode() {
        return code;
    }

    WeddingShowBigScreenStates(int code) {
        this.code = code;
    }
}
