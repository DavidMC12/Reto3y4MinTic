package com.usa.ciclo3.reto3.service;

import com.usa.ciclo3.reto3.model.Message;
import com.usa.ciclo3.reto3.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll(){
        return messageRepository.getAll();
    }

    public Optional<Message> getMessage(int idMessage){
        return messageRepository.getMessage(idMessage);
    }

    public Message save(Message message) {
        if (message.getIdMessage() == null){
            return messageRepository.save(message);
        }
        else{
            Optional<Message> tpmMessage = messageRepository.getMessage(message.getIdMessage());
            if (tpmMessage.isEmpty()) {
                return messageRepository.save(message);
            }
            else{
                return  message;
            }
        }

    }

    public Message update(Message message) {
        if (message.getIdMessage() != null){
            Optional<Message> tpmMessage = messageRepository.getMessage(message.getIdMessage());
            if (!tpmMessage.isEmpty()) {
                if (message.getMessageText()!=null) {
                    tpmMessage.get().setMessageText(message.getMessageText());
                }
                if (message.getCloud()!=null){
                    tpmMessage.get().setCloud(message.getCloud());
                }
                if (message.getClient()!=null){
                    tpmMessage.get().setClient(message.getClient());
                }
                return messageRepository.save(tpmMessage.get());
            }
            else{
                return message;
            }
        }
        else{
            return  message;
        }
    }

    public  boolean deleteMessage(int id){
    Boolean result = getMessage(id).map(message -> {
        messageRepository.delete(message);
        return true;
    }).orElse(false);
    return  result;
    }
}
