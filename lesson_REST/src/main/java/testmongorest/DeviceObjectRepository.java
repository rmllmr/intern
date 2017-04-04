package testmongorest;

import org.springframework.data.mongodb.repository.MongoRepository;
import testmongorest.dataconfig.Device;
import testmongorest.dataconfig.Position;

/**
 * Created by user on 04.04.2017.
 */
public interface DeviceObjectRepository extends MongoRepository <Device, String> {
    public Device findById(String id);
}
