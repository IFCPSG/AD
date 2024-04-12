package Videoclub;


import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.Statement;
public class MySQL {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/videoclub";
        String usuario = "root";
        String contraseña = "";

        try {
            // Carga el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establece la conexión
            try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
                 Statement statement = conexion.createStatement()) {

                // Copia y pega el script SQL aquí
                String scriptSQL = "CREATE TABLE ...";  // (Completa con el script completo)

                // Ejecuta el script SQL
                String[] queries = scriptSQL.split(";\n");
                for (String query : queries) {
                    statement.executeUpdate(query);
                }

                System.out.println("Tablas creadas exitosamente.");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
