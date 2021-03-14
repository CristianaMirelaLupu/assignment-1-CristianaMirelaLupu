package model.builder;

import model.User;

public class UserBuilder {

    private final User user;

    public UserBuilder()
    {
        user = new User();
    }

    public UserBuilder setId(Long id){
        user.setId(id);
        return this;
    }

    public UserBuilder setName(String name){
        user.setName(name);
        return this;
    }

    public UserBuilder setUsername(String username){
        user.setUsername(username);
        return this;
    }

    public UserBuilder setPassword(String password){
        user.setPassword(password);
        return this;
    }
    public UserBuilder setRole (String role){
        user.setRole(role);
        return this;
    }

    public User build ()
    {
        return user;
    }
}
