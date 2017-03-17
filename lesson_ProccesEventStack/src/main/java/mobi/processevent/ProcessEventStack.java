package mobi.processevent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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

    public void addProcessEvent(){

    }

    public setValueLast(int value){
        this.last = value;

    }
}


class ListenToMe {

    private String variable = "Initial";
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void addListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public void setVariable(String newValue) {
        String oldValue = variable;
        variable = newValue;
        support.firePropertyChange("variable", oldValue, newValue);
    }
}

class MyListener implements PropertyChangeListener {

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        System.out.println("Property \"" + event.getPropertyName() + "\" has new value: " + event.getNewValue());
    }
}