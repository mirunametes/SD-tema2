package ro.tuc.ds2020.dtos;
public class SmartDeviceDTO {

    private Integer id;
    private String description;
    private String address;
    private float maxEnergyConsumtion;
    private float avgConsumtion;
    //private String sensorName;
    private String userName;

    public SmartDeviceDTO(Integer id, String description, String address, float maxEnergyConsumtion, float avgConsumtion, String userName) {
        this.id = id;
        this.description = description;
        this.address = address;
        this.maxEnergyConsumtion = maxEnergyConsumtion;
        this.avgConsumtion = avgConsumtion;
        // this.sensorName = sensorName;
        this.userName = userName;
    }

    public SmartDeviceDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getMaxEnergyConsumtion() {
        return maxEnergyConsumtion;
    }

    public void setMaxEnergyConsumtion(float maxEnergyConsumtion) {
        this.maxEnergyConsumtion = maxEnergyConsumtion;
    }

    public float getAvgConsumtion() {
        return avgConsumtion;
    }

    public void setAvgConsumtion(float avgConsumtion) {
        this.avgConsumtion = avgConsumtion;
    }

    /*
    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

     */

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

