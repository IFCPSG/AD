
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCOpearations {
    private JDBCOpearations(){

    }
    private static Statement crearSentencia(Connection connection){
        try{
            return connection.createStatement();
        }catch (SQLException ex){
            System.err.println("No se ha podido crear la sentencia");
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        return null;

    }

    public static void crearTabla(Connection con, String query){
        Statement sentencia = crearSentencia(con);
        try {
            sentencia.execute(query);
        }catch (SQLException ex){
            System.err.println("No se ha podido ejecutar la consulta: " +query);
            System.err.println(ex.getMessage());
            System.exit(-2);
        }

    }
}



