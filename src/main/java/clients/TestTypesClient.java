package clients;

import clients.model.TestTypeQueryParam;
import io.restassured.filter.Filter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import util.BasePathFilter;
import util.NoDataPathFilter;

import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static util.WriterReader.saveUtils;

public class TestTypesClient {

    public Response getTestTypesWithData() {
        return getTestTypes(new BasePathFilter());
    }

    public Response getTestTypesWithNoData() {
        return getTestTypes(new NoDataPathFilter());
    }

    private Response getTestTypes(Filter filter) {
        Response response = callGetTestTypes(filter);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetTestTypes(filter);
        }

        return response;

    }

    public Response getTestTypes(String id, TestTypeQueryParam testTypeQueryParam) {
        Response response = callTestTypes(id, testTypeQueryParam);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callTestTypes(id, testTypeQueryParam);
        }

        return response;

    }

    private Response callTestTypes(String id, TestTypeQueryParam testTypeQueryParam) {
        RequestSpecification responseSpec = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("id", id);


        if (testTypeQueryParam.getFields() != null) {
            String testTypeFields = testTypeQueryParam.getFields().stream()
                        .map( n -> n.getField() )
                        .collect( Collectors.joining( "," ) );

            responseSpec.queryParam("fields", testTypeFields);
        }

        if (testTypeQueryParam.getVehicleType() != null) {
            responseSpec.queryParam("vehicleType", testTypeQueryParam.getVehicleType());
        }

        if (testTypeQueryParam.getVehicleSize() != null) {
            responseSpec.queryParam("vehicleSize", testTypeQueryParam.getVehicleSize());
        }

        if (testTypeQueryParam.getVehicleConfiguration() != null) {
            responseSpec.queryParam("vehicleConfiguration", testTypeQueryParam.getVehicleConfiguration());
        }

        if (testTypeQueryParam.getVehicleAxles() != null) {
            responseSpec.queryParam("vehicleAxles", testTypeQueryParam.getVehicleAxles());
        }

        if (testTypeQueryParam.getVehicleClass() != null) {
            responseSpec.queryParam("vehicleClass", testTypeQueryParam.getVehicleClass());
        }

        if (testTypeQueryParam.getVehicleSubClass() != null) {
            responseSpec.queryParam("vehicleSubclass", testTypeQueryParam.getVehicleSubClass());
        }

        if (testTypeQueryParam.getVehicleWheels() != null) {
            responseSpec.queryParam("vehicleWheels", testTypeQueryParam.getVehicleWheels());
        }

        if (testTypeQueryParam.getEuVehicleCategory() != null) {
            responseSpec.queryParam("euVehicleCategory", testTypeQueryParam.getEuVehicleCategory());
        }

        Response response = responseSpec
//                .log().all()
                .log().method().log().uri().log().body()
                .get("/test-types/{id}");

        return response;

    }

    private Response callGetTestTypes(Filter filter) {
        Response response = given().filters(filter)
                .contentType(ContentType.JSON)
//                 .log().all()
            .log().method().log().uri().log().body()
                .get("/test-types");

        return response;

    }
}
