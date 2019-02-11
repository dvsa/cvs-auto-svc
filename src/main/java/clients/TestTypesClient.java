package clients;

import clients.model.TestTypeField;
import clients.model.TestTypeQueryParam;
import io.restassured.filter.Filter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import util.BasePathFilter;
import util.NoDataPathFilter;

import java.util.Collections;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class TestTypesClient {

    public Response getTestTypesWithData() {
        return getTestTypes(new BasePathFilter());
    }

    public Response getTestTypesWithNoData() {
        return getTestTypes(new NoDataPathFilter());
    }

    private Response getTestTypes(Filter filter) {
        Response response = given().filters(filter)
                .contentType(ContentType.JSON)
                .get("/test-types");

        return response;

    }

    public Response getTestTypes(String id, TestTypeQueryParam testTypeQueryParam) {
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


        Response response = responseSpec.get("/test-types/{id}");

        return response;

    }


}
