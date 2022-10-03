package util;

import io.restassured.config.SSLConfig;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.commons.lang3.RandomStringUtils;

import static io.restassured.RestAssured.config;
import static util.TypeLoader.isMissingAuth;
import static util.TypeLoader.isWrongAuth;

public class BasePathFilter implements Filter {
    private static final Loader loader = new LocalLoaderImpl();

    @Override
    public Response filter(FilterableRequestSpecification filterableRequestSpecification, FilterableResponseSpecification filterableResponseSpecification, FilterContext filterContext) {
        filterableRequestSpecification.given().baseUri(createBaseUrl()).config(config().sslConfig(new SSLConfig().relaxedHTTPSValidation()));
        if (!isWrongAuth() && !isMissingAuth()) {
            filterableRequestSpecification.header("Authorization", "Bearer " + WriterReader.getToken()).header("X-Api-Key", loader.getApiKeys());
        } else if (isWrongAuth()) {
            filterableRequestSpecification.header("Authorization", "Bearer " + RandomStringUtils.randomAlphanumeric(30));
        }
        return filterContext.next(filterableRequestSpecification, filterableResponseSpecification);
    }

    /**
     * This is a method to create the VTA base path
     * depending whether you are running this method on a feature branch or on develop
     * @return Base url as String
     */
    private String createBaseUrl() {
        String baseUrl = loader.getBasePathUrl();
        String branchName = loader.getBranchName();
        return branchName.equalsIgnoreCase("develop") ?
                baseUrl.replaceFirst("(branch-)", "").replace("branch", branchName) :
                baseUrl.replaceFirst("(branch)", branchName).replace("branch", branchName);
    }
}
