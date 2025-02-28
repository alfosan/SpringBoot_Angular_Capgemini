package com.capgemini.backend.SpringBoot.presentation.controller.games;

import com.capgemini.backend.SpringBoot.application.dto.games.GameDTO;
import com.capgemini.backend.SpringBoot.application.service.games.GameService;
import com.capgemini.backend.SpringBoot.domain.model.games.Game;
import com.capgemini.backend.SpringBoot.presentation.assembler.games.GameAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/games")
public class GameController {

    /**
     * Servicio que maneja la lógica de negocio de los juegos
     */
    @Autowired
    private GameService gameService;

    /**
     * Obtiene todos los juegos disponibles
     */
    @GetMapping
    public List<GameDTO> getAllGames() {
        return gameService.getAllGames().stream()
                .map(GameAssembler::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca un juego por su ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<GameDTO> getGameById(@PathVariable Long id) {
        Optional<Game> game = gameService.getGameById(id);
        return game.map(g -> ResponseEntity.ok(GameAssembler.toDTO(g)))
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Crea un nuevo juego
     */
    @PostMapping
    public ResponseEntity<GameDTO> createGame(@RequestBody GameDTO gameDTO) {
        Game game = GameAssembler.toEntity(gameDTO);
        Game nuevoGame = gameService.createGame(game);
        return ResponseEntity.status(HttpStatus.CREATED).body(GameAssembler.toDTO(nuevoGame));
    }

    /**
     * Actualiza un juego existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<GameDTO> updateGame(@PathVariable Long id, @RequestBody GameDTO gameDTO) {
        Game game = GameAssembler.toEntity(gameDTO);
        Game gameActualizado = gameService.updateGame(id, game);
        return gameActualizado != null ? ResponseEntity.ok(GameAssembler.toDTO(gameActualizado)) : ResponseEntity.notFound().build();
    }

    /**
     * Elimina un juego por su ID
     */
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        return gameService.deleteGame(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}