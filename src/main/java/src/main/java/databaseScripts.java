package src.main.java;

import java.sql.*;


public class databaseScripts {
    /**
     * Connect to a sample database
     */
    public static void connectToDatatabase() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:/Users/cooperbrown/Desktop/Projects/Ostrowskia/src/main/resources/data_db.sqlite";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    // Takes a String Name and creates a new database with it
    public static void createNewDatabase(String fileName) {

        String url = "jdbc:sqlite:/Users/cooperbrown/Desktop/Projects/Ostrowskia/src/main/resources/" + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void createNewUserDatabaseTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:/Users/cooperbrown/Desktop/Projects/Ostrowskia/src/main/resources/data_db.sqlite";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS usrTable (\n"
                + "	usrID integer PRIMARY KEY UNIQUE,\n"
                + "	usrName text NOT NULL UNIQUE,\n"
                + "	usrPassword text NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createNewFileDatabaseTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:/Users/cooperbrown/Desktop/Projects/Ostrowskia/src/main/resources/data_db.sqlite";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS usrTable (\n"
                + "	fileID integer PRIMARY KEY UNIQUE,\n"
                + "	usrName text NOT NULL UNIQUE,\n"
                + "	usrPassword text NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        connectToDatatabase();
    }
}
