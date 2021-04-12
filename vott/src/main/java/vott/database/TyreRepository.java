package vott.database;

import vott.databaseModels.Axles;
import vott.databaseModels.Tyre;
import vott.sqlgeneration.TableDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TyreRepository extends AbstractRepository<Tyre>
{
    public TyreRepository(ConnectionFactory connectionFactory) { super(connectionFactory); }

    @Override
    protected TableDetails getTableDetails() {

        TableDetails tableDetails = new TableDetails();

        tableDetails.setTableName("tyre");
        tableDetails.setColumnNames(new String[] {
                "tyreSize",
                "plyRating",
                "fitmentCode",
                "dataTrAxles",
                "speedCategorySymbol",
                "tyreCode",
        });

        return tableDetails;
    }

    @Override
    protected void setParameters(PreparedStatement preparedStatement, Tyre entity) throws SQLException {
        // 1-indexed
        preparedStatement.setString(1, entity.getTyreSize());
        preparedStatement.setString(2, entity.getPlyRating());
        preparedStatement.setString(3, entity.getFitmentCode());
        preparedStatement.setString(4, entity.getDataTrAxles());
        preparedStatement.setString(5, entity.getSpeedCategorySymbol());
        preparedStatement.setString(6, entity.getTyreCode());
    }

    @Override
    protected Tyre mapToEntity(ResultSet rs) throws SQLException {
        Tyre tyre = new Tyre();

        tyre.setTyreSize(rs.getString("tyreSize"));
        tyre.setPlyRating(rs.getString("plyRating"));
        tyre.setFitmentCode(rs.getString("fitmentCode"));
        tyre.setDataTrAxles(rs.getString("dataTrAxles"));
        tyre.setSpeedCategorySymbol(rs.getString("speedCategorySymbol"));
        tyre.setTyreCode(rs.getString("tyreCode"));

        return tyre;
    }
}
