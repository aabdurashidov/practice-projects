package scylladb.user;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import scylladb.user.entity.User;

public interface UserRepository extends ReactiveCassandraRepository<User, String> {
}
