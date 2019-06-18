package data;

import data.config.DataMapper;
import model.vehicles.*;
import util.DataLoader;

public class VehicleTechRecordsData {

    public static Vehicle buildVehicleTechRecordsCurrentData() {

        Vehicle vehicle = DataMapper.getValue(Vehicle.class, "loader/" + DataLoader.getDataLocation() + "/technical-records_current.json");

        return vehicle;

    }


    public static Vehicle buildVehicleTechRecordsArchivedData() {

        Vehicle vehicle = DataMapper.getValue(Vehicle.class, "loader/" + DataLoader.getDataLocation() + "/technical-records_archived.json");

        return vehicle;


    }

    public static Vehicle buildVehicleTechRecordsProvisionalData() {

        Vehicle vehicle = DataMapper.getValue(Vehicle.class, "loader/" + DataLoader.getDataLocation() + "/technical-records_provisional.json");

        return vehicle;
    }


}
