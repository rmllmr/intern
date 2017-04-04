package testmongorest.service;

import testmongorest.dataconfig.BaseObject;
import testmongorest.dataconfig.Position;

import java.util.Random;

/**
 * Created by user on 23.03.2017.
 */
public class FillObjectParams implements BaseObjectGenerator {

    public FillObjectParams() {

    }


    @Override
    public BaseObject baseObjectFillParams(BaseObject objectFill) {
        Random random = new Random();

        objectFill.setTemp(random.nextInt(37));
        objectFill.setLatitude(random.nextInt(1000));
        objectFill.setLongitude(random.nextInt(1000));
        objectFill.setTimeStamp(System.currentTimeMillis());
        objectFill.setButton(random.nextBoolean());
        objectFill.setShock(random.nextBoolean());

        return objectFill;
    }

    @Override
    public Position positionFillParams(Position objectPosition) {
        Random random = new Random();

        objectPosition.setAddress(random.nextLong());
        objectPosition.setAccuracyRadius(random.nextFloat());
        objectPosition.setHasMoved(random.nextBoolean());
        objectPosition.setState(random.nextInt(500));
        objectPosition.setMappedPosition(random.nextInt(500));
        objectPosition.setX(random.nextFloat());
        objectPosition.setY(random.nextFloat());
        objectPosition.setZ(random.nextFloat());
        objectPosition.setTimestamp(System.currentTimeMillis());

        return objectPosition;
    }

}
