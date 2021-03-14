package repository.client;

import database.JDBConnectionWrapper;
import model.Client;
import model.builder.ClientBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepositoryMySQL implements ClientRepository {

    private final JDBConnectionWrapper connectionWrapper;

    public ClientRepositoryMySQL(JDBConnectionWrapper connectionWrapper) {

        this.connectionWrapper = connectionWrapper;
    }
    @Override
    public List<Client> findAll() {
        Connection connection = connectionWrapper.getConnection();
        String sql = "SELECT * from client";

        List<Client> clients = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                clients.add(getClientFromResultSet(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return clients;
    }

    @Override
    public Client findById(Long id) {
        Connection connection = connectionWrapper.getConnection();
        String sql = "SELECT * from client WHERE id_client = " + id;

        Client client = null;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                client = getClientFromResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return client;
    }

    @Override
    public boolean create(Client client) {
        Connection connection = connectionWrapper.getConnection();
        String sql = "INSERT INTO client values (null, ?, ?, ?)";

        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement(sql);
            insertStatement.setString(1, client.getName());
            insertStatement.setString(2, client.getSsn());
            insertStatement.setString(3, client.getAddress());
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }


    @Override
    public boolean update(Client client) {
        Connection connection = connectionWrapper.getConnection();
        String sql = "UPDATE client SET name = ?, ssn = ? , address = ?, WHERE id_client = ?";

        try {
            PreparedStatement updateStatement = connection
                    .prepareStatement(sql);
            updateStatement.setString(1, client.getName());
            updateStatement.setString(2, client.getSsn());
            updateStatement.setString(3, client.getAddress());
            updateStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public void deleteAll() {
        Connection connection = connectionWrapper.getConnection();
        String sql = "DELETE from client WHERE id_client >= 0";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private Client getClientFromResultSet(ResultSet rs) throws SQLException {
        return new ClientBuilder()
                .setId(rs.getLong ("id_client"))
                .setName("name")
                .setSsn("ssn")
                .setAddress("password")
                .build();
    }
}
