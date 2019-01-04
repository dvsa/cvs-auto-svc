package util;

import io.restassured.config.SSLConfig;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import static io.restassured.RestAssured.config;

public class NoDataPathFilter implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification filterableRequestSpecification, FilterableResponseSpecification filterableResponseSpecification, FilterContext filterContext) {
        filterableRequestSpecification.given().baseUri(BackEndLoader.getNoDataBasePathUrl()).config(config().sslConfig(new SSLConfig().relaxedHTTPSValidation()));

        return filterContext.next(filterableRequestSpecification,filterableResponseSpecification);
    }
}
