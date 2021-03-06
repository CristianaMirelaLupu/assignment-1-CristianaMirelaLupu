package repository.client;

import model.Client;

import java.util.List;

public class ClientRepositoryMock implements ClientRepository {

    private final List <Client> clients;

    public ClientRepositoryMock(List<Client> clients) {
        this.clients = clients;
    }


    @Override
    public List<Client> findAll() {
        return clients;
    }

    @Override
    public Client findById(Long id) {
        return clients.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public boolean create(Client client) {
       return clients.add(client);
    }

    @Override
    public boolean update(Client client) {
       Client oldClient = findById(client.getId());
       if (oldClient == null ){
           return false;
       }
       return update(client);
    }

    @Override
    public void deleteAll() {
        clients.clear();
    }
}
