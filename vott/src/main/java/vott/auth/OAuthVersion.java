package vott.auth;

import java.time.Duration;

public enum OAuthVersion {
    V1 {
        @Override
        public Duration parseExpiry(Object o) {
            return Duration.ofSeconds(Long.parseLong((String) o));
        }

        @Override
        public String baseUri(String tenantId) {
            return String.format("https://login.microsoftonline.com/%s/oauth2/token", tenantId);
        }
    },
    V2 {
        @Override
        public Duration parseExpiry(Object o) {
            int asInt = (int) o;
            return Duration.ofSeconds(asInt);
        }

        @Override
        public String baseUri(String tenantId) {
            return String.format("https://login.microsoftonline.com/%s/oauth2/v2.0/token", tenantId);
        }
    };

    public abstract Duration parseExpiry(Object o);

    public abstract String baseUri(String tenant);
}
