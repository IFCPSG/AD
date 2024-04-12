package ad.t1.ifc32b.ut3;

import java.sql.*;

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
    private static PreparedStatement crearSentencia(Connection connection, String query){
        try{
            return connection.prepareStatement(query);
        }catch (SQLException ex){
            System.err.println("No se ha podido crear la sentencia");
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        return null;
    }
    public static void crearTabla(Connection con, String query) {
        Statement sentencia = crearSentencia(con);
        try {
            sentencia.execute(query);
        } catch (SQLException ex) {
            System.err.println("No se ha podido ejecutar la consulta: " + query);
            System.err.println(ex.getMessage());
            System.exit(-2);
        }
    }

        public static ResultSet buscarDatos(Connection con, String query, String id, String password) {
            PreparedStatement sentencia = crearSentencia(con, query);
            try {
                sentencia.setString(1, id);
                sentencia.setString(2, password);
                System.out.println(sentencia);
                sentencia.executeQuery(query);
            } catch (SQLException ex) {
                System.err.println("No se han podido recuperar los datos");
                System.err.println(ex.getMessage());
                return null;
            }
            try {
                ResultSet resultSet = sentencia.getResultSet();
                return resultSet;
                } catch (SQLException ex) {
                    System.err.println("No se han podido recuperar los datos");
                    System.err.println(ex.getMessage());
                    return null;
            }

        }

    }




