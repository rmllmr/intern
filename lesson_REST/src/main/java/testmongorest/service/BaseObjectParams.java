package testmongorest.service;

import testmongorest.dataconfig.BaseObject;

import java.util.Random;

/**
 * Created by user on 23.03.2017.
 */
public class BaseObjectParams implements BaseObjectGenerator {

    public BaseObjectParams() {

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
}
