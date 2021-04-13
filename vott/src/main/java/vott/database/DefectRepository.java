package vott.database;

import vott.database.connection.ConnectionFactory;
import vott.models.dao.Defect;
import vott.sqlgeneration.TableDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DefectRepository extends AbstractRepository<Defect> {
    public DefectRepository(ConnectionFactory connectionFactory) { super(connectionFactory); }

    @Override
    protected TableDetails getTableDetails() {

        TableDetails tableDetails = new TableDetails();

        tableDetails.setTableName("defect");
        tableDetails.setColumnNames(new String[] {
                "imNumber",
                "imDescription",
                "itemNumber",
                "itemDescription",
                "deficiencyRef",
                "deficiencyId",
                "deficiencySubId",
                "deficiencyCategory",
                "deficiencyText",
                "stdForProhibition",
        });

        return tableDetails;
    }

    @Override
    protected void setParameters(PreparedStatement preparedStatement, Defect entity) throws SQLException {
        // 1-indexed
        preparedStatement.setString(1, entity.getImNumber());
        preparedStatement.setString(2, entity.getImDescription());
        preparedStatement.setString(3, entity.getItemNumber());
        preparedStatement.setString(4, entity.getItemDescription());
        preparedStatement.setString(5, entity.getDeficiencyRef());
        preparedStatement.setString(6, entity.getDeficiencyID());
        preparedStatement.setString(7, entity.getDeficiencySubID());
        preparedStatement.setString(8, entity.getDeficiencyCategory());
        preparedStatement.setString(9, entity.getDeficiencyText());
        preparedStatement.setString(10, entity.getStdForProhibition());
    }

    @Override
    protected void setParametersFull(PreparedStatement preparedStatement, Defect entity) throws SQLException {
        setParameters(preparedStatement, entity);

        preparedStatement.setString(11, entity.getImNumber());
        preparedStatement.setString(12, entity.getImDescription());
        preparedStatement.setString(13, entity.getItemNumber());
        preparedStatement.setString(14, entity.getItemDescription());
        preparedStatement.setString(15, entity.getDeficiencyRef());
        preparedStatement.setString(16, entity.getDeficiencyID());
        preparedStatement.setString(17, entity.getDeficiencySubID());
        preparedStatement.setString(18, entity.getDeficiencyCategory());
        preparedStatement.setString(19, entity.getDeficiencyText());
        preparedStatement.setString(20, entity.getStdForProhibition());
    }

    @Override
    protected Defect mapToEntity(ResultSet rs) throws SQLException {
        Defect defect = new Defect();

        defect.setImNumber(rs.getString("imNumber"));
        defect.setImDescription(rs.getString("imDescription"));
        defect.setItemNumber(rs.getString("itemNumber"));
        defect.setItemDescription(rs.getString("itemDescription"));
        defect.setDeficiencyRef(rs.getString("deficiencyRef"));
        defect.setDeficiencyID(rs.getString("deficiencyId"));
        defect.setDeficiencySubID(rs.getString("deficiencySubId"));
        defect.setDeficiencyCategory(rs.getString("deficiencyCategory"));
        defect.setDeficiencyText(rs.getString("deficiencyText"));
        defect.setStdForProhibition(rs.getString("stdForProhibition"));

        return defect;
    }
}
