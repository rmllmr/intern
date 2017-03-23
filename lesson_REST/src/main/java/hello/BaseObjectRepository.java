package hello;

import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * Created by user on 23.03.2017.
 */
public interface BaseObjectRepository extends MongoRepository<BaseObject, String>{
    public BaseObject findById(String id);
}
