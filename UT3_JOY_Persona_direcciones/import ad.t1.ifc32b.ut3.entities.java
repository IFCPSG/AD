import ad.t1.ifc32b.ut3.entities.Direccion;
import ad.t1.ifc32b.ut3.entities.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaRepository implements Repository<Persona> {
    Connection connection;

    public PersonaRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Persona> findAllWithDirecciones() {
        String query = "SELECT * FROM personas p INNER JOIN direcciones d ON p.id=d.persona_id";
        ArrayList<Persona> personas = new ArrayList<>();

        try {
            PreparedStatement estado = connection.prepareStatement(query);
            ResultSet resultSet = estado.executeQuery();

            while (resultSet.next()) {
                Persona persona = new Persona(
                        resultSet.getInt("p.id"),
                        resultSet.getString("p.nombre"),
                        resultSet.getString("p.password"),
                        resultSet.getInt("p.telefono")
                );
                Direccion direccion = new Direccion(
                        resultSet.getInt("d.id"),
                        resultSet.getInt("d.persona_id"),
                        resultSet.getString("d.direccion")
                );

                if (personas.contains(persona)) {
                    System.out.println("La persona está en la lista");
                } else {
                    System.out.println("La persona NO está en la lista");
                }

                personas.add(persona);
            }

            return personas;

        } catch (SQLException ex) {
            System.err.println("Error al ejecutar la consulta: " + query);
            System.err.println(ex.getMessage());
            return personas;
        }
    }

    @Override
    public Persona findOneById(int id) {
        String query = "SELECT * FROM personas WHERE id = ?";

        try (PreparedStatement estado = connection.prepareStatement(query)) {
            estado.setInt(1, id);
            ResultSet resultSet = estado.executeQuery();

            if (resultSet.next()) {
                return new Persona(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("password"),
                        resultSet.getInt("telefono")
                );
            } else {
                System.out.println("No se encontró ninguna persona con ID: " + id);
                return null;
            }

        } catch (SQLException ex) {
            System.err.println("Error al ejecutar la consulta: " + query);
            System.err.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public Persona save(Persona persona) {
        String query = "INSERT INTO personas (nombre, password, telefono) VALUES (?, ?, ?)";

        try (PreparedStatement estado = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            estado.setString(1, persona.getNombre());
            estado.setString(2, persona.getPassword());
            estado.setInt(3, persona.getTelefono());

            estado.executeUpdate();

            ResultSet key = estado.getGeneratedKeys();
            if (key.next()) {
                persona.setId(key.getInt(1));
            }

            System.out.println("Datos insertados correctamente");
            return persona;

        } catch (SQLException ex) {
            System.err.println("Error al insertar datos");
            System.err.println(ex.getMessage());
            return persona;
        }
    }

    @Override
    public List<Persona> findAll() {
        String query = "SELECT * FROM personas";
        ArrayList<Persona> personas = new ArrayList<>();

        try (PreparedStatement estado = connection.prepareStatement(query)) {
            ResultSet resultSet = estado.executeQuery();

            while (resultSet.next()) {
                Persona persona = new Persona(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("password"),
                        resultSet.getInt("telefono")
                );
                personas.add(persona);
            }

            return personas;

        } catch (SQLException ex) {
            System.err.println("Error al ejecutar la consulta: " + query);
            System.err.println(ex.getMessage());
            return personas;
        }
    }

    @Override
    public void updateById(int id, Persona persona) {
        String query = "UPDATE personas SET nombre=?, password=?, telefono=? WHERE id=?";

        try (PreparedStatement estado = connection.prepareStatement(query)) {
            estado.setString(1, persona.getNombre());
            estado.setString(2, persona.getPassword());
            estado.setInt(3, persona.getTelefono());
            estado.setInt(4, id);

            estado.executeUpdate();

            System.out.println("Datos actualizados correctamente");

        } catch (SQLException ex) {
            System.err.println("Error al actualizar datos");
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void deleteById(int id) {
        String query = "DELETE FROM personas WHERE id=?";

        try (PreparedStatement estado = connection.prepareStatement(query)) {
            estado.setInt(1, id);
            estado.executeUpdate();

            System.out.println("Datos eliminados correctamente");

        } catch (SQLException ex) {
            System.err.println("Error al eliminar datos");
            System.err.println(ex.getMessage());
        }
    }
}
