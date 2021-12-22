package ro.tuc.ds2020.dtos;

import java.time.LocalDateTime;

public class ConsumtionDTO {

    private Integer id;
    private LocalDateTime date;
    private float energyConsumed;
    private Integer sensorId;

    public ConsumtionDTO(Integer id, LocalDateTime date, float energyConsumed) {
        this.id = id;
        this.date = date;
        this.energyConsumed = energyConsumed;
    }

    public ConsumtionDTO(LocalDateTime date, float energyConsumed) {
        this.date = date;
        this.energyConsumed = energyConsumed;
    }

    public ConsumtionDTO(Integer id, LocalDateTime date, float energyConsumed, Integer sensorId) {
        this.id = id;
        this.date = date;
        this.energyConsumed = energyConsumed;
        this.sensorId=sensorId;

    }

    public ConsumtionDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public float getEnergyConsumed() {
        return energyConsumed;
    }

    public void setEnergyConsumed(float energyConsumed) {
        this.energyConsumed = energyConsumed;
    }

    public Integer getSmartSensorId() {
        return sensorId;
    }

    public void setSmartSensorId(Integer smartSensorId) {
        this.sensorId = smartSensorId;
    }
}
