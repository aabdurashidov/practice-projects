package scylladb.user.entity;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@Table("user_table")
public class User {
    @PrimaryKey
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID id;
    private String name;

    public User() {
        id = Uuids.timeBased();
    }
}
