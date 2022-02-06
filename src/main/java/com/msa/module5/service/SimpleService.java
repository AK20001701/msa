package com.msa.module5.service;

import com.msa.module5.entity.SimpleEntity;
import com.msa.module5.exception.NotFoundException;
import com.msa.module5.repository.SimpleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleService {

    private final SimpleRepository simpleRepository;

    public SimpleService(SimpleRepository simpleRepository) {
        this.simpleRepository = simpleRepository;
    }

    public SimpleEntity getSimpleEntity(Long id){
        return simpleRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public List<SimpleEntity> getAllSimpleEntities(){
        return simpleRepository.findAll();
    }

    public void addSimpleEntity(SimpleEntity simpleEntity){
        simpleRepository.save(simpleEntity);
    }

    public void updateSimpleEntity(SimpleEntity simpleEntity){
        SimpleEntity entity = getSimpleEntity(simpleEntity.getId());
        entity.setSomeString(simpleEntity.getSomeString());
        entity.setSomeInteger(simpleEntity.getSomeInteger());
        entity.setAnotherString(simpleEntity.getAnotherString());
        simpleRepository.save(entity);
    }

    public void deleteSimpleEntity(Long id){
        SimpleEntity entity = getSimpleEntity(id);
        simpleRepository.delete(entity);
    }
}
