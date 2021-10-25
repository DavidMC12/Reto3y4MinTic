package com.usa.ciclo3.reto3.controller;

import com.usa.ciclo3.reto3.model.Category;
import com.usa.ciclo3.reto3.model.Client;
import com.usa.ciclo3.reto3.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/all")
    public List<Client> getClient(){
       return clientService.getAll();
    }

    @GetMapping("/{idClient}")
    public Optional<Client> getClient(@PathVariable("idClient")int id){
        return clientService.getClient(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Client save(@RequestBody Client client){
        return clientService.save(client);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Client update(@RequestBody Client client){
        return clientService.update(client);
    }

    @DeleteMapping("/{idClient}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable ("idClient") int id){
        return clientService.deleteClient(id);
    }
}
