package com.capgemini.backend.SpringBoot.presentation.assembler.games;

import com.capgemini.backend.SpringBoot.application.dto.games.GameDTO;
import com.capgemini.backend.SpringBoot.domain.model.games.Game;

public class GameAssembler {

    public static GameDTO toDTO(Game game) {
        return new GameDTO(game.getId(), game.getTitulo());
    }

    public static Game toEntity(GameDTO gameDTO) {
        return new Game(gameDTO.getId(), gameDTO.getTitulo());
    }
}