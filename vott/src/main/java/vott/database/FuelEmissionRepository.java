package vott.database;

import vott.databaseModels.FuelEmission;
import vott.sqlgeneration.TableDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FuelEmissionRepository extends AbstractRepository<FuelEmission> {
    public FuelEmissionRepository(ConnectionFactory connectionFactory) { super(connectionFactory); }

    @Override
    protected TableDetails getTableDetails() {

        TableDetails tableDetails = new TableDetails();

        tableDetails.setTableName("fuel_emission");
        tableDetails.setColumnNames(new String[] {
                "modTypeCode",
                "description",
                "emissionStandard",
                "fuelType",
        });

        return tableDetails;
    }

    @Override
    protected void setParameters(PreparedStatement preparedStatement, FuelEmission entity) throws SQLException {
        // 1-indexed
        preparedStatement.setString(1, entity.getModTypeCode());
        preparedStatement.setString(2, entity.getDescription());
        preparedStatement.setString(3, entity.getEmissionStandard());
        preparedStatement.setString(4, entity.getFuelType());
    }

    @Override
    protected void setParametersFull(PreparedStatement preparedStatement, FuelEmission entity) throws SQLException {

    }

    @Override
    protected FuelEmission mapToEntity(ResultSet rs) throws SQLException {
        FuelEmission fe = new FuelEmission();

        fe.setModTypeCode(rs.getString("modTypeCode"));
        fe.setDescription(rs.getString("description"));
        fe.setEmissionStandard(rs.getString("emissionStandard"));
        fe.setFuelType(rs.getString("fuelType"));

        return fe;
    }
}
