package vott.database.connection;

@FunctionalInterface
public interface DatabaseConfigurationProvider {
    DatabaseConfiguration provide();
}
