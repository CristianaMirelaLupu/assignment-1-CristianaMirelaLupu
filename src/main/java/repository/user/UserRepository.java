package repository.user;

import model.User;

import java.util.List;

public interface UserRepository {

    //CRUD on employees information

    List<User> findAll();
    User findById(Long id);
    boolean create(User user);
    boolean update(User user);
    void deleteAll();

}
