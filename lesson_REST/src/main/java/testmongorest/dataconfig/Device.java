package testmongorest.dataconfig;

import org.springframework.data.annotation.Id;
import testmongorest.dataconfig.enums.AppRole;
import testmongorest.dataconfig.enums.NetworkRole;
import testmongorest.dataconfig.enums.NetworkType;
import testmongorest.dataconfig.enums.RangingTechnology;

import java.util.Set;

public class Device{
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    public void setId(long id) {this.id = String.valueOf(id);}

    public void setId(String id) {this.id = id;}

    private long address;
    private short shortAddr;
    private short parentAddr;
    private byte anchorId;
    private NetworkRole networkRole;
    private NetworkType networkType;
    private AppRole appRole;
    private byte deviceState;
    private boolean activated;
    private boolean connected;
    private String customName;
    private String customType;
    private String hardwareName;
    private int softwareVersion;
    private double battery;
    private int rssi;
    private Set<RangingTechnology> rangingCapabilities;
    private long timestamp;

    public long getAddress() {
        return address;
    }

    public void setAddress(long address) {
        this.address = address;
    }

    public short getShortAddr() {
        return shortAddr;
    }

    public void setShortAddr(short shortAddr) {
        this.shortAddr = shortAddr;
    }

    public short getParentAddr() {
        return parentAddr;
    }

    public void setParentAddr(short parentAddr) {
        this.parentAddr = parentAddr;
    }

    public byte getAnchorId() {
        return anchorId;
    }

    public void setAnchorId(byte anchorId) {
        this.anchorId = anchorId;
    }

    public NetworkRole getNetworkRole() {
        return networkRole;
    }

    public void setNetworkRole(NetworkRole networkRole) {
        this.networkRole = networkRole;
    }

    public NetworkType getNetworkType() {
        return networkType;
    }

    public void setNetworkType(NetworkType networkType) {
        this.networkType = networkType;
    }

    public AppRole getAppRole() {
        return appRole;
    }

    public void setAppRole(AppRole appRole) {
        this.appRole = appRole;
    }

    public byte getDeviceState() {
        return deviceState;
    }

    public void setDeviceState(byte deviceState) {
        this.deviceState = deviceState;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getCustomType() {
        return customType;
    }

    public void setCustomType(String customType) {
        this.customType = customType;
    }

    public String getHardwareName() {
        return hardwareName;
    }

    public void setHardwareName(String hardwareName) {
        this.hardwareName = hardwareName;
    }

    public int getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(int softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public double getBattery() {
        return battery;
    }

    public void setBattery(double battery) {
        this.battery = battery;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public Set<RangingTechnology> getRangingCapabilities() {
        return rangingCapabilities;
    }

    public void setRangingCapabilities(Set<RangingTechnology> rangingCapabilities) {
        this.rangingCapabilities = rangingCapabilities;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
