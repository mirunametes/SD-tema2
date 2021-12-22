package ro.tuc.ds2020.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="sensor")
public class Sensor {
    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name="id", unique = true, nullable = false)
    private Integer id;

    @Column(name="description", unique = false, nullable = false, length = 100)
    private String description;

    @Column(name="maxValue", unique = false, nullable = false, length = 100)
    private String maxValue;


    @OneToMany(mappedBy="sensor")
    private List<Consumtion> consumtions;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "smartDevice_id", referencedColumnName = "id")
    private SmartDevice smartDevice;

    public Sensor(Integer id, String description, String maxValue) {
        this.id = id;
        this.description = description;
        this.maxValue = maxValue;
    }

    public Sensor(Integer id, String description, String maxValue, SmartDevice smartDevice) {
        this.id = id;
        this.description = description;
        this.maxValue = maxValue;
        this.smartDevice = smartDevice;
    }

    public Sensor() {
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

    public List<Consumtion> getConsumtions() {
        return consumtions;
    }

    public void setConsumtions(List<Consumtion> consumtions) {
        this.consumtions = consumtions;
    }

    public SmartDevice getSmartDevice() {
        return smartDevice;
    }

    public void setSmartDevice(SmartDevice smartDevice) {
        this.smartDevice = smartDevice;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }
}
