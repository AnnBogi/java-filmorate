package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;


@Data
@Builder
public class Film {

    public int id;
    @NotBlank(message = "имя не должно быть пустым")
    private String name;
    @Size(max = 200)
    private String description;
    @Past(message = "Дата релиза не может быть в будущем")

    private LocalDate releaseDate;
    @Positive(message = "продолжительность не должна быть отрицательной")
    private int duration;
    private Set<Integer> likes;
    @NotNull
    private Mpa mpa;
    private Set<Genre> genres;

}