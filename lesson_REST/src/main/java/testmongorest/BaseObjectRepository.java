package testmongorest;

import org.springframework.data.mongodb.repository.MongoRepository;
import testmongorest.dataconfig.BaseObject;
import testmongorest.dataconfig.Position;


/**
 * Created by user on 23.03.2017.
 */
public interface BaseObjectRepository<T> extends MongoRepository<T, String>{
    public BaseObject findById(String id);
}
