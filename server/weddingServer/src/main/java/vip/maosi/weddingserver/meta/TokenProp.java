package vip.maosi.weddingserver.meta;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Getter
@Setter
@ConfigurationProperties(prefix = "token")
public class TokenProp {

    @NestedConfigurationProperty
    public Wechat wechat;

    @Getter
    @Setter
    public static class Wechat {

        @NestedConfigurationProperty
        public MiniProgram miniProgram;

        @Getter
        @Setter
        public static class MiniProgram {
            public String appId;
            public String appSecret;
        }
    }
}

