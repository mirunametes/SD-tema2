package ro.tuc.ds2020.dtos;

public class SmartDeviceAdminViewDTO  implements SmartDeviceViewDTO{
    private String description;
    private String address;
    private float maxEnergyConsumtion;
    private float avgConsumtion;
    private String sensorDescription;
    private String userName;

    public SmartDeviceAdminViewDTO(String description, String address, float maxEnergyConsumtion, float avgConsumtion, String sensorDescription, String userName) {
        this.description = description;
        this.address = address;
        this.maxEnergyConsumtion = maxEnergyConsumtion;
        this.avgConsumtion = avgConsumtion;
        this.sensorDescription = sensorDescription;
        this.userName = userName;
    }

    public SmartDeviceAdminViewDTO() {
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

    public String getSensorDescription() {
        return sensorDescription;
    }

    public void setSensorDescription(String sensorDescription) {
        this.sensorDescription = sensorDescription;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
