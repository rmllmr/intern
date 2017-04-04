package testmongorest.restcontroller;

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
import testmongorest.PositionObjectRepository;
import testmongorest.dataconfig.AverTemp;
import testmongorest.dataconfig.BaseObject;
import testmongorest.dataconfig.Position;
import testmongorest.service.TimeRecordObjectByBlock;
import testmongorest.service.TimeRecordObjectByOne;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;

@RestController
public class BaseRestController {

    @Autowired
    private BaseObjectRepository baseObjectRepository;

    @Autowired
    private PositionObjectRepository positionObjectRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/findAll")
    public List<BaseObject> findAll() {

        return baseObjectRepository.findAll();
    }

    @RequestMapping("/findById")
    public String findById(@RequestParam(value="id", defaultValue="1") String id) {

        long timeFindById = System.currentTimeMillis();
        BaseObject object;
        object = baseObjectRepository.findById(id);
        timeFindById = System.currentTimeMillis() - timeFindById;
        return "findId # "+ id+ ", time - " + (double)timeFindById/1000+" - " +object.toString();
    }

    @RequestMapping("/findPositionById")
    public String findPositionById(@RequestParam(value="id", defaultValue="1") String id) {

        long timeFindById = System.currentTimeMillis();
        Position object;
        object = positionObjectRepository.findById(id);
        timeFindById = System.currentTimeMillis() - timeFindById;
        return "findId # "+ id+ ", time - " + (double)timeFindById/1000+" - " +object.toString();
    }

    @RequestMapping("/findPositionByTS")
    public String findPositionByTS(@RequestParam(value="timestamp", defaultValue="10") long timeStamp) {

        long timeFindByTS = System.currentTimeMillis();

        Query query = new Query();
        query.addCriteria(Criteria.where("timeStamp").is(timeStamp));

        Position result = mongoTemplate.findOne(query, Position.class , "Position");

        timeFindByTS = System.currentTimeMillis() - timeFindByTS;

        if (result == null) {
            return "findId # "+ " null " + ", time - " + (double)timeFindByTS/1000+" - " + " null" ;
        }
        else {
            return "findId # " + result.getId() + ", time - " + (double) timeFindByTS / 1000 + " - " + result.getTimestamp();
        }
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

        baseObjectRepository.deleteAll();

    }

    @RequestMapping("/timeRecordByOne")
    public String timeRecordByOne(@RequestParam(value="numberofmillions", defaultValue="1") long number) {

        return new TimeRecordObjectByOne(number).GetResultBase(baseObjectRepository, counter, new BaseObject());

    }

    @RequestMapping("/timeRecordPositionByOne")
    public String timeRecordPositionByOne(@RequestParam(value="numberofmillions", defaultValue="1") long number) {

        return new TimeRecordObjectByOne(number).GetResult(positionObjectRepository, counter, new Position());

    }


    @RequestMapping("/timeRecordByBlock")
    public String timeRecordByBlock(@RequestParam(value="number", defaultValue="10000") long number, @RequestParam(value="sizeblock", defaultValue="500") long sizeBlock) {

        return new TimeRecordObjectByBlock(number, sizeBlock).GetResultBase(baseObjectRepository, counter, new BaseObject());

    }

    @RequestMapping("/timeRecordPositionByBlock")
    public String timeRecordPositionByBlock(@RequestParam(value="number", defaultValue="10000") long number, @RequestParam(value="sizeblock", defaultValue="500") long sizeBlock) {

        return new TimeRecordObjectByBlock(number, sizeBlock).GetResult(positionObjectRepository, counter, new Position());

    }

}
