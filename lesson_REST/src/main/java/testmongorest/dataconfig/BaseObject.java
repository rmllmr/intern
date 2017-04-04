package testmongorest.dataconfig;

import org.springframework.data.annotation.Id;

/**
 * Created by user on 23.03.2017.
 */
public class BaseObject {

    @Id
    private String id;

    private long timeStamp;
    private long latitude;
    private long longitude;
    private boolean shock;
    private boolean button;
    private int temp;

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", timeStamp=" + timeStamp +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", shock=" + shock +
                ", button=" + button +
                ", temp=" + temp +
                '}';
    }

    public BaseObject() {
    }

    public void setId(long id) {this.id = String.valueOf(id);}
    public void setId(String id) {this.id = id;}

    public String getId() {
        return id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public boolean isShock() {
        return shock;
    }

    public void setShock(boolean shock) {
        this.shock = shock;
    }

    public boolean isButton() {
        return button;
    }

    public void setButton(boolean button) {
        this.button = button;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
}
