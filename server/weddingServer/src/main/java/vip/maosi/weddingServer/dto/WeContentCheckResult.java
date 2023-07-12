package vip.maosi.weddingServer.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class WeContentCheckResult {
    public int code;
    private String label;
    public String message;

    public static final int CODE_ERROR = -1;
    public static final int CODE_LEGAL = 0;
    public static final int CODE_ILLEGAL = 1;
}
