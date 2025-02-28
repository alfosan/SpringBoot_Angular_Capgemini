package com.capgemini.backend.SpringBoot.domain.repo.games;

import com.capgemini.backend.SpringBoot.domain.model.games.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    boolean existsByTitulo(String titulo);
}