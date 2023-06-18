package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.film.FilmService;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/films", produces = "application/json")
public class FilmController {

    private final FilmStorage filmStorage;
    private final FilmService filmService;

    @PostMapping
    public Film create(@Valid @RequestBody Film film) {
        log.info("A request has been received to add a movie.");
        return filmStorage.addFilm(film);
    }

    @PutMapping
    public Film changeFilm(@Valid @RequestBody Film film) {
        log.info("A request for changes to the movie has been received.");
        return filmStorage.updateFilm(film);
    }

    @PutMapping("/{id}/like/{filmId}")
    public void like(@PathVariable Integer id, @PathVariable Integer filmId) {
        log.info("A request has been received to assign a like to the movie.");
        filmService.like(id, filmId);
    }

    @GetMapping()
    public List<Film> getFilms() {
        log.info("A request has been received to obtain a list of all movies.");
        return filmStorage.findAllFilms();
    }

    @GetMapping("/{id}")
    public Film getFilm(@PathVariable Integer id) {
        log.info("Received a GET-request to receive a movie");
        return filmStorage.getFilmById(id);
    }

    @GetMapping("/popular")
    public List<Film> getBestFilms(@RequestParam(defaultValue = "10") Integer count) {
        log.info("A request has been received for a list of popular films.");
        return filmService.getTopFilms(count);
    }

    @DeleteMapping("/{id}/like/{filmId}")
    public void deleteLike(@PathVariable Integer id, @PathVariable Integer filmId) {
        log.info("A request has been received to remove a like from the film.");
        filmService.deleteLike(filmId, id);
    }

}