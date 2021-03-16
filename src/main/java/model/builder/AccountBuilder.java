package model.builder;

import model.Account;
import model.Client;

import java.time.LocalDate;

public class AccountBuilder {
    private final Account account;

    public AccountBuilder()
    {
       account = new Account();
    }

    public AccountBuilder setId(Long id){
        account.setId(id);
        return this;
    }

    public AccountBuilder setType(String type){
        account.setType(type);
        return this;
    }

    public AccountBuilder setAmount(float amount){
        account.setAmount(amount);
        return this;
    }

    public AccountBuilder setCreationDate (LocalDate creationDate){
        account.setCreationDate(creationDate);
        return this;
    }

    public AccountBuilder setClient (Client client) {
        account.setClient(client);
        return this;
    }

    public Account build ()
    {
        return account;
    }

}

