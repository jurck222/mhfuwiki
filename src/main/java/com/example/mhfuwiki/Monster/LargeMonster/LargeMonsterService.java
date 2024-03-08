package com.example.mhfuwiki.Monster.LargeMonster;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LargeMonsterService {
    private final LargeMonsterRepository largeMonsterRepository;

    public LargeMonsterService(LargeMonsterRepository largeMonsterRepository) {
        this.largeMonsterRepository = largeMonsterRepository;
    }

    public List<LargeMonster> getLargeMonsters() {
        return largeMonsterRepository.findAll();
    }

    public LargeMonster findByName(String name) {
        LargeMonster monster = largeMonsterRepository.findByName(name);
        if (monster == null) {
            throw new IllegalStateException("Monster named " + name + " doesnt exist");
        }
        return monster;
    }

    public LargeMonster addMonster(LargeMonster largeMonster) {
        boolean valuesPresent = areAllFieldsPresent(largeMonster);
        LargeMonster monster = largeMonsterRepository.findByName(largeMonster.getName());
        if (!valuesPresent) {
            throw new IllegalStateException("Entered values are wrong");
        } else if (monster != null) {
            throw new IllegalStateException("Monster with name " + largeMonster.getName() + " already exists");
        } else {
            largeMonsterRepository.save(largeMonster);
        }
        return largeMonster;
    }

    public void deleteMonster(Long id) {
        boolean exists = largeMonsterRepository.existsById(id);
        if (exists) {
            largeMonsterRepository.deleteById(id);
        } else {
            throw new IllegalStateException("Monster with id " + id + " doesnt exist");
        }
    }

    private boolean areAllFieldsPresent(LargeMonster monster) {
        return monster.getName() != null &&
                monster.getSpecies() != null &&
                monster.getElements() != null &&
                monster.getAilments() != null &&
                monster.getWeakness() != null &&
                monster.getBreaks() != null &&
                monster.getHabitat() != null;
    }
}
