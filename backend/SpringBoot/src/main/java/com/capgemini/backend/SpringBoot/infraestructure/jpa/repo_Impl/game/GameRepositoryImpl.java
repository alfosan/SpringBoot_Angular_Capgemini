package com.capgemini.backend.SpringBoot.infraestructure.jpa.repo_Impl.games;

import com.capgemini.backend.SpringBoot.domain.model.games.Game;
import com.capgemini.backend.SpringBoot.domain.repo.games.GameRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepositoryImpl extends JpaRepository<Game, Long>, GameRepository {}