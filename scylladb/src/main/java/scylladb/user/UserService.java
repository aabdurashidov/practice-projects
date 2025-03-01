package scylladb.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import scylladb.user.entity.User;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public Mono<User> customSave(User entity) {
        entity.setName(entity.getName() + " - Saved at " + Instant.now());
        return repository.save(entity);
    }

    public Mono<User> findById(String id) {
        return repository.findById(id);
    }
}