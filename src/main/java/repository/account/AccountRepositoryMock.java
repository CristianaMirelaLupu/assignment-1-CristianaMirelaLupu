package repository.account;

import model.Account;

import java.util.List;

public class AccountRepositoryMock implements AccountRepository {

    private final List<Account> accounts;

    public AccountRepositoryMock(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public List<Account> findAll() {
        return accounts;
    }

    @Override
    public Account findById(Long id) {
        return accounts.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public boolean create(Account account) {
        return accounts.add(account);
    }

    @Override
    public boolean update(Account account) {
        return false;
    }

    @Override
    public void deleteAll() {
        accounts.clear();

    }
}
