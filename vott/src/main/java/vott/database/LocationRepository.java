package vott.database;

import vott.database.connection.ConnectionFactory;
import vott.models.dao.Location;
import vott.sqlgeneration.TableDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationRepository extends AbstractRepository<Location> {
    public LocationRepository(ConnectionFactory connectionFactory) { super(connectionFactory); }

    @Override
    protected TableDetails getTableDetails() {

        TableDetails tableDetails = new TableDetails();

        tableDetails.setTableName("location");
        tableDetails.setColumnNames(new String[] {
                "vertical",
                "horizontal",
                "lateral",
                "longitudinal",
                "rowNumber",
                "seatNumber",
                "axleNumber",
        });

        return tableDetails;
    }

    @Override
    protected void setParameters(PreparedStatement preparedStatement, Location entity) throws SQLException {
        // 1-indexed
        preparedStatement.setString(1, entity.getVertical());
        preparedStatement.setString(2, entity.getHorizontal());
        preparedStatement.setString(3, entity.getLateral());
        preparedStatement.setString(4, entity.getLongitudinal());
        preparedStatement.setString(5, entity.getRowNumber());
        preparedStatement.setString(6, entity.getSeatNumber());
        preparedStatement.setString(7, entity.getAxleNumber());
    }

    @Override
    protected void setParametersFull(PreparedStatement preparedStatement, Location entity) throws SQLException {
        setParameters(preparedStatement, entity);

        preparedStatement.setString(8, entity.getVertical());
        preparedStatement.setString(9, entity.getHorizontal());
        preparedStatement.setString(10, entity.getLateral());
        preparedStatement.setString(11, entity.getLongitudinal());
        preparedStatement.setString(12, entity.getRowNumber());
        preparedStatement.setString(13, entity.getSeatNumber());
        preparedStatement.setString(14, entity.getAxleNumber());
    }

    @Override
    protected Location mapToEntity(ResultSet rs) throws SQLException {
        Location location = new Location();

        location.setVertical(rs.getString("vertical"));
        location.setHorizontal(rs.getString("horizontal"));
        location.setLateral(rs.getString("lateral"));
        location.setLongitudinal(rs.getString("longitudinal"));
        location.setRowNumber(rs.getString("rowNumber"));
        location.setSeatNumber(rs.getString("seatNumber"));
        location.setAxleNumber(rs.getString("axleNumber"));

        return location;
    }
}
