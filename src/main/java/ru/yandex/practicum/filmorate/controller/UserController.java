package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.user.UserService;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/users", produces = "application/json")
public class UserController {

    private final UserStorage userStorage;
    private final UserService userService;


    @PostMapping
    public User create(@Valid @RequestBody User user) {
        log.info("A request has been received to create a user.");
        return userStorage.addUser(user);
    }

    @PutMapping
    public User changeUser(@Valid @RequestBody User user) {
        log.info("A user update request has been received.");
        return userStorage.updateUser(user);
    }

    @PutMapping("/{id}/friends/{friendId}")
    public User addFriend(@PathVariable Integer id, @PathVariable Integer friendId) {
        log.info("A friend request has been received.");
        return userService.addFriend(id, friendId);
    }

    @GetMapping
    public List<User> getUsers() {
        log.info("A request has been received for a list of users.");
        return userStorage.findAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        log.info("A request has been received to get a user by id.");
        return userStorage.getUserById(Integer.parseInt(id));
    }


    @GetMapping("/{id}/friends")
    public List<User> getFriends(@PathVariable Integer id) {
        log.info("A request has been received for a list of friends.");
        return userService.getUserFriends(id);
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public List<User> getMutualFriends(@PathVariable Integer id, @PathVariable Integer otherId) {
        log.info("A request has been received for a list of mutual friends.");
        return userService.getMutualFriends(id, otherId);
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public void deleteFriend(@PathVariable Integer id, @PathVariable Integer friendId) {
        log.info("I received a request to be removed from friends.");
        userService.deleteFriend(id, friendId);
    }

}