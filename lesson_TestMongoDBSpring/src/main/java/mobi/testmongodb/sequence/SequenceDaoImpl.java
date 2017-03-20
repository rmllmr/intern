package mobi.testmongodb.sequence;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * Created by user on 20.03.2017.
 */
public class SequenceDaoImpl implements SequenceDao{

    @Autowired
    private MongoOperations mongoOperation;

    @Override
    public String getNextSequenceId(String key) throws SequenceException {

        //get sequence id
        Query query = new Query(Criteria.where("_id").is(key));

        //increase sequence id by 1
        Update update = new Update();
        update.inc("seq", 1);

        //return new increased id
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);

        //this is the magic happened.
        Person personId =
                mongoOperation.findAndModify(query, update, options, Person.class);

        //if no id, tthrows SequenceException
        //optional, just a way to tell user when the sequence id is failed to generate.
        if (personId == null) {
            throw new SequenceException("Unable to get sequence id for key : " + key);
        }

        return personId.getFirstName();

    }
}
