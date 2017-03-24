package hello;

/**
 * Created by user on 24.03.2017.
 */
public class AverTemp {

    private String id;
    private double averTemp;

    public AverTemp() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAverTemp() {
        return averTemp;
    }

    public void setAverTemp(double averTemp) {
        this.averTemp = averTemp;
    }

    @Override
    public String toString() {
        return "{ " + averTemp + " }";
    }
}
