package com.example.mhfuwiki.Monster.LargerMonster;

import com.example.mhfuwiki.Monster.LargeMonster.LargeMonster;
import com.example.mhfuwiki.Monster.LargeMonster.LargeMonsterRepository;
import com.example.mhfuwiki.Monster.LargeMonster.LargeMonsterService;
import com.example.mhfuwiki.shared.Break;
import com.example.mhfuwiki.shared.Element;
import com.example.mhfuwiki.shared.Location;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LargeMonsterServiceTest {

    @Mock
    private LargeMonsterRepository largeMonsterRepository;

    @InjectMocks
    private LargeMonsterService largeMonsterService;

    private LargeMonster testMonster = new LargeMonster(1L, "Teostra", "Elder dragon",
            false, new String[]{Element.FIRE}, new String[]{}, new String[]{Element.DRAGON, Element.WATER},
            new String[]{Break.HORN, Break.WINGS, Break.TAIL}, 2610, 1479,
            new String[]{Location.DESERT, Location.OLD_VOLCANO, Location.TOWN, Location.VOLCANO, Location.SWAMP, Location.TOWER});

    private LargeMonster testMonster2 = new LargeMonster(1L, "Lunastra", "Elder dragon",
            false, new String[]{Element.FIRE}, new String[]{}, new String[]{Element.DRAGON, Element.WATER},
            new String[]{Break.HORN, Break.WINGS, Break.TAIL}, 2610, 1479,
            new String[]{Location.DESERT, Location.OLD_VOLCANO, Location.TOWN, Location.VOLCANO, Location.SWAMP, Location.TOWER});

    private LargeMonster testMonster3 = new LargeMonster(1L, "Fatalis", "Elder dragon",
            false, new String[]{Element.FIRE}, new String[]{}, new String[]{Element.DRAGON, Element.WATER},
            new String[]{Break.HORN, Break.WINGS, Break.TAIL}, 2610, 1479,
            new String[]{Location.DESERT, Location.OLD_VOLCANO, Location.TOWN, Location.VOLCANO, Location.SWAMP, Location.TOWER});

    @Test
    @DisplayName("Should find large monster by name")
    public void testFindByName() {
        when(largeMonsterRepository.findByName("Teostra")).thenReturn(testMonster);

        LargeMonster result = largeMonsterService.findByName("Teostra");

        assertEquals(testMonster, result);
    }

    @Test
    @DisplayName("Should find all large monsters")
    public void testFindAllMonsters() {
        List<LargeMonster> monsters = Arrays.asList(testMonster, testMonster2, testMonster3);
        when(largeMonsterRepository.findAll()).thenReturn(monsters);

        List<LargeMonster> result = largeMonsterService.getLargeMonsters();
        assertFalse(result.isEmpty());
        assertEquals(3, result.size());
        assertEquals(testMonster, result.get(0));
        assertEquals(testMonster2, result.get(1));
        assertEquals(testMonster3, result.get(2));
    }

    @Test
    @DisplayName("Should add large monster")
    public void testAddQuest() {
        when(largeMonsterRepository.save(testMonster)).thenReturn(testMonster);

        largeMonsterService.addMonster(testMonster);

        verify(largeMonsterRepository).save(testMonster);
    }

    @Test
    @DisplayName("Should delete large monster")
    public void testDeleteQuest() {
        Long id = 1L;
        when(largeMonsterRepository.findById(id)).thenReturn(Optional.of(testMonster));
        when(largeMonsterRepository.existsById(id)).thenReturn(true);

        largeMonsterService.deleteMonster(id);

        verify(largeMonsterRepository).deleteById(id);
    }
}
