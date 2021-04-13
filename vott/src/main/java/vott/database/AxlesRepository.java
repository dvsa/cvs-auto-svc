package vott.database;

import vott.database.connection.ConnectionFactory;
import vott.models.dao.Axles;
import vott.sqlgeneration.TableDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AxlesRepository extends AbstractRepository<Axles> {
    public AxlesRepository(ConnectionFactory connectionFactory) { super(connectionFactory); }

    @Override
    protected TableDetails getTableDetails() {

        TableDetails tableDetails = new TableDetails();

        tableDetails.setTableName("axles");
        tableDetails.setColumnNames(new String[] {
                "technical_record_id",
                "tyre_id",
                "axleNumber",
                "parkingBrakeMrk",
                "kerbWeight",
                "ladenWeight",
                "gbWeight",
                "eecWeight",
                "designWeight",
                "brakeActuator",
                "leverLength",
                "springBrakeParking",
        });

        return tableDetails;

    }

    @Override
    protected void setParameters(PreparedStatement preparedStatement, Axles entity) throws SQLException {
        // 1-indexed
        preparedStatement.setString(1, entity.getTechnicalRecordID());
        preparedStatement.setString(2, entity.getTyreID());
        preparedStatement.setString(3, entity.getAxleNumber());
        preparedStatement.setString(4, entity.getParkingBrakeMrk());
        preparedStatement.setString(5, entity.getKerbWeight());
        preparedStatement.setString(6, entity.getLadenWeight());
        preparedStatement.setString(7, entity.getGbWeight());
        preparedStatement.setString(8, entity.getEecWeight());
        preparedStatement.setString(9, entity.getDesignWeight());
        preparedStatement.setString(10, entity.getBrakeActuator());
        preparedStatement.setString(11, entity.getLeverLength());
        preparedStatement.setString(12, entity.getSpringBrakeParking());
    }

    @Override
    protected void setParametersFull(PreparedStatement preparedStatement, Axles entity) throws SQLException {
        setParameters(preparedStatement, entity);

        preparedStatement.setString(13, entity.getTechnicalRecordID());
        preparedStatement.setString(14, entity.getTyreID());
        preparedStatement.setString(15, entity.getAxleNumber());
        preparedStatement.setString(16, entity.getParkingBrakeMrk());
        preparedStatement.setString(17, entity.getKerbWeight());
        preparedStatement.setString(18, entity.getLadenWeight());
        preparedStatement.setString(19, entity.getGbWeight());
        preparedStatement.setString(20, entity.getEecWeight());
        preparedStatement.setString(21, entity.getDesignWeight());
        preparedStatement.setString(22, entity.getBrakeActuator());
        preparedStatement.setString(23, entity.getLeverLength());
        preparedStatement.setString(24, entity.getSpringBrakeParking());
    }

    @Override
    protected Axles mapToEntity(ResultSet rs) throws SQLException {
        Axles axle = new Axles();

        axle.setTechnicalRecordID(rs.getString("technical_record_id"));
        axle.setTyreID(rs.getString("tyre_id"));
        axle.setAxleNumber(rs.getString("axleNumber"));
        axle.setParkingBrakeMrk(rs.getString("parkingBrakeMrk"));
        axle.setKerbWeight(rs.getString("kerbWeight"));
        axle.setLadenWeight(rs.getString("ladenWeight"));
        axle.setGbWeight(rs.getString("gbWeight"));
        axle.setEecWeight(rs.getString("eecWeight"));
        axle.setDesignWeight(rs.getString("designWeight"));
        axle.setBrakeActuator(rs.getString("brakeActuator"));
        axle.setLeverLength(rs.getString("leverLength"));
        axle.setSpringBrakeParking(rs.getString("springBrakeParking"));

        return axle;
    }
}
