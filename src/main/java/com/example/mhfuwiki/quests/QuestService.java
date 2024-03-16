package com.example.mhfuwiki.quests;

import com.example.mhfuwiki.shared.Location;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class QuestService {
    private final QuestRepository questRepository;
    private final String[] questRanks = {"LR", "HR", "GR"};
    private final String[] questGiverLocations = {"Village", "Guild"};

    private final String[] dayTimes = {"day", "night"};

    private final int minStar = 1;
    private final int maxStar = 9;

    public QuestService(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    public Quest addQuest(Quest quest) {
        try {
            if (quest.getTitle() == null) {
                throw new IllegalStateException("Title is missing");
            }
            if (quest.getDescription() == null) {
                throw new IllegalStateException("Description is missing");
            }
            if (quest.getStar() < minStar || quest.getStar() > maxStar) {
                throw new IllegalStateException("Stars is missing");
            }
            if (!Arrays.asList(questRanks).contains(quest.getRank())) {
                System.out.println(quest.getRank());
                throw new IllegalStateException("Rank is missing");
            }
            if (!Arrays.asList(questGiverLocations).contains(quest.getQuestGiverLocation())) {
                throw new IllegalStateException("Quest giver location is missing");
            }
            if (!Arrays.asList(Location.MAP_LOCATIONS).contains(quest.getMapLocation())) {
                throw new IllegalStateException("Map location is missing");
            }
            if (quest.getObjective() == null) {
                throw new IllegalStateException("Objective is missing");
            }
            if (quest.getReward() == 0) {
                throw new IllegalStateException("Reward is missing");
            }
            if (quest.getTime() == 0) {
                throw new IllegalStateException("Time is missing");
            }
            if (!Arrays.asList(dayTimes).contains(quest.getTimeOfDay())) {
                throw new IllegalStateException("Time of day is missing");
            }

            questRepository.save(quest);
            return quest;
        } catch (Exception e) {
            throw new IllegalStateException("There was a problem with your request");
        }
    }

    public List<Quest> getQuests() {
        return questRepository.findAll();
    }

    public void deleteQuest(long id) {
        questRepository.findById(id).orElseThrow(() -> new IllegalStateException("Quest with id " + id + " does not exist"));
        questRepository.deleteById(id);
    }

    public Quest findQuest(Long id) {
        boolean exists = questRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Quest with id " + id + " does not exist");
        }
        Optional<Quest> optional = questRepository.findById(id);
        if (optional.isEmpty()) {
            throw new IllegalStateException("Quest with id " + id + " does not exist");
        }
        return optional.get();
    }

    public List<Quest> getQuestsForRankAndStar(String rank, int star) {
        return questRepository.findByRankAndStar(rank, star);
    }
}
