package model.vehicles;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(value = { "primaryVrm", "secondaryVrms", "partialVin"})
public class Vehicle {


    private List<Vrms> vrms;
    private String vin;
    private List<TechRecord> techRecord;
    private MsUserDetails msUserDetails;
    private String systemNumber;

    public List<Vrms> getVrms() {
        return vrms;
    }

    public Vehicle setVrms(List<Vrms> vrms) {
        this.vrms = vrms;
        return this;
    }

    public String getVin() {
        return vin;
    }

    public String getSystemNumber() {
        return systemNumber;
    }

    public Vehicle setVin(String vin) {
        this.vin = vin;
        return this;
    }

    public Vehicle setSystemNumber(String systemNumber) {
        this.systemNumber = systemNumber;
        return this;
    }

    public List<TechRecord> getTechRecord() {
        return techRecord;
    }

    public Vehicle setTechRecord(List<TechRecord> techRecord) {
        this.techRecord = techRecord;
        return this;
    }

    public MsUserDetails getMsUserDetails() {
        return msUserDetails;
    }

    public Vehicle setMsUserDetails(MsUserDetails msUserDetails) {
        this.msUserDetails = msUserDetails;
        return this;
    }


    @Override
    public String toString() {
        return "Vehicle{" +
                "vrms=" + vrms +
                ", vin='" + vin + '\'' +
                ", systemNumber='" + systemNumber + '\'' +
                ", techRecord=" + techRecord +
                '}';
    }
}
