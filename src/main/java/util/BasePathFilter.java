package util;

import exceptions.AutomationException;
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

    private static Loader loader;

    static {
        EnvironmentType envType = TypeLoader.getType();

        switch (envType) {
            case CI_DEVELOP:
                loader = new CIDevelopLoaderImpl();
                break;
            case LOCAL:
                loader = new LocalLoaderImpl();
                break;
            default:
                throw new AutomationException("Environment configuration not found");
        }
    }

   /*@Override
    public Response filter(FilterableRequestSpecification filterableRequestSpecification, FilterableResponseSpecification filterableResponseSpecification, FilterContext filterContext) {

        filterableRequestSpecification.given().baseUri(loader.getBasePathUrl()).config(config().sslConfig(new SSLConfig().relaxedHTTPSValidation()));
        if (!isWrongAuth() && !isMissingAuth()) {
            filterableRequestSpecification.header("Authorization", "Bearer " +WriterReader.getToken());
        } else if (isWrongAuth()) {
            filterableRequestSpecification.header("Authorization", "Bearer " +RandomStringUtils.randomAlphanumeric(30));
        }

        return filterContext.next(filterableRequestSpecification, filterableResponseSpecification);
    }*/

    @Override
    public Response filter (FilterableRequestSpecification filterableRequestSpecification, FilterableResponseSpecification filterableResponseSpecification, FilterContext filterContext) {

        filterableRequestSpecification.given().baseUri(loader.getBasePathUrl()).config(config().sslConfig(new SSLConfig().relaxedHTTPSValidation()));
        if (!isWrongAuth() && !isMissingAuth()) {
            System.out.println("API key is " +loader.getApiKeys());
            filterableRequestSpecification.header("Authorization", "Bearer " +WriterReader.getToken()).header("X-Api-Key", loader.getApiKeys());
        } else if (isWrongAuth()) {
            filterableRequestSpecification.header("Authorization", "Bearer " +RandomStringUtils.randomAlphanumeric(30));
        }

        return filterContext.next(filterableRequestSpecification, filterableResponseSpecification);
    }
}
