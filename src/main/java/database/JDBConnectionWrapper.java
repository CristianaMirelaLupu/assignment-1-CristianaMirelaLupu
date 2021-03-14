package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBConnectionWrapper {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/";

    private static final String USER = "root";
    private static final String PASS = "root";
    private static final int TIMEOUT = 5;

    private Connection connection;

    public JDBConnectionWrapper(String schema) {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL + schema, USER, PASS);
            createTables();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTables() throws SQLException {
        Statement statement = connection.createStatement();

        String sql_user = "CREATE TABLE IF NOT EXISTS user (" +
                "  id_user int(11) NOT NULL AUTO_INCREMENT," +
                "  name varchar(500) NOT NULL," +
                "  username varchar(500) NOT NULL," +
                "  password varchar(500) NOT NULL," +
                "  role varchar(500) NOT NULL," +
                "  PRIMARY KEY (id_user)," +
                "  UNIQUE KEY id_UNIQUE (id_user)" +
                ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";
        statement.execute(sql_user);

        String sql_client = "CREATE TABLE IF NOT EXISTS client (" +
                "  id_client int(11) NOT NULL AUTO_INCREMENT," +
                "  name varchar(500) NOT NULL," +
                "  ssn varchar(500) NOT NULL," +
                "  address varchar(500) NOT NULL," +
                "  PRIMARY KEY (id_client)," +
                "  UNIQUE KEY id_UNIQUE (id_client)" +
                ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";
        statement.execute(sql_client);

        String sql_account = "CREATE TABLE IF NOT EXISTS account (" +
                "  id_account int(11) NOT NULL AUTO_INCREMENT," +
                "  type varchar(500) NOT NULL," +
                "  amount double (11) NOT NULL," +
                "  creationDate datetime DEFAULT NULL," +
                "  publishedDate datetime DEFAULT NULL," +
                "  PRIMARY KEY (id_account)," +
                "  UNIQUE KEY id_UNIQUE (id_account)" +
                "  id_client int(11) NOT NULL,"+
                "CONSTRAINT FK_ID FOREIGN KEY (id_client) REFERENCES client (id_client)"+
                ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";
        statement.execute(sql_account);
    }

    public boolean testConnection() throws SQLException {
        return connection.isValid(TIMEOUT);
    }

    public Connection getConnection() {
        return connection;
    }

}