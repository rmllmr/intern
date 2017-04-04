package testmongorest.service;

import testmongorest.BaseObjectRepository;
import testmongorest.PositionObjectRepository;
import testmongorest.dataconfig.BaseObject;
import testmongorest.dataconfig.Position;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by user on 03.04.2017.
 */
public class TimeRecordObjectByOne {

    private final long number;


    public TimeRecordObjectByOne(long number) {
        this.number = number;
    }

    public String GetResult(PositionObjectRepository repository, AtomicLong counter, Position localObject){

        long timeSaveAllObject = System.currentTimeMillis();
        long timeSaveBlock = System.currentTimeMillis();
        FillObjectParams fillObjectParams = new FillObjectParams();
        int k = 0;
        for (int i = 0; i <  1000000*number; i++) {
            localObject.setId(counter.incrementAndGet());
            repository.save(fillObjectParams.positionFillParams(localObject));
            k ++;
            if (k == 10001) {
                k = 0;
                System.out.println("#"+ i + " time "+ (System.currentTimeMillis() - timeSaveBlock));
                timeSaveBlock = System.currentTimeMillis();
            }
        }
        timeSaveAllObject = System.currentTimeMillis() - timeSaveAllObject;

        return " Time records by one (" + number + " million base object) = "+ (double)timeSaveAllObject/1000;

    }


    public String GetResultBase(BaseObjectRepository repository, AtomicLong counter, BaseObject localObject ){

        long timeSaveAllObject = System.currentTimeMillis();
        long timeSaveBlock = System.currentTimeMillis();
        FillObjectParams fillObjectParams = new FillObjectParams();
        int k = 0;
        for (int i = 0; i <  1000000*number; i++) {
            localObject.setId(counter.incrementAndGet());
            repository.save(fillObjectParams.baseObjectFillParams(localObject));
            k ++;
            if (k == 10001) {
                k = 0;
                System.out.println("#"+ i + " time "+ (System.currentTimeMillis() - timeSaveBlock));
                timeSaveBlock = System.currentTimeMillis();
            }
        }
        timeSaveAllObject = System.currentTimeMillis() - timeSaveAllObject;

        return " Time records by one (" + number + " million base object) = "+ (double)timeSaveAllObject/1000;

    }

}
