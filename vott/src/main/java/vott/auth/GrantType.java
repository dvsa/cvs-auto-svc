package vott.auth;

import java.util.HashMap;
import java.util.Map;

public enum GrantType {
    IMPLICIT {
        @Override
        protected Map<String, Object> formParams() {
            Map<String, Object> formParams = new HashMap<>();

            return formParams;
        }
    },
    CLIENT_CREDENTIALS {
        @Override
        protected Map<String, Object> formParams() {
            Map<String, Object> formParams = new HashMap<>();

            return formParams;
        }
    };

    protected abstract Map<String, Object> formParams();
}
