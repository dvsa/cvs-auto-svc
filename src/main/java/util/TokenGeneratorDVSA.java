package util;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class TokenGeneratorDVSA {

    private static Loader loader = new LocalLoaderImpl();

    public TokenGeneratorDVSA(){}

    public static String getDVSAToken() {

        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> jsonResponse  = null;
        try {
            jsonResponse = Unirest.post(loader.getAppTokenUrlDVSA())
                    .field("grant_type", "client_credentials")
                    .field("client_id", loader.getAppClientIdDVSA())
                    .field("scope", loader.getAppScopeDVSA())
                    .field("client_secret", loader.getAppClientSecretDVSA())
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        String token = jsonResponse.getBody().getObject().get("access_token").toString();

        return token;
    }
}
