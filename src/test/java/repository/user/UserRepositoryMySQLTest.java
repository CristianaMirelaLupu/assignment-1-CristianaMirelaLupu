package repository.user;

import database.DatabaseConnectionFactory;
import database.JDBConnectionWrapper;
import model.User;
import model.builder.UserBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class UserRepositoryMySQLTest {

    private static UserRepository repository;

    @BeforeClass
    public static void setupClass() {
        JDBConnectionWrapper connectionWrapper = DatabaseConnectionFactory.getConnectionWrapper(true);
        repository = new UserRepositoryCacheDecorator(new UserRepositoryMySQL(connectionWrapper));
    }


    @Test
    public void findAll() {
        System.out.println("findAll");
        List<User> noUsers = repository.findAll();
        Assert.assertTrue(noUsers.isEmpty());
    }

    @Test
    public void findById() {
    }

    @Test
    public void create() {
    }

    @Test
    public void update() {
    }

    @Test
    public void deleteAll() {
    }
}