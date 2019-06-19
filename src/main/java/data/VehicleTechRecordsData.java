package data;

import data.config.BaseData;
import data.config.DataMapper;
import model.vehicles.*;

public class VehicleTechRecordsData {

    public static Vehicle buildVehicleTechRecordsCurrentData() {

        Vehicle vehicle = DataMapper.getValue(Vehicle.class, "loader/" + BaseData.getDataLocation() + "/technical-records_current.json");

        return vehicle;

    }


    public static Vehicle buildVehicleTechRecordsArchivedData() {

        Vehicle vehicle = DataMapper.getValue(Vehicle.class, "loader/" + BaseData.getDataLocation() + "/technical-records_archived.json");

        return vehicle;


    }

    public static Vehicle buildVehicleTechRecordsProvisionalData() {

        Vehicle vehicle = DataMapper.getValue(Vehicle.class, "loader/" + BaseData.getDataLocation() + "/technical-records_provisional.json");

        return vehicle;
    }


}
