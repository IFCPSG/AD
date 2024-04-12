package DDL;


import Common.Column;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DDL {
    private Connection connection;

    public DDL(Connection connection) {
        this.connection = connection;
    }

    public void createTable(String tableName, Column... columns) {
        try (Statement statement = connection.createStatement()) {
            StringBuilder query = new StringBuilder("CREATE TABLE " + tableName + " (");
            for (Column column : columns) {
                query.append(column.getSQL()).append(", ");
            }
            query.setLength(query.length() - 2);  // Remove the last comma and space
            query.append(")");
            statement.executeUpdate(query.toString());
            System.out.println("Table " + tableName + " created successfully.");
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    // Implement other DDL operations (update, delete, etc.) as needed
}
