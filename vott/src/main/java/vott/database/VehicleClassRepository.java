package vott.database;

import vott.database.connection.ConnectionFactory;
import vott.models.dao.VehicleClass;
import vott.sqlgeneration.TableDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleClassRepository extends AbstractRepository<VehicleClass>{
    public VehicleClassRepository(ConnectionFactory connectionFactory) { super(connectionFactory); }

    @Override
    protected TableDetails getTableDetails() {

        TableDetails tableDetails = new TableDetails();

        tableDetails.setTableName("vehicle_class");
        tableDetails.setColumnNames(new String[] {
                "code",
                "description",
                "vehicleType",
                "vehicleSize",
                "vehicleConfiguration",
                "euVehicleCategory",
        });

        return tableDetails;
    }

    @Override
    protected void setParameters(PreparedStatement preparedStatement, VehicleClass entity) throws SQLException {
        preparedStatement.setString(1, entity.getCode());
        preparedStatement.setString(2, entity.getDescription());
        preparedStatement.setString(3, entity.getVehicleType());
        preparedStatement.setString(4, entity.getVehicleSize());
        preparedStatement.setString(5, entity.getVehicleConfiguration());
        preparedStatement.setString(6, entity.getEuVehicleCategory());
    }

    @Override
    protected void setParametersFull(PreparedStatement preparedStatement, VehicleClass entity) throws SQLException {
        setParameters(preparedStatement, entity);

        preparedStatement.setString(7, entity.getCode());
        preparedStatement.setString(8, entity.getDescription());
        preparedStatement.setString(9, entity.getVehicleType());
        preparedStatement.setString(10, entity.getVehicleSize());
        preparedStatement.setString(11, entity.getVehicleConfiguration());
        preparedStatement.setString(12, entity.getEuVehicleCategory());
    }

    @Override
    protected VehicleClass mapToEntity(ResultSet rs) throws SQLException {
        VehicleClass vc = new VehicleClass();

        vc.setCode(rs.getString("code"));
        vc.setDescription(rs.getString("description"));
        vc.setVehicleType(rs.getString("vehicleType"));
        vc.setVehicleSize(rs.getString("vehicleSize"));
        vc.setVehicleConfiguration(rs.getString("vehicleConfiguration"));
        vc.setEuVehicleCategory(rs.getString("euVehicleCategory"));

        return vc;
    }
}
