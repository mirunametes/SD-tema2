package ro.tuc.ds2020.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="consumtion")
public class Consumtion {
    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name="id", unique = true, nullable = false)
    private Integer id;

    @JsonFormat(pattern = "dd-mm-yyyy HH:mm:ss")
    @DateTimeFormat(pattern = "dd-mm-yyyy HH:mm:ss")
    @Column(name="date", unique = false, nullable = false)
    private LocalDateTime date;

    @Column(name="energyConsumed", unique = false, nullable = false, length = 100)
    private float energyConsumed;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="smartSensor_id", nullable=false)
    private Sensor sensor;


    public Consumtion(LocalDateTime date, float energyConsumed, Sensor sensor) {
        this.date = date;
        this.energyConsumed = energyConsumed;
        this.sensor = sensor;
    }

    public Consumtion(Integer id, LocalDateTime date, float energyConsumed, Sensor sensor) {
        this.id = id;
        this.date = date;
        this.energyConsumed = energyConsumed;
        this.sensor = sensor;
    }



    public Consumtion() {
    }


    public Consumtion(Integer id, LocalDateTime date, float energyConsumed) {
        this.id = id;
        this.date = date;
        this.energyConsumed = energyConsumed;
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

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
