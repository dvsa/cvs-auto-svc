package vott.database;

import vott.database.connection.ConnectionFactory;
import vott.models.dao.VehicleSubclass;
import vott.sqlgeneration.TableDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleSubclassRepository extends AbstractRepository<VehicleSubclass>{
    public VehicleSubclassRepository(ConnectionFactory connectionFactory) { super(connectionFactory); }

    @Override
    protected TableDetails getTableDetails() {

        TableDetails tableDetails = new TableDetails();

        tableDetails.setTableName("vehicle_subclass");
        tableDetails.setColumnNames(new String[] {
                "vehicle_class_id",
                "subclass",
        });

        return tableDetails;

    }

    @Override
    protected void setParameters(PreparedStatement preparedStatement, VehicleSubclass entity) throws SQLException {
        preparedStatement.setString(1, entity.getVehicleClassID());
        preparedStatement.setString(2, entity.getSubclass());
    }

    @Override
    protected void setParametersFull(PreparedStatement preparedStatement, VehicleSubclass entity) throws SQLException {
        setParameters(preparedStatement, entity);

        preparedStatement.setString(3, entity.getVehicleClassID());
        preparedStatement.setString(4, entity.getSubclass());

    }

    @Override
    protected VehicleSubclass mapToEntity(ResultSet rs) throws SQLException {
        VehicleSubclass vs = new VehicleSubclass();

        vs.setVehicleClassID(rs.getString("vehicle_subclass_id"));
        vs.setSubclass(rs.getString("subclass"));

        return vs;
    }
}
