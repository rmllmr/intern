package testmongorest.service;

import testmongorest.BaseObjectRepository;
import testmongorest.PositionObjectRepository;
import testmongorest.dataconfig.BaseObject;
import testmongorest.dataconfig.Position;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by user on 03.04.2017.
 */
public class TimeRecordObjectByBlock {

    private final long number;
    private final long sizeBlock;


    public TimeRecordObjectByBlock(long number, long sizeBlock) {
        this.number = number;
        this.sizeBlock = sizeBlock;
    }

    public String GetResult(PositionObjectRepository repository, AtomicLong counter, Position localObject){

        long timeSaveAllObject = System.currentTimeMillis();
        long timeSaveBlock = System.currentTimeMillis();
        FillObjectParams fillObjectParams = new FillObjectParams();
        ArrayList<Position> blockObjects = new ArrayList<>();

        int k = 0;
        for (int i = 0; i < number; i++) {
            k++;
            if (k == sizeBlock) {
                k = 0;
                repository.save(blockObjects);
                blockObjects.removeAll(blockObjects);

                System.out.println("# "+(i+1)+ " time = "+(System.currentTimeMillis() - timeSaveBlock));

                timeSaveBlock = System.currentTimeMillis();
            }
            else{
                localObject.setId(counter.incrementAndGet());
                blockObjects.add(fillObjectParams.positionFillParams(localObject));
            }

        }
        repository.save(blockObjects);
        blockObjects.removeAll(blockObjects);

        timeSaveAllObject = System.currentTimeMillis() - timeSaveAllObject;

        return " Time records by block ( "+ number+ " base object, size block "+ sizeBlock+ " ) = "+ (double)timeSaveAllObject/1000;

    }


    public String GetResultBase(BaseObjectRepository repository, AtomicLong counter, BaseObject localObject ){

        long timeSaveAllObject = System.currentTimeMillis();
        long timeSaveBlock = System.currentTimeMillis();
        FillObjectParams fillObjectParams = new FillObjectParams();
        ArrayList<BaseObject> blockObjects = new ArrayList<>();

        int k = 0;
        for (int i = 0; i < number; i++) {
            k++;
            if (k == sizeBlock) {
                k = 0;
                repository.save(blockObjects);
                blockObjects.removeAll(blockObjects);

                System.out.println("# "+(i+1)+ " time = "+(System.currentTimeMillis() - timeSaveBlock));
                timeSaveBlock = System.currentTimeMillis();
            }
            else{
                localObject.setId(counter.incrementAndGet());
                blockObjects.add(fillObjectParams.baseObjectFillParams(localObject));
            }

        }
        repository.save(blockObjects);
        blockObjects.removeAll(blockObjects);

        timeSaveAllObject = System.currentTimeMillis() - timeSaveAllObject;

        return " Time records by block ( "+ number+ " base object, size block "+ sizeBlock+ " ) = "+ (double)timeSaveAllObject/1000;


    }

}
