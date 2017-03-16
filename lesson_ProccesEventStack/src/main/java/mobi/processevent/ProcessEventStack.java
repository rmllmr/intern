package mobi.processevent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 16.03.2017.
 */
public class ProcessEventStack <T>{

    private Map<Integer, T> eventStackId = new HashMap<Integer, T>();
    private Integer first;
    private Integer last;
    private int size;

}
