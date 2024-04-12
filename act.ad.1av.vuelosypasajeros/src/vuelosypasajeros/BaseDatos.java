package vuelosypasajeros;

import java.sql.*;

public class BaseDatos {

    private static BaseDatos miInstancia = null;
    private static boolean permitirInstancianueva;
    private String cadenaConexion = "jdbc:mysql://localhost:3306/aerolinea";
    private String usuario = "root";
    private String password = "";
    private Connection conn;
    private Statement stmt;

    BaseDatos() throws Exception {
        if (!permitirInstancianueva)
            throw new Exception("No se puede crear la instancia, usa getInstance");
    }

    public static BaseDatos getInstance() {
        if (miInstancia == null) {
            permitirInstancianueva = true;
            try {
                miInstancia = new BaseDatos();
            } catch (Exception e) {
                e.printStackTrace();
            }
            permitirInstancianueva = false;
        }
        return miInstancia;
    }

    public ResultSet consultaBD(String consulta) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(cadenaConexion, usuario, password);

            stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(consulta);

            return rset;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Error al cargar el controlador MySQL");
        }
    }

    public void cerrarConsulta() throws SQLException {
        stmt.close();
        conn.close();
    }

    public String getCadenaConexion() {
        return cadenaConexion;
    }

    public void setCadenaConexion(String cadenaConexion) {
        this.cadenaConexion = cadenaConexion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }
}
