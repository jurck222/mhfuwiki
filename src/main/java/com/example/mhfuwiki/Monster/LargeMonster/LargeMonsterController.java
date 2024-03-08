package com.example.mhfuwiki.Monster.LargeMonster;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/largeMonsters")
@CrossOrigin(origins = "http://localhost:4200")
public class LargeMonsterController {

    private final LargeMonsterService largeMonsterService;

    public LargeMonsterController(LargeMonsterService largeMonsterService) {
        this.largeMonsterService = largeMonsterService;
    }

    @GetMapping("/")
    public List<LargeMonster> getLargeMonster() {
        return largeMonsterService.getLargeMonsters();
    }

    @GetMapping("/getByName/")
    public LargeMonster findByName(@RequestParam String name) {
        return largeMonsterService.findByName(name);
    }

    @PostMapping("/")
    public LargeMonster addMonster(@RequestBody LargeMonster largeMonster) {
        return largeMonsterService.addMonster(largeMonster);
    }

    @DeleteMapping("/{id}")
    public void deleteMonster(@PathVariable Long id) {
        largeMonsterService.deleteMonster(id);
    }
}
