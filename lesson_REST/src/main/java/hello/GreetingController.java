package hello;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/averageTemp")
    public String averageTemp(@RequestParam(value="timestamp", defaultValue="1") String timestamp) {

        mongoTemplate.ex
        long timeFindById = System.currentTimeMillis();
        BaseObject object;
        repository.findAll(Ex)
        object = repository.findById(id);
        timeFindById = System.currentTimeMillis() - timeFindById;
        return "findId # "+ id+ ", time - " + timeFindById+" ----       " +object.toString();
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
