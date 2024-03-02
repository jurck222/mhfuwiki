package com.example.mhfuwiki.quests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class QuestService {
    private final QuestRepository questRepository;
    private final String[] questRanks = {"LR","HR","GR"};
    private final String[] questGiverLocations = {"Village", "Guild"};
    private final String[] mapLocations = {"Snowy Mountains", "Jungle", "Desert", "Swamp", "Volcano", "Great Forest", "Forest & Hills",
            "Old Jungle", "Old Desert", "Old Swamp", "Old Volcano", "Fortress", "Town", "Tower", "Tower 2", "Tower 3", "Castle Schrade",
            "Battleground", "Arena", "Arena 2", "Great Arena", "Snowy Mountains Peak"};
    private final String[] dayTimes = {"day", "night"};

    private final int minStar = 1;
    private final int maxStar = 9;

    public QuestService(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    public ResponseEntity<String> addQuest(Quest quest){
        try {
            if(quest.getTitle() == null){
                return ResponseEntity.badRequest().body("{\"error\": \"Name is a required field.\"}");
            }
            if(quest.getDescription() == null){
                return ResponseEntity.badRequest().body("{\"error\": \"Description is a required field.\"}");
            }
            if(quest.getStar() < minStar || quest.getStar() > maxStar){
                return ResponseEntity.badRequest().body("{\"error\": \"Bad entry for quest star.\"}");
            }
            if(!Arrays.asList(questRanks).contains(quest.getRank())){
                System.out.println(quest.getRank());
                return ResponseEntity.badRequest().body("{\"error\": \"Bad entry for quest rank.\"}");
            }
            if(!Arrays.asList(questGiverLocations).contains(quest.getQuestGiverLocation())){
                return ResponseEntity.badRequest().body("{\"error\": \"Bad entry for quest giver location.\"}");
            }
            if(!Arrays.asList(mapLocations).contains(quest.getMapLocation())){
                return ResponseEntity.badRequest().body("{\"error\": \"Bad entry for quest map location.\"}");
            }
            if(quest.getObjective() == null){
                return ResponseEntity.badRequest().body("{\"error\": \"Objective is a required field.\"}");
            }
            if(quest.getReward() == 0){
                return ResponseEntity.badRequest().body("{\"error\": \"Reward is a required field.\"}");
            }
            if(quest.getTime() == 0){
                return ResponseEntity.badRequest().body("{\"error\": \"Time is a required field.\"}");
            }
            if(!Arrays.asList(dayTimes).contains(quest.getTimeOfDay())){
                return ResponseEntity.badRequest().body("{\"error\": \"Bad entry for time of day.\"}");
            }

            questRepository.save(quest);
            return ResponseEntity.ok().body("{\"message\": \"Quest added successfully.\"}");
        }catch(Exception e){
            return ResponseEntity.ok().body("{\"error\":\"There was a problem with your request try again later.\"}");
        }
    }

    public List<Quest> getQuests(){
        return questRepository.findAll();
    }

    public ResponseEntity<String> deleteTodo(long id) {
        try {
            questRepository.findById(id).orElseThrow(() -> new IllegalStateException("Todo with id "+id+" does not exist"));
            questRepository.deleteById(id);
            return ResponseEntity.ok().body("{\"message\":\"Quest with id "+id+" successfully deleted\"}");
        }catch(Exception e){
            return ResponseEntity.ok().body("{\"error\":\"Quest with id "+id+" does not exist\"}");
        }
    }

    public Quest findQuest(Long id) {
        boolean exists = questRepository.existsById(id);
        if (!exists){
            throw new IllegalStateException("Quest with id "+id+" does not exist");
        }
        Optional<Quest> optional = questRepository.findById(id);
        if(optional.isEmpty()){
            throw new IllegalStateException("Quest with id "+id+" does not exist");
        }
        return optional.get();
    }

    public List<Quest> getQuestsForRankAndStar(String rank, int star){
        return questRepository.findByRankAndStar(rank,star);
    }
}
