package testmongorest.service;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import testmongorest.dataconfig.Device;
import testmongorest.dataconfig.Position;

/**
 * Created by user on 04.04.2017.
 */
public class ObjectByTimestampFinder<T> {

    private MongoTemplate mongoTemplate;

    public ObjectByTimestampFinder(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public String findObject(long timestamp, Class<T> localClass, String mongoCollection) {

        T result;

        long timeFindByTS = System.currentTimeMillis();

        Query query = new Query();
        query.addCriteria(Criteria.where("timeStamp").is(timestamp));

        result = mongoTemplate.findOne(query, localClass, mongoCollection);

        timeFindByTS = System.currentTimeMillis() - timeFindByTS;
        if (result == null) {
            return "findId # "+ " null " + ", time - " + (double)timeFindByTS/1000+" - " + " null" ;
        } else {
            return "findTimestamp # " + timestamp + ", time - " + (double) timeFindByTS / 1000 + " - " + result.toString();
        }

}
}
