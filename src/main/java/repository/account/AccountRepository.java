package repository.account;

import model.Account;

import java.util.List;

public interface AccountRepository {

    //create, update, delete, view
    List<Account> findAll();
    Account findById(Long id);
    boolean create(Account account);
    boolean update(Account account);
    void deleteAll();
}
