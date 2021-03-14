package model;

import java.time.LocalDate;

public class Account {

    private Long id;
    private String type;
    private double amount;
    private LocalDate creationDate;
    private Client client;

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Client getClient() {
        return client;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setClient(Client client){
        this.client = client;
    }

}

