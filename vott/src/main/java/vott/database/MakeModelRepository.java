package vott.database;

import vott.database.connection.ConnectionFactory;
import vott.models.dao.MakeModel;
import vott.sqlgeneration.TableDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MakeModelRepository extends AbstractRepository<MakeModel>{
    public MakeModelRepository(ConnectionFactory connectionFactory) { super(connectionFactory); }

    @Override
    protected TableDetails getTableDetails() {
        TableDetails tableDetails = new TableDetails();

        tableDetails.setTableName("make_model");
        tableDetails.setColumnNames(new String[] {
                "make",
                "model",
                "chassisMake",
                "chassisModel",
                "bodyMake",
                "bodyModel",
                "modelLiteral",
                "bodyTypeCode",
                "bodyTypeDescription",
                "fuelPropulsionSystem",
                "dtpCode",
        });

        return tableDetails;
    }

    @Override
    protected void setParameters(PreparedStatement preparedStatement, MakeModel entity) throws SQLException {
        preparedStatement.setString(1, entity.getMake());
        preparedStatement.setString(2, entity.getModel());
        preparedStatement.setString(3, entity.getChassisMake());
        preparedStatement.setString(4, entity.getChassisModel());
        preparedStatement.setString(5, entity.getBodyMake());
        preparedStatement.setString(6, entity.getBodyModel());
        preparedStatement.setString(7, entity.getModelLiteral());
        preparedStatement.setString(8, entity.getBodyTypeCode());
        preparedStatement.setString(9, entity.getBodyTypeDescription());
        preparedStatement.setString(10, entity.getFuelPropulsionSystem());
        preparedStatement.setString(11, entity.getDtpCode());
    }

    @Override
    protected void setParametersFull(PreparedStatement preparedStatement, MakeModel entity) throws SQLException {
        setParameters(preparedStatement,entity);

        preparedStatement.setString(12, entity.getMake());
        preparedStatement.setString(13, entity.getModel());
        preparedStatement.setString(14, entity.getChassisMake());
        preparedStatement.setString(15, entity.getChassisModel());
        preparedStatement.setString(16, entity.getBodyMake());
        preparedStatement.setString(17, entity.getBodyModel());
        preparedStatement.setString(18, entity.getModelLiteral());
        preparedStatement.setString(19, entity.getBodyTypeCode());
        preparedStatement.setString(20, entity.getBodyTypeDescription());
        preparedStatement.setString(21, entity.getFuelPropulsionSystem());
        preparedStatement.setString(22, entity.getDtpCode());
    }

    @Override
    protected MakeModel mapToEntity(ResultSet rs) throws SQLException {
        MakeModel mm = new MakeModel();

        mm.setMake(rs.getString("make"));
        mm.setModel(rs.getString("model"));
        mm.setChassisMake(rs.getString("chassisMake"));
        mm.setChassisModel(rs.getString("chassisModel"));
        mm.setBodyMake(rs.getString("bodyMake"));
        mm.setBodyModel(rs.getString("bodyModel"));
        mm.setModelLiteral(rs.getString("modelLiteral"));
        mm.setBodyTypeCode(rs.getString("bodyTypeCode"));
        mm.setBodyTypeDescription(rs.getString("bodyTypeDescription"));
        mm.setFuelPropulsionSystem(rs.getString("fuelPropulsionSystem"));
        mm.setDtpCode(rs.getString("dtpCode"));

        return mm;
    }
}
