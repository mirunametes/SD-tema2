package ro.tuc.ds2020.dtos;

public class SensorDTO {

    private Integer id;
    private String description;
    private String maxValue;
    private Integer smartDeviceId;

    public SensorDTO(Integer id, String description, String maxValue, Integer smartDeviceId) {
        this.id = id;
        this.description = description;
        this.maxValue = maxValue;
        this.smartDeviceId = smartDeviceId;
    }

    public SensorDTO(Integer id, String description, String maxValue) {
        this.id = id;
        this.description = description;
        this.maxValue = maxValue;
    }

    public SensorDTO() {
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

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public Integer getSmartDeviceId() {
        return smartDeviceId;
    }

    public void setSmartDeviceId(Integer smartDeviceId) {
        this.smartDeviceId = smartDeviceId;
    }
}
