
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        String urlMysql="jdbc:mysql://localhost/test";
        String urlH2="jdbc:h2:" + Path.of("test").toAbsolutePath().toString();
        String username =  "root";
        String password = "";

        System.out.println("Iniciando conexión con el servidor");
        Connection connection = MySQLConnection.newInstance(urlMysql, username, password);
        //Connection connection = H2Connection.newInstance(urlH2, username, password);
        String crearTablaPersona ="create table if not exists Persona(id INTEGER PRIMARY KEY AUTO_INCREMENT, nombre VARCHAR(30) NOT NULL, telefono INTEGER(9) NOT NULL)";
        String crearTablaDirecciones = "create table if not exists direcciones(\n" +
                "    id INTEGER PRIMARY KEY AUTO_INCREMENT,\n" +
                "    persona_id INTEGER NOT NULL,\n" +
                "    direccion VARCHAR(50) NOT NULL,\n" +
                "    FOREIGN KEY (persona_id) REFERENCES PERSONA(id)\n" +
                ")";
        JDBCOpearations.crearTabla(connection,crearTablaPersona);
        JDBCOpearations.crearTabla(connection,crearTablaDirecciones);


        try {
            connection.close();
        }catch (SQLException ex){
            System.err.println("No se ha podido cerrar la conexión con el servidor");
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        System.out.println("Finalizando conexión con el servidor");
    }
}
