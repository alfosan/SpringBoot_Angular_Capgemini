package com.capgemini.backend.SpringBoot.application.service.games;

import com.capgemini.backend.SpringBoot.domain.model.games.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {
    List<Game> getAllGames();
    Optional<Game> getGameById(Long id);
    Game createGame(Game game);
    Game updateGame(Long id, Game game);
    boolean deleteGame(Long id);
}