package testmongorest.restcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import testmongorest.BaseObjectRepository;
import testmongorest.dataconfig.AverTemp;
import testmongorest.dataconfig.BaseObject;
import testmongorest.dataconfig.Position;
import testmongorest.service.BaseObjectParams;
import testmongorest.service.timeRecordObjectByBlock;
import testmongorest.service.timeRecordObjectByOne;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;

@RestController
public class BaseRestController {

    @Autowired
    private BaseObjectRepository repository;

    @Autowired
    MongoTemplate mongoTemplate;

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/findAll")
    public List<BaseObject> findAll() {

        return repository.findAll();
    }

    @RequestMapping("/findById")
    public String findById(@RequestParam(value="id", defaultValue="1") String id) {

        long timeFindById = System.currentTimeMillis();
        BaseObject object;
        object = repository.findById(id);
        timeFindById = System.currentTimeMillis() - timeFindById;
        return "findId # "+ id+ ", time - " + (double)timeFindById/1000+" - " +object.toString();
    }

    @RequestMapping("/countShockByTS")
    public String countShockByTS(@RequestParam(value="timestamp", defaultValue="10") long timestamp) {

        long timeFindByTS = System.currentTimeMillis();
        long findCount;
        Query query = new Query();
        query.addCriteria(Criteria.where("shock").is(true).and("timeStamp").is(timestamp));

        findCount = mongoTemplate.count(query, BaseObject.class, "baseObject");
        timeFindByTS = System.currentTimeMillis() - timeFindByTS;

        return " Count shock = " + findCount + " for TS " + timestamp +" time = " + (double)timeFindByTS/1000;
    }

    @RequestMapping("/countShockByRangeTS")
    public String countShockByRangeTS(@RequestParam(value="timestampF", defaultValue="10") long timestampFirst, @RequestParam(value="timestampL", defaultValue="10") long timestampLast) {

        long timeFindByTS = System.currentTimeMillis();
        long findCount;
        Query query = new Query();
        query.addCriteria(Criteria.where("shock").is(true).and("timeStamp").exists(true).andOperator(Criteria.where("timeStamp").gt(timestampFirst), Criteria.where("timeStamp").lt(timestampLast)));

        findCount = mongoTemplate.count(query, BaseObject.class, "baseObject");
        timeFindByTS = System.currentTimeMillis() - timeFindByTS;

        return " Count shock = " + findCount + " for range TS [" + timestampFirst + " - " + timestampLast +"] time = " + (double)timeFindByTS/1000;
    }

    @RequestMapping("/averageTemp")
    public String averageTemp() {

        long timeAverageTemp = System.currentTimeMillis();
        Aggregation aggregation = Aggregation.newAggregation(group("id").avg("temp").as("averTemp"));

        AggregationResults<AverTemp> groupResults = mongoTemplate.aggregate(aggregation, "baseObject", AverTemp.class);
        AverTemp result = groupResults.getUniqueMappedResult();
        timeAverageTemp = System.currentTimeMillis() - timeAverageTemp;

        return " Average Temp = " + result.toString() + " time  = "+ (double)timeAverageTemp/1000;
    }

    @RequestMapping("/removeAll")
    public void removeAll() {

        repository.deleteAll();

    }

    @RequestMapping("/timeRecordByOne")
    public String timeRecordByOne(@RequestParam(value="numberofmillions", defaultValue="1") long number) {

        return new timeRecordObjectByOne(number).GetResult(repository, counter, new Position());

    }


    @RequestMapping("/timeRecordByBlock")
    public String timeRecordByBlock(@RequestParam(value="number", defaultValue="10000") long number, @RequestParam(value="sizeblock", defaultValue="500") long sizeBlock) {

        return new timeRecordObjectByBlock(number, sizeBlock).GetResult(repository, counter, new Position());

    }
}
