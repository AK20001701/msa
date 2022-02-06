package com.msa.module5.controller;

import com.msa.module5.entity.SimpleEntity;
import com.msa.module5.service.SimpleService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/simple")
public class SimpleController {

    private final SimpleService simpleService;

    public SimpleController(SimpleService simpleService) {
        this.simpleService = simpleService;
    }

    @GetMapping("{id}")
    public SimpleEntity getSimpleEntity(@PathVariable Long id) {
        return simpleService.getSimpleEntity(id);
    }

    @GetMapping("/all")
    public List<SimpleEntity> getAllSimpleEntities() {
        return simpleService.getAllSimpleEntities();
    }

    @PostMapping
    public void addSimpleEntity(@RequestBody SimpleEntity simpleEntity) {
        simpleService.addSimpleEntity(simpleEntity);
    }

    @PutMapping
    public void updateSimpleEntity(@RequestBody SimpleEntity simpleEntity) {
        simpleService.updateSimpleEntity(simpleEntity);
    }

    @DeleteMapping("{id}")
    public void deleteSimpleEntity(@PathVariable Long id) {
        simpleService.deleteSimpleEntity(id);
    }

}
