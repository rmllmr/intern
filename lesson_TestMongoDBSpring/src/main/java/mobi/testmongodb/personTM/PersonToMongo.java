package mobi.testmongodb.personTM;

/**
 * Created by user on 20.03.2017.
 */
import mobi.testmongodb.sequence.SequenceException;

public interface PersonToMongo {

    void save(String name) throws SequenceException;

}