package ro.tuc.ds2020;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class SensorCSV {
    private LocalDateTime timestamp;
    private int sensorId;
    private float measurementValue;

    public SensorCSV(@JsonProperty("timestamp") LocalDateTime timestamp, @JsonProperty("sensorId") int sensorId, @JsonProperty("measurementValue") float measurementValue) {
        this.timestamp = timestamp;
        this.sensorId = sensorId;
        this.measurementValue = measurementValue;

    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public float getMeasurementValue() {
        return measurementValue;
    }

    public void setMeasurementValue(float measurementValue) {
        this.measurementValue = measurementValue;
    }

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

}
