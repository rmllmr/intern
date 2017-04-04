package testmongorest.service;

import testmongorest.dataconfig.BaseObject;
import testmongorest.dataconfig.Device;
import testmongorest.dataconfig.Position;
import testmongorest.dataconfig.enums.AppRole;
import testmongorest.dataconfig.enums.NetworkRole;
import testmongorest.dataconfig.enums.NetworkType;

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

    @Override
    public Device deviceFillParams(Device objectPosition) {
        Random random = new Random();

        objectPosition.setAddress(random.nextLong());
        objectPosition.setActivated(random.nextBoolean());
        objectPosition.setAnchorId((byte) random.nextInt(256));
        objectPosition.setAppRole(AppRole.ANCHOR);
        objectPosition.setBattery(random.nextDouble());
        objectPosition.setConnected(random.nextBoolean());
        objectPosition.setDeviceState((byte) random.nextInt(256));
        objectPosition.setRssi(random.nextInt());
        objectPosition.setNetworkRole(NetworkRole.END_DEVICE);
        objectPosition.setNetworkType(NetworkType.GENERIC);
        objectPosition.setSoftwareVersion(random.nextInt(128));
        objectPosition.setTimestamp(System.currentTimeMillis());

        return objectPosition;
    }

}
