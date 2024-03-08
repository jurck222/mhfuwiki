package com.example.mhfuwiki.Monster.LargeMonster;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LargeMonsterRepository extends JpaRepository<LargeMonster, Long> {
    LargeMonster findByName(String name);
}
