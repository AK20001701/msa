package com.msa.module7.service;

import com.msa.module7.entity.SimpleEntity;
import com.msa.module7.exception.NotFoundException;
import com.msa.module7.repository.SimpleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class SimpleServiceTest {

    @MockBean
    private SimpleRepository simpleRepository;

    private SimpleService simpleService;

    private final Long ID = 7L;
    private final Integer SOME_INT = 123;
    private final String SOME_STRING = "QWE";
    private final String ANOTHER_STRING = "ASD";

    @BeforeEach
    public void init() {
        simpleService = new SimpleService(simpleRepository);
    }

    @Test
    void getSimpleEntity() {
        SimpleEntity simpleEntity = initSimpleEntity();

        when(simpleRepository.findById(ID)).thenReturn(Optional.of(simpleEntity));

        SimpleEntity result = simpleService.getSimpleEntity(ID);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(ID);
        verify(simpleRepository, times(1)).findById(ID);
    }

    @Test
    void getSimpleEntity_IfNotPresented() {
        when(simpleRepository.findById(ID)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> simpleService.getSimpleEntity(ID));
        verify(simpleRepository, times(1)).findById(ID);
    }

    @Test
    void getAllSimpleEntities() {
        SimpleEntity simpleEntity1 = initSimpleEntity();
        SimpleEntity simpleEntity2 = initSimpleEntity();
        simpleEntity2.setId(ID * 2);
        SimpleEntity simpleEntity3 = initSimpleEntity();
        simpleEntity3.setId(ID * 3);
        List<SimpleEntity> simpleEntityList = new ArrayList<>();
        simpleEntityList.add(simpleEntity1);
        simpleEntityList.add(simpleEntity2);
        simpleEntityList.add(simpleEntity3);
        when(simpleRepository.findAll()).thenReturn(simpleEntityList);

        List<SimpleEntity> result = simpleService.getAllSimpleEntities();

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(simpleEntityList.size());
    }

    @Test
    void addSimpleEntity() {
        SimpleEntity simpleEntity = initSimpleEntity();
        simpleService.addSimpleEntity(simpleEntity);
        verify(simpleRepository, times(1)).save(simpleEntity);

    }

    @Test
    void updateSimpleEntity() {
        SimpleEntity simpleEntityToChange = initSimpleEntity();
        simpleEntityToChange.setSomeInteger(SOME_INT * 2);
        simpleEntityToChange.setSomeString(SOME_STRING + ANOTHER_STRING);
        simpleEntityToChange.setAnotherString(ANOTHER_STRING + SOME_STRING);
        SimpleEntity simpleEntityFromRepo = initSimpleEntity();
        when(simpleRepository.findById(ID)).thenReturn(Optional.of(simpleEntityFromRepo));
        simpleService.updateSimpleEntity(simpleEntityToChange);
        verify(simpleRepository, times(1)).save(simpleEntityToChange);
    }

    @Test
    void deleteSimpleEntity() {
        SimpleEntity simpleEntity = initSimpleEntity();
        when(simpleRepository.findById(ID)).thenReturn(Optional.of(simpleEntity));
        simpleService.deleteSimpleEntity(ID);
        verify(simpleRepository, times(1)).delete(simpleEntity);
    }

    private SimpleEntity initSimpleEntity() {
        SimpleEntity simpleEntity = new SimpleEntity();
        simpleEntity.setId(ID);
        simpleEntity.setSomeInteger(SOME_INT);
        simpleEntity.setSomeString(SOME_STRING);
        simpleEntity.setAnotherString(ANOTHER_STRING);
        return simpleEntity;
    }
}