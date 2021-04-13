package vott.models.dao;

import lombok.Data;

@Data
public class VehicleClass {
    private String code;
    private String description;
    private String vehicleType;
    private String vehicleSize;
    private String vehicleConfiguration;
    private String euVehicleCategory;

}
