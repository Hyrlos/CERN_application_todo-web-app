package ch.cern.todo.controller;

import ch.cern.todo.entity.Task;
import ch.cern.todo.entity.TaskCategory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getAllTask() throws Exception {
        mvc.perform(get("/task"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getOneTask() throws Exception {
        long firstTaskId = getFirstTask().get(0).getTaskId();

        mvc.perform(get("/task/" + firstTaskId))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void newTask() throws Exception {
        TaskCategory tc = getFirstTaskCategory().get(0);
        Date randomFutureDate = new Date((long) ((new Date().getTime()) + Math.random() * 100000000));

        Task t1 = new Task("AddedTask", "Added Task Description",
                randomFutureDate, tc);
        mvc.perform(post("/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(t1)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void replaceTask() throws Exception {
        Task firstTask = getFirstTask().get(0);
        firstTask.setTaskName("PuttedTask");
        firstTask.setTaskDescription("Putted Task Description");


        mvc.perform(post("/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(firstTask)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void deleteTask() throws Exception {
        long firstTaskId = getFirstTask().get(0).getTaskId();

        mvc.perform(delete("/task/" + firstTaskId))
                .andExpect(status().isOk())
                .andReturn();
    }


    private String asJsonString(Task t1) {
        try {
            return new ObjectMapper().writeValueAsString(t1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Return the list of all TaskCategory
     */
    private List<TaskCategory> getFirstTaskCategory() throws Exception {

        MvcResult resultAll = mvc.perform(get("/taskCategory"))
                .andExpect(status().isOk())
                .andReturn();

        String content = resultAll.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(content, new TypeReference<>() {
        });
    }

    /**
     * Return the list of all TaskCategory
     */
    private List<Task> getFirstTask() throws Exception {

        MvcResult resultAll = mvc.perform(get("/task"))
                .andExpect(status().isOk())
                .andReturn();

        String content = resultAll.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(content, new TypeReference<>() {
        });
    }
}