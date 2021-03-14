package repository.user;

import model.User;

import java.util.List;

public class UserRepositoryMock implements UserRepository {

    private final List <User> users;

    public UserRepositoryMock(List<User> users) {
        this.users = users;
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findById(Long id) {
        return users.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public boolean create(User user) {
        return users.add(user);
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public void deleteAll() {

    }
}
