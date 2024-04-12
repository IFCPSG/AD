package H2;


        import Common.DDL;
        import Common.DML;

        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        // Configuración de conexión H2
        String url = "jdbc:h2:~/test"; // Cambiar "tu_base de_datos" por el nombre de tu base de datos
        String usuario = "root"; // Cambiar "tu_usuario" por tu nombre de usuario
        String contraseña = ""; // Cambiar "tu_contraseña" por tu contraseña

        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña)) {
            System.out.println("Conexión establecida con H2");

            // Crear instancias de DDL y DML
            DDL ddl = new DDL(conexion);
            DML dml = new DML(conexion);

            // Ejemplo de uso de DDL
            ddl.crearAmbasTablas();  // Puedes ajustar esta llamada según tus necesidades

            // Ejemplo de uso de DML
            // Puedes realizar operaciones de consulta, inserción, actualización y eliminación con DML
            // Ejemplo:
            // dml.insertarPersona(new Persona(1, "Nombre", "123456789", "Dirección"));
            // dml.modificarPersona(1, "NuevoNombre", "987654321", "NuevaDireccion");
            // dml.eliminarPersonaYDireccion(1);

        } catch (SQLException e) {
            System.err.println("Error al conectar con H2: " + e.getMessage());
        }
    }
}
