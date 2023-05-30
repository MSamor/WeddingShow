package vip.maosi.weddingserver.response;

public class RGenerator {
    public static <T> ResEntity<T> resSuccess() {
        return new ResEntity<T>().setCode(DefinedCode.SUCCESS.getCode())
                .setMsg(DefinedCode.SUCCESS.getMsg());
    }

    public static <T> ResEntity<T> resSuccess(T data) {
        return new ResEntity<T>().setCode(DefinedCode.SUCCESS.getCode())
                .setMsg(DefinedCode.SUCCESS.getMsg())
                .setData(data);
    }

    public static <T> ResEntity<T> resSuccess(String message, T data) {
        return new ResEntity<T>().setCode(DefinedCode.SUCCESS.getCode())
                .setMsg(message)
                .setData(data);
    }

    public static <T> ResEntity<T> resFail() {
        return new ResEntity<T>().setCode(DefinedCode.ERROR.getCode())
                .setMsg(DefinedCode.ERROR.getMsg());
    }

    public static <T> ResEntity<T> resFail(String msg) {
        return new ResEntity<T>().setCode(DefinedCode.ERROR.getCode())
                .setMsg(msg);
    }

    public static <T> ResEntity<T> resCustom(int code, String msg) {
        return new ResEntity<T>().setCode(code).setMsg(msg);
    }

    public static <T> ResEntity<T> resCustom(int code, String msg, T data) {
        return new ResEntity<T>().setCode(code).setMsg(msg).setData(data);
    }
}
