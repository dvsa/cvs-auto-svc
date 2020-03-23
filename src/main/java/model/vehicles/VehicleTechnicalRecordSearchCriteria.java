package model.vehicles;

public enum VehicleTechnicalRecordSearchCriteria {

    ALL("all"), VIN("vin"), VRM("vrm"), PARTIAL_VIN("partialVin"),
    TRAILER_ID("trailerId"), SYSTEM_NUMBER("systemNumber");

    private String searchCriteria;

    VehicleTechnicalRecordSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public String getSearchCriteria() {
        return searchCriteria;
    }
}
