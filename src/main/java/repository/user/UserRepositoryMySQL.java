package repository.user;

import database.JDBConnectionWrapper;
import model.User;
import model.builder.UserBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserRepositoryMySQL implements UserRepository {

    private final JDBConnectionWrapper connectionWrapper;

    public UserRepositoryMySQL(JDBConnectionWrapper connectionWrapper) {

        this.connectionWrapper = connectionWrapper;
    }

    @Override
    public List<User> findAll() {
        Connection connection = connectionWrapper.getConnection();
        String sql = "SELECT * from user";

        List<User> users = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                users.add(getUserFromResultSet(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }


    @Override
    public User findById(Long id) {
        Connection connection = connectionWrapper.getConnection();
        String sql = "SELECT * from book WHERE id_user = " + id;

        User user = null;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                user = getUserFromResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean create(User user) {
        Connection connection = connectionWrapper.getConnection();
        String sql = "INSERT INTO user values (null, ?, ?, ?, ?)";

        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement(sql);
            insertStatement.setString(1, user.getName());
            insertStatement.setString(2, user.getUsername());
            insertStatement.setString(3, user.getPassword());
            insertStatement.setString(4, user.getRole());
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean update(User user) {
        Connection connection = connectionWrapper.getConnection();
        String sql = "UPDATE user SET name = ?, username = ? , password = ?, role = ?, WHERE id_user = ?";

        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement(sql);
            insertStatement.setString(1, user.getName());
            insertStatement.setString(2, user.getUsername());
            insertStatement.setString(3, user.getPassword());
            insertStatement.setString(4, user.getRole());
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public void deleteAll() {
        Connection connection = connectionWrapper.getConnection();
        String sql = "DELETE from user WHERE id_user >= 0";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private User getUserFromResultSet(ResultSet rs) throws SQLException {
        return new UserBuilder()
                .setId(rs.getLong ("id_user"))
                .setName("name")
                .setUsername("username")
                .setPassword("password")
                .setRole("role")
                .build();
    }
}
