package mobi.testmongodb.sequence;

public interface SequenceDao {

    String getNextSequenceId(String key) throws SequenceException;

}
