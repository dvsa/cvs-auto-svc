package util;

import io.restassured.config.SSLConfig;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import static io.restassured.RestAssured.config;

public class NoDataPathFilter implements Filter {
    private static final Loader loader = new LocalLoaderImpl();
    @Override
    public Response filter(FilterableRequestSpecification filterableRequestSpecification, FilterableResponseSpecification filterableResponseSpecification, FilterContext filterContext) {
        filterableRequestSpecification.given().baseUri(returnBaseUrl()).config(config().sslConfig(new SSLConfig().relaxedHTTPSValidation())).header("Authorization",
                "Bearer " + WriterReader.getToken());
        return filterContext.next(filterableRequestSpecification,filterableResponseSpecification);
    }

    /**
     * Simple method to construct the base URL with the new format following the APIG restructure
     * @return Base url as String
     */
    private String returnBaseUrl() {
        String baseUrl = loader.getNoDataBasePathUrl();
        String branchName = loader.getBranchName();
        if (branchName.equalsIgnoreCase("develop")) {
            return baseUrl.replaceFirst("(branch-)", "").replace("branch", branchName);
        } else {
            return baseUrl.replaceFirst("(branch)", branchName).replace("branch", branchName);
        }
    }
}
