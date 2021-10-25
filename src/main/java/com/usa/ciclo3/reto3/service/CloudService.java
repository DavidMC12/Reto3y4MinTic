package com.usa.ciclo3.reto3.service;


import com.usa.ciclo3.reto3.model.Cloud;
import com.usa.ciclo3.reto3.repository.CloudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CloudService {

    @Autowired
    private CloudRepository cloudRepository;

    public List<Cloud> getAll(){
        return cloudRepository.getAll();
    }

    public Optional<Cloud> getCloud(int id){
        return cloudRepository.getCloud(id);
    }

    public Cloud save(Cloud cloud){
        if (cloud.getId() == null){
            return cloudRepository.save(cloud);
        }
        else {
            Optional<Cloud> tpmCloud = cloudRepository.getCloud(cloud.getId());
            if (tpmCloud.isEmpty()){
                return cloudRepository.save(cloud);
            }
            else {
                return  cloud;
            }
        }
    }

    public Cloud update(Cloud cloud){
        if (cloud.getId() != null){
            Optional<Cloud> tpmCloud = cloudRepository.getCloud(cloud.getId());
            if (!tpmCloud.isEmpty()){
                return cloudRepository.save(cloud);
            }
        }
        return cloud;
    }

    public boolean deleteCloud(int id){
        Boolean result = getCloud(id).map(cloud -> {cloudRepository.delete(cloud); return true;}).orElse(false);
        return result;
    }
}
