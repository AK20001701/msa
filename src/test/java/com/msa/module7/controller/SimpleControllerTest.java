package com.msa.module7.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa.module7.entity.SimpleEntity;
import com.msa.module7.service.SimpleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class SimpleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SimpleService simpleService;

    private final Long ID = 7L;
    private final Integer SOME_INT = 123;
    private final String SOME_STRING = "QWE";
    private final String ANOTHER_STRING = "ASD";

    @Test
    void getSimpleEntity() throws Exception {
        SimpleEntity simpleEntity = initSimpleEntity();
        when(simpleService.getSimpleEntity(1L)).thenReturn(simpleEntity);
        mockMvc.perform(get("/simple/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content()
                        .json("{'id':7,'someString':'QWE','someInteger':123,'anotherString':'ASD'}"));
        verify(simpleService).getSimpleEntity(1L);
    }

    @Test
    void getAllSimpleEntities() throws Exception {
        SimpleEntity simpleEntity1 = initSimpleEntity();
        SimpleEntity simpleEntity2 = initSimpleEntity();
        simpleEntity2.setId(ID * 2);
        SimpleEntity simpleEntity3 = initSimpleEntity();
        simpleEntity3.setId(ID * 3);
        List<SimpleEntity> simpleEntityList = new ArrayList<>();
        simpleEntityList.add(simpleEntity1);
        simpleEntityList.add(simpleEntity2);
        simpleEntityList.add(simpleEntity3);
        when(simpleService.getAllSimpleEntities()).thenReturn(simpleEntityList);
        mockMvc.perform(get("/simple/all"))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)));
        verify(simpleService).getAllSimpleEntities();
    }

    @Test
    void addSimpleEntity() throws Exception {
        SimpleEntity simpleEntity = initSimpleEntity();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(simpleEntity);

        mockMvc.perform(post("/simple").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(simpleService).addSimpleEntity(simpleEntity);
    }

    @Test
    void updateSimpleEntity() throws Exception {
        SimpleEntity simpleEntity = initSimpleEntity();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(simpleEntity);
        mockMvc.perform(put("/simple").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(simpleService).updateSimpleEntity(simpleEntity);
    }

    @Test
    void deleteSimpleEntity() throws Exception {
        mockMvc.perform(delete("/simple/1"))
                .andExpect(status().isOk());
        verify(simpleService).deleteSimpleEntity(1L);
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