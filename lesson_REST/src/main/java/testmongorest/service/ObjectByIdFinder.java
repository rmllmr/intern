package testmongorest.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import testmongorest.dataconfig.Position;

/**
 * Created by user on 05.04.2017.
 */
public class ObjectByIdFinder<T> {

    MongoRepository<T, String> mongoRepository;

    public ObjectByIdFinder(MongoRepository<T, String> mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    public String findObjectToString(String id){

        long timeFindById = System.currentTimeMillis();
        T object = mongoRepository.findOne(id);
        timeFindById = System.currentTimeMillis() - timeFindById;
        return "FindObject  # "+ object.toString() + ", Search time - " + (double)timeFindById/1000+ " - " ;
    }

    public T findObject(String id){

        T object = mongoRepository.findOne(id);
        return object;
    }
}
