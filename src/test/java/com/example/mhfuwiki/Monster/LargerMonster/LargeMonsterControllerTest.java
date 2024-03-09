package com.example.mhfuwiki.Monster.LargerMonster;

import com.example.mhfuwiki.Monster.LargeMonster.LargeMonster;
import com.example.mhfuwiki.Monster.LargeMonster.LargeMonsterController;
import com.example.mhfuwiki.Monster.LargeMonster.LargeMonsterService;
import com.example.mhfuwiki.shared.Break;
import com.example.mhfuwiki.shared.Element;
import com.example.mhfuwiki.shared.Location;
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

import static com.example.mhfuwiki.quests.QuestControllerTest.asJsonString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LargeMonsterController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class LargeMonsterControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LargeMonsterService largeMonsterService;
    @Autowired
    private ObjectMapper objectMapper;

    private LargeMonster testMonster = new LargeMonster(1L, "Teostra", "Elder dragon",
            false, new String[]{Element.FIRE}, new String[]{}, new String[]{Element.DRAGON, Element.WATER},
            new String[]{Break.HORN, Break.WINGS, Break.TAIL}, 2610, 1479,
            new String[]{Location.DESERT, Location.OLD_VOLCANO, Location.TOWN, Location.VOLCANO, Location.SWAMP, Location.TOWER});

    private LargeMonster testMonster2 = new LargeMonster(2L, "Lunastra", "Elder dragon",
            false, new String[]{Element.FIRE}, new String[]{}, new String[]{Element.DRAGON, Element.WATER},
            new String[]{Break.HORN, Break.WINGS, Break.TAIL}, 2610, 1479,
            new String[]{Location.DESERT, Location.OLD_VOLCANO, Location.TOWN, Location.VOLCANO, Location.SWAMP, Location.TOWER});

    private LargeMonster testMonster3 = new LargeMonster(3L, "Fatalis", "Elder dragon",
            false, new String[]{Element.FIRE}, new String[]{}, new String[]{Element.DRAGON, Element.WATER},
            new String[]{Break.HORN, Break.WINGS, Break.TAIL}, 2610, 1479,
            new String[]{Location.DESERT, Location.OLD_VOLCANO, Location.TOWN, Location.VOLCANO, Location.SWAMP, Location.TOWER});

    @Test
    public void testGetAllLargeMonsters() throws Exception {
        List<LargeMonster> largeMonsters = Arrays.asList(testMonster, testMonster2);

        when(largeMonsterService.getLargeMonsters()).thenReturn(largeMonsters);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/largeMonsters/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(testMonster.getName()))
                .andExpect(jsonPath("$[0].species").value(testMonster.getSpecies()))
                .andExpect(jsonPath("$[1].name").value(testMonster2.getName()))
                .andExpect(jsonPath("$[1].species").value(testMonster2.getSpecies()));
    }

    @Test
    public void testAddLargeMonster() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/largeMonsters/")
                        .content(asJsonString(testMonster))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteLargeMonsterTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/largeMonsters/{id}", testMonster.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void findLargeMonsterByName() throws Exception {
        LargeMonster largeMonsters = testMonster;
        when(largeMonsterService.findByName("Teostra")).thenReturn(largeMonsters);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/largeMonsters/getByName/")
                        .param("name", "Teostra")
                        .content(asJsonString(largeMonsters))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Teostra"));
    }
}
