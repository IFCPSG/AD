package ad.t1.ifc32b.ut3;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        String urlMysql="jdbc:mysql://localhost/test";
        String urlH2="jdbc:h2:" + Path.of("test").toAbsolutePath().toString();
        String username =  "root";
        String password = "";

        System.err.println("Iniciando conexión con el servidor");
        //Connection connection = MySQLConnection.newInstance(urlMysql, username, password);
        Connection connection = H2Connection.newInstance(urlH2, username, password);
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
