package testmongorest.dataconfig;

/**
 * Created by user on 31.03.2017.
 */
public class Position {

        private static final long serialVersionUID = 1L;

        private String id;
        private long address;
        private int mappedPosition;
        private boolean hasMoved;
        private long positionAccuracy;
        private float accuracyRadius;
        private int state;
        private float x;
        private float y;
        private float z;
        private long timeStamp;

    @Override
    public String toString() {
        return "Position{" +
                "id='" + id + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(long id) {this.id = String.valueOf(id);}

    public void setId(String id) {this.id = id;}

    public long getAddress() {
        return address;
    }

    public void setAddress(long address) {
        this.address = address;
    }

    public int getMappedPosition() {
        return mappedPosition;
    }

    public void setMappedPosition(int mappedPosition) {
        this.mappedPosition = mappedPosition;
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public float getAccuracyRadius() {
        return accuracyRadius;
    }

    public void setAccuracyRadius(float accuracyRadius) {
        this.accuracyRadius = accuracyRadius;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public long getTimestamp() {
        return timeStamp;
    }

    public void setTimestamp(long timestamp) {

        this.timeStamp = timestamp;
    }
}


