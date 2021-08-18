package util;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class TokenGeneratorDVLA {

    private static Loader loader = new LocalLoaderImpl();

    public TokenGeneratorDVLA(){}

    public static String getDVLAToken() {

        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> jsonResponse  = null;
        try {
            jsonResponse = Unirest.post(loader.getAppTokenUrlDVLA())
                    .field("grant_type", "client_credentials")
                    .field("client_id", loader.getAppClientIdDVLA())
                    .field("scope", loader.getAppScopeDVLA())
                    .field("client_secret", loader.getAppClientSecretDVLA())
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        String token = jsonResponse.getBody().getObject().get("access_token").toString();

        return token;
    }
}
