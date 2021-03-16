package repository.account;

import database.JDBConnectionWrapper;
import model.Account;
import model.builder.AccountBuilder;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryMySQL implements AccountRepository {
    private final JDBConnectionWrapper connectionWrapper;

    public AccountRepositoryMySQL(JDBConnectionWrapper connectionWrapper) {

        this.connectionWrapper = connectionWrapper;
    }
    @Override
    public List<Account> findAll() {
        Connection connection = connectionWrapper.getConnection();
        String sql = "SELECT * from account";

        List<Account> accounts = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                accounts.add(getAccountFromResultSet(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return accounts;
    }


    @Override
    public Account findById(Long id) {
        Connection connection = connectionWrapper.getConnection();
        String sql = "SELECT * from account WHERE id_account = " + id;

        Account account = null;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                account = getAccountFromResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return account;
    }

    @Override
    public boolean create(Account account) {
        Connection connection = connectionWrapper.getConnection();
        String sql = "INSERT INTO account values (null, ?, ?, ?, ?)";

        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement(sql);
            insertStatement.setString(1, account.getType());
            insertStatement.setDouble(2, account.getAmount());
            insertStatement.setDate(3, new java.sql.Date(account.getCreationDate().toEpochDay()));
            insertStatement.setLong(4, account.getClient().getId());
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean update(Account account) {
        Connection connection = connectionWrapper.getConnection();
        String sql = "UPDATE account SET type = ?, amount = ? , creationDate = ?, id_client = ?, WHERE id_client = ?";

        try {
            PreparedStatement updateStatement = connection
                    .prepareStatement(sql);
            updateStatement.setString(1, account.getType());
            updateStatement.setDouble(2, account.getAmount());
            updateStatement.setDate(3, new java.sql.Date(account.getCreationDate().toEpochDay()));
            updateStatement.setLong(4, account.getClient().getId());
            updateStatement.executeUpdate();
            return true;

            } catch (SQLException e) {
                return false;
            }
    }

    @Override
    public void deleteAll() {
        Connection connection = connectionWrapper.getConnection();
        String sql = "DELETE from account WHERE id_count >= 0";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private Account getAccountFromResultSet(ResultSet rs) throws SQLException {
        return new AccountBuilder()
                .setId(rs.getLong ("id_account"))
                .setType("type")
                .setAmount(rs.getFloat("amount"))
                .setCreationDate(LocalDate.ofEpochDay(rs.getDate("creationDate").getTime()))
                .build();
    }
}

