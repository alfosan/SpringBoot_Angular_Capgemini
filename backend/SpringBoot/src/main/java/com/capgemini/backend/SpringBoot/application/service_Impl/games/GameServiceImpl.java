package com.capgemini.backend.SpringBoot.application.service_Impl.games;

import com.capgemini.backend.SpringBoot.application.service.games.GameService;
import com.capgemini.backend.SpringBoot.domain.model.games.Game;
import com.capgemini.backend.SpringBoot.domain.repo.games.GameRepository;
import com.capgemini.backend.SpringBoot.application.exception.games.DuplicateGameTitleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    // Obtiene todos los juegos de la base de datos
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    // Busca un juego específico por su ID
    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }

    @Override
    // Crea un nuevo juego verificando que no exista otro con el mismo título
    public Game createGame(Game game) {
        if (gameRepository.existsByTitulo(game.getTitulo())) {
            throw new DuplicateGameTitleException("Ya existe un juego con el título: " + game.getTitulo());
        }
        return gameRepository.save(game);
    }

    @Override
    // Actualiza un juego existente si se encuentra por su ID
    public Game updateGame(Long id, Game game) {
        if (gameRepository.findById(id).isPresent()) {
            game.setId(id);
            return gameRepository.save(game);
        }
        return null;
    }

    @Override
    // Elimina un juego si existe en la base de datos
    public boolean deleteGame(Long id) {
        if (gameRepository.findById(id).isPresent()) {
            gameRepository.deleteById(id);
            return true;
        }
        return false;
    }
}