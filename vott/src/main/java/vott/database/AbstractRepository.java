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
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                sqlGenerator.generatePartialUpsertSql(getTableDetails()),
                Statement.RETURN_GENERATED_KEYS
            );

            setParameters(preparedStatement, entity);

            return upsert(
                preparedStatement,
                entity
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int fullUpsert(T entity) {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                sqlGenerator.generateFullUpsertSql(getTableDetails()),
                Statement.RETURN_GENERATED_KEYS
            );

            setParametersFull(preparedStatement, entity);

            return upsert(
                preparedStatement,
                entity
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    protected abstract void setParametersFull(PreparedStatement preparedStatement, T entity) throws SQLException;

    protected abstract T mapToEntity(ResultSet rs) throws SQLException;

    private int upsert(PreparedStatement preparedStatement, T entity) throws SQLException {
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
    }
}
