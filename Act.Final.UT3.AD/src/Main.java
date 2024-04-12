package Act.Final.UT3.AD;

import Common.Column;
import DDL.DDL;
import DML.DML;
import Entities.Persona;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // H2 Example
        try (Connection h2Connection = DriverManager.getConnection("jdbc:h2:" + Path.of("test").toAbsolutePath().toString())) {
            String urlMysql="jdbc:mysql://localhost/test";
            String urlH2="jdbc:h2:" + Path.of("test").toAbsolutePath().toString();
            String username =  "root";
            String password = "";
            DDL h2DDL = new DDL(h2Connection);
            DML h2DML = new DML(h2Connection);

            // Create tables
            h2DDL.createTable("Persona", new Column("id", "INT", false),
                    new Column("nombre", "VARCHAR(255)", false),
                    new Column("telefono", "VARCHAR(15)", true),
                    new Column("direccion", "VARCHAR(255)", true));

            h2DDL.createTable("Direccion", new Column("id", "INT", false),
                    new Column("idPersona", "INT", false),
                    new Column("direccion", "VARCHAR(255)", true));

            // Insert data
            h2DML.insertPersona(new Persona(1, "Juan", "123456789", "Calle A", null));

            System.out.println("H2 Example: Data inserted successfully.");
        } catch (SQLException e) {
            System.err.println("H2 Database Error: " + e.getMessage());
        }

        // MySQL Example
        try (Connection mysqlConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "")) {
            DDL mysqlDDL = new DDL(mysqlConnection);
            DML mysqlDML = new DML(mysqlConnection);

            // Create tables
            mysqlDDL.createTable("Persona", new Column("id", "INT", false),
                    new Column("nombre", "VARCHAR(255)", false),
                    new Column("telefono", "VARCHAR(15)", true),
                    new Column("direccion", "VARCHAR(255)", true));

            mysqlDDL.createTable("Direccion", new Column("id", "INT", false),
                    new Column("idPersona", "INT", false),
                    new Column("direccion", "VARCHAR(255)", true));

            // Insert data
            mysqlDML.insertPersona(new Persona(1, "Juan", "123456789", "Calle A", null));

            System.out.println("MySQL Example: Data inserted successfully.");
        } catch (SQLException e) {
            System.err.println("MySQL Database Error: " + e.getMessage());
        }
    }
}
