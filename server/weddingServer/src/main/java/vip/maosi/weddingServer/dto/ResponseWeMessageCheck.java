package vip.maosi.weddingServer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ResponseWeMessageCheck {
    private Integer errcode;
    private String errmsg;
    @JsonProperty("trace_id")
    private String traceId;
    private Result result;
    private List<Detail> detail;

    @Data
    public static class Result {
        private String suggest;
        private String label;
    }

    @Data
    public static class Detail {
        private String strategy;
        private Integer errcode;
        private String suggest;
        private Integer label;
        private Integer level;
        private Integer prob;
        private String keyword;
    }
}
