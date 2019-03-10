package util;

import exceptions.AutomationException;
import io.restassured.config.SSLConfig;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import static io.restassured.RestAssured.config;

public class NoDataPathFilter implements Filter {
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

    @Override
    public Response filter(FilterableRequestSpecification filterableRequestSpecification, FilterableResponseSpecification filterableResponseSpecification, FilterContext filterContext) {

        filterableRequestSpecification.given().baseUri(loader.getNoDataBasePathUrl()).config(config().sslConfig(new SSLConfig().relaxedHTTPSValidation())).header("Authorization",
                "Bearer " + WriterReader.getToken());

        return filterContext.next(filterableRequestSpecification,filterableResponseSpecification);
    }
}
