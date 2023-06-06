package vip.maosi.weddingServer.meta;

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

        @NestedConfigurationProperty
        public PublicProgram publicProgram;

        @Getter
        @Setter
        public static class MiniProgram {
            public String appId;
            public String appSecret;
        }

        @Getter
        @Setter
        public static class PublicProgram {
            public String appId;
            public String appSecret;
        }
    }
}

