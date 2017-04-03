package testmongorest.service;

import testmongorest.dataconfig.BaseObject;
import testmongorest.dataconfig.Position;

/**
 * Created by user on 23.03.2017.
 */
public interface BaseObjectGenerator {

    BaseObject baseObjectFillParams(BaseObject object);
    Position positionFillParams(Position object);

}
