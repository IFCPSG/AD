package Common;

import Entities.Direccion;
import Entities.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Clase DML
public class DML {
    private Connection conexion;  // Necesitas una conexión a la base de datos para ejecutar las consultas

    // Constructor
    public DML(Connection conexion) {
        this.conexion = conexion;
    }

    // Función para consultar datos
    public void consultarDatos(String query) {
        try {
            try (PreparedStatement statement = conexion.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                // Procesar los resultados
                while (resultSet.next()) {
                    // Lógica para procesar cada fila de resultados
                    // Puedes imprimir o manipular los datos según tus necesidades
                }

                System.out.println("Consulta exitosa.");
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar datos: " + e.getMessage());
        }
    }

    // Función para consultar una persona por ID
    public Persona consultarPersonaPorId(int id) {
        try {
            String query = "SELECT * FROM Persona WHERE id = ?";
            try (PreparedStatement statement = conexion.prepareStatement(query)) {
                statement.setInt(1, id);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        // Construir y devolver la instancia de Persona
                        return construirPersonaDesdeResultSet(resultSet);
                    }
                }
            }

            System.out.println("Persona no encontrada con ID: " + id);
        } catch (SQLException e) {
            System.err.println("Error al consultar persona por ID: " + e.getMessage());
        }

        return null;
    }

    // Función para consultar todas las personas
    public ArrayList<Persona> consultarTodasLasPersonas() {
        ArrayList<Persona> personas = new ArrayList<>();

        try {
            String query = "SELECT * FROM Persona";
            try (PreparedStatement statement = conexion.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    personas.add(construirPersonaDesdeResultSet(resultSet));
                }

                System.out.println("Consulta exitosa de todas las personas.");
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar todas las personas: " + e.getMessage());
        }

        return personas;
    }

    // Función para consultar una persona por ID junto a sus direcciones
    public Persona consultarPersonaConDirecciones(int idPersona) {
        Persona persona = consultarPersonaPorId(idPersona);

        if (persona != null) {
            try {
                String query = "SELECT * FROM Direccion WHERE idPersona = ?";
                try (PreparedStatement statement = conexion.prepareStatement(query)) {
                    statement.setInt(1, idPersona);

                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            Direccion direccion = construirDireccionDesdeResultSet(resultSet);
                            persona.agregarDireccion(direccion);
                        }
                    }
                }

                System.out.println("Consulta exitosa de persona con direcciones.");
            } catch (SQLException e) {
                System.err.println("Error al consultar persona con direcciones: " + e.getMessage());
            }
        }

        return persona;
    }

    // Función para insertar datos
    public void insertarDatos(String query) {
        try {
            try (PreparedStatement statement = conexion.prepareStatement(query)) {
                statement.executeUpdate();
                System.out.println("Datos insertados con éxito.");
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar datos: " + e.getMessage());
        }
    }

    // Función para insertar una persona
    public void insertarPersona(Persona persona) {
        try {
            String query = "INSERT INTO Persona (id, nombre, telefono, direccion) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = conexion.prepareStatement(query)) {
                statement.setInt(1, persona.getId());
                statement.setString(2, persona.getNombre());
                statement.setString(3, persona.getTelefono());
                statement.setString(4, persona.getDireccion());

                statement.executeUpdate();
                System.out.println("Persona insertada con éxito.");
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar persona: " + e.getMessage());
        }
    }

    // Función para insertar una dirección
    public void insertarDireccion(Direccion direccion) {
        try {
            String query = "INSERT INTO Direccion (id, idPersona, direccion) VALUES (?, ?, ?)";
            try (PreparedStatement statement = conexion.prepareStatement(query)) {
                statement.setInt(1, direccion.getId());
                statement.setInt(2, direccion.getIdPersona());
                statement.setString(3, direccion.getDireccion());

                statement.executeUpdate();
                System.out.println("Dirección insertada con éxito.");
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar dirección: " + e.getMessage());
        }
    }

    // Método privado para construir una instancia de Persona desde un ResultSet
    private Persona construirPersonaDesdeResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String nombre = resultSet.getString("nombre");
        String telefono = resultSet.getString("telefono");
        String direccion = resultSet.getString("direccion");

        return new Persona(id, nombre, telefono, direccion, new ArrayList<>());
    }

    // Método privado para construir una instancia de Direccion desde un ResultSet
    private Direccion construirDireccionDesdeResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int idPersona = resultSet.getInt("idPersona");
        String direccion = resultSet.getString("direccion");

        return new Direccion(id, idPersona, direccion);
    }
    public void actualizarDatos(String query) {
        try {
            try (PreparedStatement statement = conexion.prepareStatement(query)) {
                statement.executeUpdate();
                System.out.println("Datos actualizados con éxito.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar datos: " + e.getMessage());
        }
    }

    // Función para modificar una persona
    public void modificarPersona(int id, String nuevoNombre, String nuevoTelefono, String nuevaDireccion) {
        try {
            String query = "UPDATE Persona SET nombre = ?, telefono = ?, direccion = ? WHERE id = ?";
            try (PreparedStatement statement = conexion.prepareStatement(query)) {
                statement.setString(1, nuevoNombre);
                statement.setString(2, nuevoTelefono);
                statement.setString(3, nuevaDireccion);
                statement.setInt(4, id);

                statement.executeUpdate();
                System.out.println("Persona modificada con éxito.");
            }
        } catch (SQLException e) {
            System.err.println("Error al modificar persona: " + e.getMessage());
        }
    }

    // Función para modificar una dirección
    public void modificarDireccion(int id, int idPersona, String nuevaDireccion) {
        try {
            String query = "UPDATE Direccion SET direccion = ? WHERE id = ? AND idPersona = ?";
            try (PreparedStatement statement = conexion.prepareStatement(query)) {
                statement.setString(1, nuevaDireccion);
                statement.setInt(2, id);
                statement.setInt(3, idPersona);

                statement.executeUpdate();
                System.out.println("Dirección modificada con éxito.");
            }
        } catch (SQLException e) {
            System.err.println("Error al modificar dirección: " + e.getMessage());
        }
    }

    // Función para eliminar datos
    public void eliminarDatos(String query) {
        try {
            try (PreparedStatement statement = conexion.prepareStatement(query)) {
                statement.executeUpdate();
                System.out.println("Datos eliminados con éxito.");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar datos: " + e.getMessage());
        }
    }

    // Función para eliminar una persona y su dirección
    public void eliminarPersonaYDireccion(int idPersona) {
        try {
            // Eliminar la dirección asociada a la persona
            String queryEliminarDireccion = "DELETE FROM Direccion WHERE idPersona = ?";
            try (PreparedStatement statementEliminarDireccion = conexion.prepareStatement(queryEliminarDireccion)) {
                statementEliminarDireccion.setInt(1, idPersona);
                statementEliminarDireccion.executeUpdate();
                System.out.println("Dirección asociada a la persona eliminada con éxito.");
            }

            // Eliminar la persona
            String queryEliminarPersona = "DELETE FROM Persona WHERE id = ?";
            try (PreparedStatement statementEliminarPersona = conexion.prepareStatement(queryEliminarPersona)) {
                statementEliminarPersona.setInt(1, idPersona);
                statementEliminarPersona.executeUpdate();
                System.out.println("Persona eliminada con éxito.");
            }

        } catch (SQLException e) {
            System.err.println("Error al eliminar persona y dirección: " + e.getMessage());
        }
    }
}

