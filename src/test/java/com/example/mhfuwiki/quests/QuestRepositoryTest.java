package com.example.mhfuwiki.quests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class QuestRepositoryTest {

    @Autowired
    private QuestRepository questRepository;

    @Test
    @DisplayName("Should save a quest")
    public void testAddQuest(){
        Quest testQuestToAdd = new Quest(1L,1,"test title","LR","Village","Jungle","test desc","blango",200,0,50,"none","none","day", false, false);

        Quest savedQuest = questRepository.save(testQuestToAdd);

        assertEquals("test title", savedQuest.getTitle());
        assertEquals("LR", savedQuest.getRank());
    }

    @Test
    @DisplayName("Should find a quest by id")
    public void testFindQuestById(){
        Quest testQuest = new Quest(1L,1,"test title","LR","Village","Jungle","test desc","blango",200,0,50,"none","none","day", false, false);
        Quest savedQuest = questRepository.save(testQuest);

        Optional<Quest> foundQuest = questRepository.findById(savedQuest.getId());

        assertTrue(foundQuest.isPresent());
        assertEquals(savedQuest, foundQuest.orElse(null));
    }

    @Test
    public void testDeleteById() {
        Quest testQuestToSave = new Quest(1L,1,"test title","LR","Village","Jungle","test desc","blango",200,0,50,"none","none","day", false, false);
        Quest savedQuest = questRepository.save(testQuestToSave);

        questRepository.deleteById(savedQuest.getId());

        assertFalse(questRepository.findById(savedQuest.getId()).isPresent());
    }

    @Test
    @DisplayName("Should find quests by rank and stars")
    public void testFindByRankAndStars(){
        Quest testQuest = new Quest(1L,1,"test title","LR","village","Jungle","test desc","blango",200,0,50,"none","none","day", false, false);
        Quest testQuest2 = new Quest(1L,1,"test title2","HR","village","Jungle","test desc","blango",200,0,50,"none","none","day", false, false);

        List<Quest> quests = List.of(testQuest);

        questRepository.deleteAll();

        Quest savedQuest = questRepository.save(testQuest);
        Quest savedQuest2 = questRepository.save(testQuest2);

        List<Quest> foundQuests = questRepository.findByRankAndStar("LR", 1);

        //assertEquals(1, foundQuests.size());
        assertEquals("test title", foundQuests.get(0).getTitle());
    }

    @Test
    @DisplayName("Should get all quests")
    public void testGetQuests(){
        Quest testQuest = new Quest(1L,1,"test title","LR","village","Jungle","test desc","blango",200,0,50,"none","none","day", false, false);
        Quest testQuest2 = new Quest(1L,1,"test title2","HR","village","Jungle","test desc","blango",200,0,50,"none","none","day", false, false);

        questRepository.deleteAll();

        Quest savedQuest = questRepository.save(testQuest);
        Quest savedQuest2 = questRepository.save(testQuest2);

        List<Quest> foundQuests = questRepository.findAll();
        assertEquals(2, foundQuests.size());
        assertEquals("test title", foundQuests.get(0).getTitle());
        assertEquals("test title2", foundQuests.get(1).getTitle());
    }
}
