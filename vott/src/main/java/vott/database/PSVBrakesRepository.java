package vott.database;

import vott.databaseModels.PSVBrakes;
import vott.databaseModels.Vehicle;
import vott.sqlgeneration.TableDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PSVBrakesRepository extends AbstractRepository<PSVBrakes>{
    public PSVBrakesRepository(ConnectionFactory connectionFactory) { super(connectionFactory); }

    @Override
    protected TableDetails getTableDetails() {

        TableDetails tableDetails = new TableDetails();

        tableDetails.setTableName("psv_brakes");
        tableDetails.setColumnNames(new String[] {
                "technical_record_id",
                "brakeCodeOriginal",
                "brakeCode",
                "dataTrBrakeOne",
                "dataTrBrakeTwo",
                "dataTrBrakeThree",
                "retarderBrakeOne",
                "retarderBrakeTwo",
                "serviceBrakeForceA",
                "secondaryBrakeForceA",
                "parkingBrakeForceA",
                "serviceBrakeForceB",
                "secondaryBrakeForceB",
                "parkingBrakeForceB",
        });

        return tableDetails;
    }

    @Override
    protected void setParameters(PreparedStatement preparedStatement, PSVBrakes entity) throws SQLException {
        // 1-indexed
        preparedStatement.setString(1, entity.getTechnicalRecordID());
        preparedStatement.setString(2, entity.getBrakeCodeOriginal());
        preparedStatement.setString(3, entity.getBrakeCode());
        preparedStatement.setString(4, entity.getDataTrBrakeOne());
        preparedStatement.setString(5, entity.getDataTrBrakeTwo());
        preparedStatement.setString(6, entity.getDataTrBrakeThree());
        preparedStatement.setString(7, entity.getRetarderBrakeOne());
        preparedStatement.setString(8, entity.getRetarderBrakeTwo());
        preparedStatement.setString(9, entity.getServiceBrakeForceA());
        preparedStatement.setString(10, entity.getSecondaryBrakeForceA());
        preparedStatement.setString(11, entity.getParkingBrakeForceA());
        preparedStatement.setString(12, entity.getServiceBrakeForceB());
        preparedStatement.setString(13, entity.getSecondaryBrakeForceB());
        preparedStatement.setString(14, entity.getParkingBrakeForceB());
    }

    @Override
    protected PSVBrakes mapToEntity(ResultSet rs) throws SQLException {
        PSVBrakes psv = new PSVBrakes();

        psv.setTechnicalRecordID(rs.getString("technical_record_id"));
        psv.setBrakeCodeOriginal(rs.getString("brakeCodeOriginal"));
        psv.setBrakeCode(rs.getString("brakeCode"));
        psv.setDataTrBrakeOne(rs.getString("dataTrBrakeOne"));
        psv.setDataTrBrakeTwo(rs.getString("dataTrBrakeTwo"));
        psv.setDataTrBrakeThree(rs.getString("dataTrBrakeThree"));
        psv.setRetarderBrakeOne(rs.getString("retarderBrakeOne"));
        psv.setRetarderBrakeTwo(rs.getString("retarderBrakeTwo"));
        psv.setServiceBrakeForceA(rs.getString("serviceBrakeForceA"));
        psv.setSecondaryBrakeForceA(rs.getString("secondaryBrakeForceA"));
        psv.setParkingBrakeForceA(rs.getString("parkingBrakeForceA"));
        psv.setServiceBrakeForceB(rs.getString("serviceBrakeForceB"));
        psv.setSecondaryBrakeForceB(rs.getString("secondaryBrakeForceB"));
        psv.setParkingBrakeForceB(rs.getString("parkingBrakeForceB"));

        return psv;
    }
}
