package scylladb.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import scylladb.user.entity.User;

@RestController
@RequestMapping("/entities")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping
    public Mono<User> createEntity(@RequestBody User entity) {
        return service.customSave(entity);
    }

    @GetMapping("/{id}")
    public Mono<User> getEntity(@PathVariable String id) {
        return service.findById(id);
    }
}