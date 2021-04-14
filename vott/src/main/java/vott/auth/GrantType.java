package vott.auth;

import java.util.HashMap;
import java.util.Map;

public enum GrantType {
    IMPLICIT {
        @Override
        protected Map<String, Object> formParams() {
            Map<String, Object> formParams = new HashMap<>();

            formParams.put("grant_type", "password");
            formParams.put("userName", "cvs.automation1@dvsagov.onmicrosoft.com");
            formParams.put("password", "*7vU4EWuJPwues_nGKBMaaNYVC3434");
            formParams.put("resource", "11be8c44-22f5-43bf-b9bb-50467a26cfa7");
            formParams.put("client_id", "11be8c44-22f5-43bf-b9bb-50467a26cfa7");

            return formParams;
        }
    },
    CLIENT_CREDENTIALS {
        @Override
        protected Map<String, Object> formParams() {
            Map<String, Object> formParams = new HashMap<>();

            formParams.put("grant_type", "client_credentials");
            formParams.put("scope", "https://dev.vta.dvsatest-cloud.uk/.default");
            formParams.put("client_secret", "eU9QK2.WixG5Io-17-~Pv_RJ3cZkCm_kke");
            formParams.put("client_id", "8b897993-7cb9-48f2-a66f-5cb6092d2d80");

            return formParams;
        }
    };

    protected abstract Map<String, Object> formParams();
}
