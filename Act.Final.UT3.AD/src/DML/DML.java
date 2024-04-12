package DML;

import Entities.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DML {
    private Connection connection;

    public DML(Connection connection) {
        this.connection = connection;
    }

    public void insertPersona(Entities.Persona persona) {
        String query = "INSERT INTO Persona (id, nombre, telefono, direccion) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, persona.getId());
            statement.setString(2, persona.getNombre());
            statement.setString(3, persona.getTelefono());
            statement.setString(4, persona.getDireccion());
            statement.executeUpdate();
            System.out.println("Persona inserted successfully.");
        } catch (SQLException e) {
            System.err.println("Error inserting persona: " + e.getMessage());
        }
    }

    // Implement other DML operations (update, delete, etc.) as needed
}
