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
import testmongorest.service.BaseObjectParams;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;

@RestController
public class GreetingController {

    @Autowired
    private BaseObjectRepository repository;

    @Autowired
    MongoTemplate mongoTemplate;

    private final AtomicLong counter = new AtomicLong();
    private static BaseObject tempObject;

    @RequestMapping("/newBaseObject")
    public BaseObject newBaseObject(@RequestParam(value="lat", defaultValue="50") long latitude, @RequestParam(value="long", defaultValue="50") long longitude, @RequestParam(value="timestamp", defaultValue="8500") long   timeStamp)  {

        tempObject = new BaseObject(counter.incrementAndGet());
        repository.save(tempObject);
        return tempObject;
    }

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
        return "findId # "+ id+ ", time - " + timeFindById+" ----       " +object.toString();
    }

    @RequestMapping("/findByTS")
    public String findByTS(@RequestParam(value="timestamp", defaultValue="1") long timestamp) {

        long timeFindById = System.currentTimeMillis();
        long findCount;
        Query query = new Query();
        long timeStamp1 = 1490602557822L;
        query.addCriteria(Criteria.where("timeStamp").is(timeStamp1));
        findCount = mongoTemplate.count(query, BaseObject.class,"BaseObject");
        timeFindById = System.currentTimeMillis() - timeFindById;
        return "findId # "+ findCount+ ", time - " + timeFindById+" ----       ";
    }

    @RequestMapping("/averageTemp")
    public String averageTemp() {

        long timeAverT = System.currentTimeMillis();
        Aggregation aggregation = Aggregation.newAggregation(group("id").avg("temp").as("averTemp"));

        AggregationResults<AverTemp> groupResults = mongoTemplate.aggregate(aggregation, "baseObject", AverTemp.class);
        AverTemp result = groupResults.getUniqueMappedResult();
        timeAverT = System.currentTimeMillis() - timeAverT;

        return " aver T = " + result.toString() + " time  = "+ timeAverT ;
    }

    @RequestMapping("/removeAll")
    public void removeAll() {

        repository.deleteAll();

    }

    @RequestMapping("/M1")
    public long M1() {

        long timeSaveAllObject = System.currentTimeMillis();
        long timeSaveBlock = System.currentTimeMillis();
        BaseObjectParams objectGen = new BaseObjectParams();
        int k = 0;
        for (int i = 0; i <  1000000; i++) {
            repository.save(objectGen.baseObjectFillParams(new BaseObject(counter.incrementAndGet())));
            k ++;
            if (k == 10001) {
                k = 0;
                System.out.println("#"+ i + " time "+ (System.currentTimeMillis() - timeSaveBlock));
                timeSaveBlock = System.currentTimeMillis();
            }
        }
        timeSaveAllObject = System.currentTimeMillis() - timeSaveAllObject;


        return timeSaveAllObject;
    }

    @RequestMapping("/NB")
    public String NB(@RequestParam(value="number", defaultValue="10000") long number, @RequestParam(value="sizeblock", defaultValue="500") long sizeBlock) {

        long timeSaveAllObject = System.currentTimeMillis();
        long timeSaveBlock = System.currentTimeMillis();
        BaseObjectParams objectGen = new BaseObjectParams();
        ArrayList<BaseObject> baseObject100 = new ArrayList<>();

        int k = 0;
        for (int i = 0; i < number; i++) {
            k++;
            if (k == sizeBlock) {
                k = 0;
                repository.save(baseObject100);
                baseObject100.removeAll(baseObject100);

                System.out.println("# "+(i+1)+ " time = "+(System.currentTimeMillis() - timeSaveBlock));
                timeSaveBlock = System.currentTimeMillis();
            }
            else{
                baseObject100.add(objectGen.baseObjectFillParams(new BaseObject(counter.incrementAndGet())));
            }

        }
        repository.save(baseObject100);
        baseObject100.removeAll(baseObject100);

        timeSaveAllObject = System.currentTimeMillis() - timeSaveAllObject;
        return "Saved "+ number+ " obj., size block "+ sizeBlock+ ". Time = "+timeSaveAllObject;

    }
}
