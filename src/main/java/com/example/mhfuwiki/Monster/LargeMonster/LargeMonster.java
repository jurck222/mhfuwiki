package com.example.mhfuwiki.Monster.LargeMonster;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
@Entity
@Table
public class LargeMonster {
    @Id
    @SequenceGenerator(
            name = "large_monster_sequence",
            sequenceName = "large_monster_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "large_monster_sequence"
    )
    private Long id;
    private String name;
    private String species;
    private boolean subSpecies;
    private String[] elements;
    private String[] ailments;
    private String[] weakness;
    private String[] breaks;
    private int largeSize;
    private int smallSize;
    private String[] habitat;

    public LargeMonster() {
    }

    public LargeMonster(Long id, String name, String species, boolean subSpecies, String[] elements, String[] ailments,
                        String[] weakness, String[] breaks, int largeSize, int smallSize, String[] habitat) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.subSpecies = subSpecies;
        this.elements = elements;
        this.ailments = ailments;
        this.weakness = weakness;
        this.breaks = breaks;
        this.largeSize = largeSize;
        this.smallSize = smallSize;
        this.habitat = habitat;
    }

    public LargeMonster(String name, String species, boolean subSpecies, String[] elements, String[] ailments, String[]
            weakness, String[] breaks, int largeSize, int smallSize, String[] habitat) {
        this.name = name;
        this.species = species;
        this.subSpecies = subSpecies;
        this.elements = elements;
        this.ailments = ailments;
        this.weakness = weakness;
        this.breaks = breaks;
        this.largeSize = largeSize;
        this.smallSize = smallSize;
        this.habitat = habitat;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setSubSpecies(boolean subSpecies) {
        this.subSpecies = subSpecies;
    }

    public void setElements(String[] elements) {
        this.elements = elements;
    }

    public void setAilments(String[] ailments) {
        this.ailments = ailments;
    }

    public void setWeakness(String[] weakness) {
        this.weakness = weakness;
    }

    public void setBreaks(String[] breaks) {
        this.breaks = breaks;
    }

    public void setLargeSize(int largeSize) {
        this.largeSize = largeSize;
    }

    public void setSmallSize(int smallSize) {
        this.smallSize = smallSize;
    }

    public void setHabitat(String[] habitat) {
        this.habitat = habitat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LargeMonster that = (LargeMonster) o;
        return subSpecies == that.subSpecies && largeSize == that.largeSize && smallSize == that.smallSize && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(species, that.species) && Arrays.equals(elements, that.elements) && Arrays.equals(ailments, that.ailments) && Arrays.equals(weakness, that.weakness) && Arrays.equals(breaks, that.breaks) && Arrays.equals(habitat, that.habitat);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, species, subSpecies, largeSize, smallSize);
        result = 31 * result + Arrays.hashCode(elements);
        result = 31 * result + Arrays.hashCode(ailments);
        result = 31 * result + Arrays.hashCode(weakness);
        result = 31 * result + Arrays.hashCode(breaks);
        result = 31 * result + Arrays.hashCode(habitat);
        return result;
    }
}
