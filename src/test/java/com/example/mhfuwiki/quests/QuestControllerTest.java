package com.example.mhfuwiki.quests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(QuestController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class QuestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestService questService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllQuests() throws Exception {
        Quest testQuest = new Quest(1L, 1, "test title", "LR", "village", "Jungle", "test desc", "blango", 200, 0, 50, "none", new String[]{}, "day", false, false);
        Quest testQuest2 = new Quest(2L, 1, "test title2", "HR", "village", "Jungle", "test desc", "blango", 200, 0, 50, "none", new String[]{}, "day", false, false);

        List<Quest> quests = Arrays.asList(testQuest, testQuest2);

        when(questService.getQuests()).thenReturn(quests);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/quests/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("test title"));
    }

    @Test
    public void testAddQuest() throws Exception {
        Quest testQuest = new Quest(1L, 1, "test title", "LR", "Village", "Jungle", "test desc", "blango", 200, 0, 50, "none", new String[]{}, "day", false, false);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/quests/")
                        .content(asJsonString(testQuest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteQuestTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/quests/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void findQuestTest() throws Exception {
        Quest testQuest = new Quest(1L, 1, "test title", "LR", "village", "Jungle", "test desc", "blango", 200, 0, 50, "none", new String[]{}, "day", false, false);

        when(questService.findQuest(1L)).thenReturn(testQuest);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/quests/{id}", 1L)
                        .content(asJsonString(testQuest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("test title"));
    }

    @Test
    public void findQuestByRankAndStar() throws Exception {
        Quest testQuest = new Quest(1L, 1, "test title", "LR", "village", "Jungle", "test desc", "blango", 200, 0, 50, "none", new String[]{}, "day", false, false);
        Quest testQuest2 = new Quest(2L, 1, "test title", "HR", "village", "Jungle", "test desc", "blango", 200, 0, 50, "none", new String[]{}, "day", false, false);

        List<Quest> quests = List.of(testQuest2);
        when(questService.getQuestsForRankAndStar("HR", 1)).thenReturn(quests);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/quests/getQuestsForRankAndStar/")
                        .param("rank", "HR")
                        .param("star", "1")
                        .content(asJsonString(quests))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(2))
                .andExpect(jsonPath("$[0].title").value("test title"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
