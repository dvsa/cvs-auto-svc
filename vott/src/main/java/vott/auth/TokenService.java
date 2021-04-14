package vott.auth;

import io.restassured.path.json.JsonPath;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

import static io.restassured.RestAssured.given;

@RequiredArgsConstructor
public class TokenService {

    private static final String TENANT_ID = "6c448d90-4ca1-4caf-ab59-0a2aa67d7801";

    private final OAuthVersion oAuthVersion;
    private final GrantType authStrategy;

    private Instant lastRefreshed;
    private AccessToken cachedToken;

    public String getBearerToken() {
        if (lastRefreshed == null || tokenHasExpired()) {
            refreshToken();
        }
        return cachedToken.getAccessToken();
    }

    private boolean tokenHasExpired() {
        return Instant.now().isAfter(lastRefreshed.plus(cachedToken.getExpiresIn()));
    }

    private void refreshToken() {
        this.lastRefreshed = Instant.now();
        this.cachedToken = getAccessToken();
    }

    public AccessToken getAccessToken() {
        String response = given()
            .baseUri(oAuthVersion.baseUri(TENANT_ID))
            .formParams(authStrategy.formParams())
            .post()
            .then()
            .statusCode(200)
            .extract()
            .response()
            .asString();

        JsonPath js = new JsonPath(response);
        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken(js.get("access_token"));

        accessToken.setExpiresIn(oAuthVersion.parseExpiry(js.get("expires_in")));

        return accessToken;
    }
}
