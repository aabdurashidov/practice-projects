package scylladb.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;
import scylladb.user.entity.User;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final Sinks.Many<User> userSink = Sinks.many().replay().all();

    public Mono<User> customSave(User entity) {
        entity.setName(entity.getName());
        return repository.save(entity)
                .doOnNext(userSink::tryEmitNext);
    }

    public Mono<User> findById(String id) {
        return repository.findById(id);
    }

    public Flux<User> getAll() {
        return repository.findAll();
    }

    public Flux<ServerSentEvent<User>> streamUsers() {
        return userSink.asFlux()
                .map(user -> ServerSentEvent.builder(user)
                        .id(user.getId().toString())
                        .event("new-user")
                        .build())
                .onBackpressureBuffer()
                .publishOn(Schedulers.boundedElastic())
                .doOnCancel(() -> userSink.asFlux().subscribe().dispose());
    }
}