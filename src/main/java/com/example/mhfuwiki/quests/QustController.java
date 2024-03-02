package com.example.mhfuwiki.quests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/quests")
@CrossOrigin(origins = "http://localhost:4200")
public class QustController {
    private final QuestService questService;

    public QustController(QuestService questService) {
        this.questService = questService;
    }

    @PostMapping("/")
    @ResponseBody()
    public ResponseEntity<String> addQuest(@RequestBody Quest quest){
        return questService.addQuest(quest);
    }

    @GetMapping("/")
    public List<Quest> getQuests(){
        return questService.getQuests();
    }

    @GetMapping("/{id}")
    public Quest findQuest(@PathVariable long id){
        try {
            return questService.findQuest(id);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuest(@PathVariable long id){
        return questService.deleteTodo(id);
    }



}
