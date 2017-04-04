package testmongorest;

import org.springframework.data.mongodb.repository.MongoRepository;
import testmongorest.dataconfig.BaseObject;
import testmongorest.dataconfig.Position;

/**
 * Created by user on 04.04.2017.
 */
public interface PositionObjectRepository extends MongoRepository<Position, String> {
    public Position findById(String id);
}
