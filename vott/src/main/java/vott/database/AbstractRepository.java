package vott.database;

import vott.sqlgeneration.SqlGenerator;
import vott.sqlgeneration.TableDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRepository<T> {

    private final SqlGenerator sqlGenerator = new SqlGenerator();

    private final ConnectionFactory connectionFactory;

    public AbstractRepository(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public int partialUpsert(T entity) {
        return upsert(
            sqlGenerator.generatePartialUpsertSql(getTableDetails()),
            entity
        );
    }

    public int fullUpsert(T entity) {
        return upsert(
            sqlGenerator.generateFullUpsertSql(getTableDetails()),
            entity
        );
    }

    public T select(int primaryKey) {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                sqlGenerator.generateSelectSql(getTableDetails(), primaryKey)
            );

            ResultSet rs = preparedStatement.executeQuery();

            List<T> entities = new ArrayList<>();

            while (rs.next()) {
                entities.add(mapToEntity(rs));
            }

            if (entities.size() != 1) {
                throw new RuntimeException("Expected exactly 1 entity, got " + entities.size());
            }

            return entities.get(0);
        } catch (SQLException e) {
            throw new RuntimeException("DELETE failed", e);
        }
    }

    public void delete(int primaryKey) {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                sqlGenerator.generateDeleteSql(getTableDetails(), primaryKey)
            );

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("DELETE failed", e);
        }
    }

    protected abstract TableDetails getTableDetails();

    protected abstract void setParameters(PreparedStatement preparedStatement, T entity) throws SQLException;

    protected abstract T mapToEntity(ResultSet rs) throws SQLException;

    private int upsert(String sql, T entity) {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS
            );

            setParameters(preparedStatement, entity);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows != 1) {
                throw new RuntimeException("Expected exactly 1 affected row, got " + affectedRows);
            }

            ResultSet rs = preparedStatement.getGeneratedKeys();

            int i = 1; // ResultSet instances are 1-indexed

            List<Integer> generatedKeys = new ArrayList<>();

            while (rs.next()) {
                generatedKeys.add(rs.getInt(i++));
            }

            if (generatedKeys.size() != 1) {
                throw new RuntimeException("Expected exactly 1 generated key, got " + generatedKeys.size());
            }

            return generatedKeys.get(0);
        } catch (SQLException e) {
            throw new RuntimeException("INSERT ON DUPLICATE KEY UPDATE failed", e);
        }
    }
}
