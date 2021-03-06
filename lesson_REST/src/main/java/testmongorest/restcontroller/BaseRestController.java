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
import testmongorest.DeviceObjectRepository;
import testmongorest.PositionObjectRepository;
import testmongorest.dataconfig.AverTemp;
import testmongorest.dataconfig.BaseObject;
import testmongorest.dataconfig.Device;
import testmongorest.dataconfig.Position;
import testmongorest.service.ObjectByIdFinder;
import testmongorest.service.ObjectByTimestampFinder;
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
    private DeviceObjectRepository deviceObjectRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/findAll")
    public List<BaseObject> findAll() {

        return baseObjectRepository.findAll();
    }

    @RequestMapping("/findById")
    public String findById(@RequestParam(value="id", defaultValue="1") String id) {

        return new ObjectByIdFinder(baseObjectRepository).findObjectToString(id);
    }

    @RequestMapping("/findPositionById")
    public String findPositionById(@RequestParam(value="id", defaultValue="1") String id) {

        return new ObjectByIdFinder(positionObjectRepository).findObjectToString(id);
    }

    @RequestMapping("/findPositionJSById")
    public Object findPositionJSById(@RequestParam(value="id", defaultValue="100") String id) {

        return new ObjectByIdFinder(positionObjectRepository).findObject(id);
    }

    @RequestMapping("/findDeviceById")
    public String findDeviceById(@RequestParam(value="id", defaultValue="1") String id) {

        return new ObjectByIdFinder(deviceObjectRepository).findObjectToString(id);
    }

    @RequestMapping("/findPositionByTS")
    public String findPositionByTS(@RequestParam(value="timestamp", defaultValue="10") long timeStamp) {

        return new ObjectByTimestampFinder(mongoTemplate).findObject(timeStamp, Position.class, "position");
    }

    @RequestMapping("/findDeviceByTS")
    public String findDeviceByTS(@RequestParam(value="timestamp", defaultValue="10") long timeStamp) {

        return new ObjectByTimestampFinder(mongoTemplate).findObject(timeStamp, Device.class, "device");
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

    @RequestMapping("/timeRecordDeviceByOne")
    public String timeRecordDeviceByOne(@RequestParam(value="numberofmillions", defaultValue="1") long number) {

        return new TimeRecordObjectByOne(number).GetResultDevice(deviceObjectRepository, counter, new Device());

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
