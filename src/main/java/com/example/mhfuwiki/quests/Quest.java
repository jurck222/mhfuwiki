package com.example.mhfuwiki.quests;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table
public class Quest {
    @Id
    @SequenceGenerator(
            name = "quest_sequence",
            sequenceName = "quest_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "quest_sequence"
    )
    private Long id;
    private String title;
    private String rank;
    private String questGiverLocation;
    private String mapLocation;
    private String description;
    private String objective;
    private int reward;
    private int fee;
    private int time;
    private int star;
    private String requirements;
    private String otherMonsters;
    private String timeOfDay;
    private boolean keyQuest;
    private boolean urgentQuest;

    public Quest() {
    }

    public Quest(int star, String title, String rank, String questGiverLocation, String mapLocation, String description, String objective, int reward, int fee, int time, String requirements, String otherMonsters, String timeOfDay, boolean keyQuest, boolean urgentQuest) {
        this.title = title;
        this.rank = rank;
        this.questGiverLocation = questGiverLocation;
        this.mapLocation = mapLocation;
        this.description = description;
        this.objective = objective;
        this.reward = reward;
        this.fee = fee;
        this.time = time;
        this.requirements = requirements;
        this.otherMonsters = otherMonsters;
        this.timeOfDay = timeOfDay;
        this.keyQuest = keyQuest;
        this.urgentQuest = urgentQuest;
        this.star = star;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setQuestGiverLocation(String questGiverLocation) {
        this.questGiverLocation = questGiverLocation;
    }

    public void setMapLocation(String mapLocation) {
        this.mapLocation = mapLocation;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public void setKeyQuest(boolean keyQuest) {
        this.keyQuest = keyQuest;
    }

    public void setUrgentQuest(boolean urgentQuest) {
        this.urgentQuest = urgentQuest;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public void setOtherMonsters(String otherMonsters) {
        this.otherMonsters = otherMonsters;
    }

    public void setStar(int star) {
        this.star = star;
    }

    @Override
    public String toString() {
        return "Quest{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rank='" + rank + '\'' +
                ", questGiverLocation='" + questGiverLocation + '\'' +
                ", mapLocation='" + mapLocation + '\'' +
                ", description='" + description + '\'' +
                ", objective='" + objective + '\'' +
                ", reward=" + reward +
                ", fee=" + fee +
                ", time=" + time +
                ", star=" + star +
                ", requirements='" + requirements + '\'' +
                ", otherMonsters='" + otherMonsters + '\'' +
                ", timeOfDay='" + timeOfDay + '\'' +
                ", keyQuest=" + keyQuest +
                ", urgentQuest=" + urgentQuest +
                '}';
    }
}
