package scylladb.user.entity;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@Table("user_table")
public class User {
    @PrimaryKey
    private String id;
    private String name;
}
