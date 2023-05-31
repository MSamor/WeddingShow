package vip.maosi.weddingServer.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResEntity<T> {
    private int code;
    private String msg;
    private T data;
}
