package repository.client;

import model.Client;

import java.util.List;

public interface ClientRepository {

    //add, update, view
    List<Client> findAll();
    Client findById(Long id);
    boolean create(Client client);
    boolean update(Client client);
    void deleteAll();
}
