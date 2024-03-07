package com.example.mhfuwiki.Monster;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table
public class Monster {
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
}
