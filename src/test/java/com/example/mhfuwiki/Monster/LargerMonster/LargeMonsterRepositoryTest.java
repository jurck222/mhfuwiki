package com.example.mhfuwiki.Monster.LargerMonster;

import com.example.mhfuwiki.Monster.LargeMonster.LargeMonster;
import com.example.mhfuwiki.Monster.LargeMonster.LargeMonsterRepository;
import com.example.mhfuwiki.shared.Break;
import com.example.mhfuwiki.shared.Element;
import com.example.mhfuwiki.shared.Location;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class LargeMonsterRepositoryTest {
    @Autowired
    private LargeMonsterRepository largeMonsterRepository;

    private LargeMonster testMonster = new LargeMonster("Teostra", "Elder dragon",
            false, new String[]{Element.FIRE}, new String[]{}, new String[]{Element.DRAGON, Element.WATER},
            new String[]{Break.HORN, Break.WINGS, Break.TAIL}, 2610, 1479,
            new String[]{Location.DESERT, Location.OLD_VOLCANO, Location.TOWN, Location.VOLCANO, Location.SWAMP, Location.TOWER});

    private LargeMonster testMonster2 = new LargeMonster("Lunastra", "Elder dragon",
            false, new String[]{Element.FIRE}, new String[]{}, new String[]{Element.DRAGON, Element.WATER},
            new String[]{Break.HORN, Break.WINGS, Break.TAIL}, 2610, 1479,
            new String[]{Location.DESERT, Location.OLD_VOLCANO, Location.TOWN, Location.VOLCANO, Location.SWAMP, Location.TOWER});

    private LargeMonster testMonster3 = new LargeMonster("Fatalis", "Elder dragon",
            false, new String[]{Element.FIRE}, new String[]{}, new String[]{Element.DRAGON, Element.WATER},
            new String[]{Break.HORN, Break.WINGS, Break.TAIL}, 2610, 1479,
            new String[]{Location.DESERT, Location.OLD_VOLCANO, Location.TOWN, Location.VOLCANO, Location.SWAMP, Location.TOWER});

    @Test
    @DisplayName("Should save a large monster")
    public void testAddLargeMonster() {
        LargeMonster monster = largeMonsterRepository.save(testMonster);

        assertEquals("Teostra", monster.getName());
        assertEquals("Elder dragon", monster.getSpecies());
    }

    @Test
    @DisplayName("Should find a large monster by name")
    public void testLargeMonsterByName() {
        LargeMonster monster = largeMonsterRepository.save(testMonster);

        LargeMonster foundMonster = largeMonsterRepository.findByName("Teostra");

        assertNotNull(foundMonster);
        assertEquals(foundMonster, testMonster);
    }

    @Test
    public void testDeleteById() {
        LargeMonster savedLargeMonster = largeMonsterRepository.save(testMonster);

        largeMonsterRepository.deleteById(savedLargeMonster.getId());
        Optional<LargeMonster> deletedLargeMonster = largeMonsterRepository.findById(testMonster.getId());

        assertFalse(largeMonsterRepository.findById(savedLargeMonster.getId()).isPresent());
        assertThat(deletedLargeMonster).isEmpty();
    }

    @Test
    @DisplayName("Should get all largeMonsters")
    public void testGetLargeMonsters() {
        largeMonsterRepository.save(testMonster);
        largeMonsterRepository.save(testMonster2);

        List<LargeMonster> foundLargeMonsters = largeMonsterRepository.findAll();

        assertEquals(2, foundLargeMonsters.size());
        assertEquals("Teostra", foundLargeMonsters.get(0).getName());
        assertEquals("Lunastra", foundLargeMonsters.get(1).getName());
    }
}
