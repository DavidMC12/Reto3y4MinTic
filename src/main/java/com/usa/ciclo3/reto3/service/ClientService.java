package com.usa.ciclo3.reto3.service;

import com.usa.ciclo3.reto3.model.Client;
import com.usa.ciclo3.reto3.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll(){
        return clientRepository.getAll();
    }

    public Optional<Client> getClient(int id){
        return clientRepository.getClient(id);
    }

    public Client save(Client client){
        if (client.getIdClient() == null){
            return clientRepository.save(client);
        }
        else {
            Optional<Client> tpmClient = clientRepository.getClient(client.getIdClient());
            if (tpmClient.isEmpty()){
                return clientRepository.save(client);
            }
            else {
                return client;
            }
        }
    }

    public Client update(Client client){
        if (client.getIdClient() != null){
            Optional<Client> tpmClient = clientRepository.getClient(client.getIdClient());
            if (!tpmClient.isEmpty()){
                if (client.getEmail()!=null){
                    tpmClient.get().setEmail(client.getEmail());
                }
                if (client.getPassword()!=null){
                    tpmClient.get().setPassword(client.getPassword());
                }
                if (client.getName()!=null){
                    tpmClient.get().setName(client.getName());
                }
                if (client.getAge()!=null){
                    tpmClient.get().setAge(client.getAge());
                }
                if (client.getMessages()!=null){
                    tpmClient.get().setMessages(client.getMessages());
                }
                if (client.getReservations()!=null){
                    tpmClient.get().setReservations(client.getReservations());
                }
                return clientRepository.save(tpmClient.get());
            }
            else {
                return client;
            }
        }
        else {
            return client;
        }
    }

    public boolean deleteClient(int id){
        Boolean result = getClient(id).map(client -> {clientRepository.delete(client); return true;}).orElse(false);
        return result;
    }
}
