package com.example.mhfuwiki.quests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class QuestServiceTest {
    @Mock
    private QuestRepository questRepository;

    @InjectMocks
    private QuestService questService;

    @Test
    @DisplayName("Should find quest by id")
    public void testFindQuest(){
        Quest testQuest = new Quest(1L,1,"test title","LR","village","Jungle","test desc","blango",200,0,50,"none","none","day", false, false);

        when(questRepository.findById(1L)).thenReturn(Optional.of(testQuest));
        when(questRepository.existsById(1L)).thenReturn(true);

        Optional<Quest> result = Optional.ofNullable(questService.findQuest(1L));
        assertTrue(result.isPresent());
        assertEquals(testQuest, result.orElse(null));
    }
    @Test
    @DisplayName("Should find all quests")
    public void testFindAllQuests(){
        Quest testQuest = new Quest(1L,1,"test title","LR","village","Jungle","test desc","blango",200,0,50,"none","none","day", false, false);
        Quest testQuest2 = new Quest(1L,1,"test title2","LR","village","Jungle","test desc","blango",200,0,50,"none","none","day", false, false);
        Quest testQuest3 = new Quest(1L,1,"test title3","LR","village","Jungle","test desc","blango",200,0,50,"none","none","day", false, false);

        List<Quest> quests = Arrays.asList(testQuest,testQuest2,testQuest3);
        when(questRepository.findAll()).thenReturn(quests);

        List<Quest> result = questService.getQuests();
        assertFalse(result.isEmpty());
        assertEquals(3, result.size());
        assertEquals(testQuest, result.get(0));
        assertEquals(testQuest2, result.get(1));
        assertEquals(testQuest3, result.get(2));
    }

    @Test
    @DisplayName("Should find quests by rank and stars")
    public void testFindByRankAndStar(){
        Quest testQuest = new Quest(1L,1,"test title","LR","village","Jungle","test desc","blango",200,0,50,"none","none","day", false, false);
        Quest testQuest2 = new Quest(1L,1,"test title2","LR","village","Jungle","test desc","blango",200,0,50,"none","none","day", false, false);

        List<Quest> quests = Arrays.asList(testQuest,testQuest2);

        when(questRepository.findByRankAndStar("LR", 1)).thenReturn(quests);

        List<Quest> result = questService.getQuestsForRankAndStar("LR", 1);

        assertEquals(2, result.size());
        assertEquals(testQuest, result.get(0));
        assertEquals(testQuest2, result.get(1));
    }

    @Test
    @DisplayName("Should add quest")
    public void testAddQuest(){
        Quest testQuestToAdd = new Quest(1L,1,"test title","LR","Village","Jungle","test desc","blango",200,0,50,"none","none","day", false, false);

        when(questRepository.save(testQuestToAdd)).thenReturn(testQuestToAdd);

        questService.addQuest(testQuestToAdd);

        verify(questRepository).save(testQuestToAdd);
    }

    @Test
    @DisplayName("Should delete quest")
    public void testDeleteQuest(){
        Long id = 1L;
        Quest testQuest = new Quest(1L,1,"test title","LR","Village","Jungle","test desc","blango",200,0,50,"none","none","day", false, false);

        when(questRepository.findById(id)).thenReturn(Optional.of(testQuest));
        questService.deleteQuest(id);

        verify(questRepository).deleteById(id);
    }
}
