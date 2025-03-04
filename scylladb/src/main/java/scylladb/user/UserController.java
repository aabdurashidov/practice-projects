package scylladb.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import scylladb.user.entity.User;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final UserService userService;

    @PostMapping
    public Mono<User> createEntity(@RequestBody User entity) {
        return service.customSave(entity);
    }

    @GetMapping("/{id}")
    public Mono<User> getEntity(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping
    public Flux<User> getAll() {
        return userService.getAll();
    }

    @GetMapping(value = "/stream-users", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<User>> streamUsers() {
        return userService.streamUsers();
    }
}