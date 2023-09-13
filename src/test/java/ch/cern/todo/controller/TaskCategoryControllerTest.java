package ch.cern.todo.controller;

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

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TaskCategoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void testGetAllTaskCategory() throws Exception {
        mvc.perform(get("/taskCategory"))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * Get all the taskCategory to get one existing taskCategory
     */
    @Test
    void testGetOneTaskCategory() throws Exception {
        MvcResult resultAll = mvc.perform(get("/taskCategory"))
                .andExpect(status().isOk())
                .andReturn();

        String content = resultAll.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        List<TaskCategory> taskCategories = objectMapper.readValue(content, new TypeReference<>() {});
        long categoryIdFirst = taskCategories.get(0).getCategoryId();

        mvc.perform(get("/taskCategory/"+categoryIdFirst))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testNewTaskCategory() throws Exception {
        TaskCategory tc1 = new TaskCategory("AddedCategory", "AddedCategoryDescription");
        mvc.perform(post("/taskCategory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(tc1)))
                .andExpect(status().isOk())
                .andReturn();
    }

    private String asJsonString(TaskCategory tc1) {
        try {
            return new ObjectMapper().writeValueAsString(tc1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Get all the taskCategory to replace one existing taskCategory
     */
    @Test
    void testReplaceTaskCategory() throws Exception {
        MvcResult resultAll = mvc.perform(get("/taskCategory"))
                .andExpect(status().isOk())
                .andReturn();

        String content = resultAll.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        List<TaskCategory> taskCategories = objectMapper.readValue(content, new TypeReference<>() {});
        long categoryIdFirst = taskCategories.get(0).getCategoryId();


        TaskCategory tc1 = new TaskCategory("PuttedCategory", "PuttedCategoryDescription");
        mvc.perform(put("/taskCategory/"+categoryIdFirst)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(tc1)))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * Get all the taskCategory to delete one existing taskCategory
     */
    @Test
    void testDeleteTaskCategory() throws Exception {
        MvcResult resultAll = mvc.perform(get("/taskCategory"))
                .andExpect(status().isOk())
                .andReturn();

        String content = resultAll.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        List<TaskCategory> taskCategories = objectMapper.readValue(content, new TypeReference<>() {});
        long categoryIdFirst = taskCategories.get(0).getCategoryId();

        mvc.perform(delete("/taskCategory/"+categoryIdFirst))
                .andExpect(status().isOk())
                .andReturn();
    }
}