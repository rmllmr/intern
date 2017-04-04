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
public class timeRecordObjectByBlock {

    private final long number;
    private final long sizeBlock;


    public timeRecordObjectByBlock(long number, long sizeBlock) {
        this.number = number;
        this.sizeBlock = sizeBlock;
    }

    public String GetResult(PositionObjectRepository repository, AtomicLong counter, Position localObject){

        long timeSaveAllObject = System.currentTimeMillis();
        long timeSaveBlock = System.currentTimeMillis();
        FillObjectParams objectGen = new FillObjectParams();
        ArrayList<Position> baseObject100 = new ArrayList<>();
     //   ArrayList<Double> yData = new ArrayList<Double>();
     //   ArrayList<Integer> xData = new ArrayList<Integer>();

        int k = 0;
        for (int i = 0; i < number; i++) {
            k++;
            if (k == sizeBlock) {
                k = 0;
                repository.save(baseObject100);
                baseObject100.removeAll(baseObject100);

                System.out.println("# "+(i+1)+ " time = "+(System.currentTimeMillis() - timeSaveBlock));

                timeSaveBlock = System.currentTimeMillis();
     //           yData.add(Double.valueOf(timeSaveBlock/1000));
     //           xData.add(i);
            }
            else{
                localObject.setId(counter.incrementAndGet());
                baseObject100.add(objectGen.positionFillParams(localObject));
            }

        }
        repository.save(baseObject100);
        baseObject100.removeAll(baseObject100);

        timeSaveAllObject = System.currentTimeMillis() - timeSaveAllObject;

//        XYChart rezultChart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData);

//        return new SwingWrapper(rezultChart).displayChart();

        return " Time records by block ( "+ number+ " base object, size block "+ sizeBlock+ " ) = "+ (double)timeSaveAllObject/1000;

    }


    public String GetResultBase(BaseObjectRepository repository, AtomicLong counter, BaseObject localObject ){

        long timeSaveAllObject = System.currentTimeMillis();
        long timeSaveBlock = System.currentTimeMillis();
        FillObjectParams objectGen = new FillObjectParams();
        ArrayList<BaseObject> baseObject100 = new ArrayList<>();
        ArrayList<Double> yData = new ArrayList<Double>();
        ArrayList<Integer> xData = new ArrayList<Integer>();

        int k = 0;
        for (int i = 0; i < number; i++) {
            k++;
            if (k == sizeBlock) {
                k = 0;
                repository.save(baseObject100);
                baseObject100.removeAll(baseObject100);

                System.out.println("# "+(i+1)+ " time = "+(System.currentTimeMillis() - timeSaveBlock));

                timeSaveBlock = System.currentTimeMillis();
                yData.add(Double.valueOf(timeSaveBlock/1000));
                xData.add(i);
            }
            else{
                localObject.setId(counter.incrementAndGet());
                baseObject100.add(objectGen.baseObjectFillParams(localObject));
            }

        }
        repository.save(baseObject100);
        baseObject100.removeAll(baseObject100);

        timeSaveAllObject = System.currentTimeMillis() - timeSaveAllObject;

//        XYChart rezultChart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData);

//        return new SwingWrapper(rezultChart).displayChart();

        return " Time records by block ( "+ number+ " base object, size block "+ sizeBlock+ " ) = "+ (double)timeSaveAllObject/1000;


    }

}
