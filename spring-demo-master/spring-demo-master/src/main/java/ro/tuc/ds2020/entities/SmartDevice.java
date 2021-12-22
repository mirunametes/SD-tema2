package ro.tuc.ds2020.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="smartDevice")
public class SmartDevice {
    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name="id", unique = true, nullable = false)
    private Integer id;

    @Column(name="description", unique = false, nullable = false, length = 100)
    private String description;

    @Column(name="address", unique = false, nullable = false, length = 100)
    private String address;

    @Column(name="maxEnergyConsution", unique = false, nullable = false, length = 100)
    private float maxEnergyConsumtion;

    @Column(name="avgConsumtion", unique = false, nullable = false, length = 100)
    private float avgConsumtion;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor sensor;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private ClientUser clientUser;

    public SmartDevice(Integer id, String description, String address, float maxEnergyConsumtion, float avgConsumtion, Sensor sensor, ClientUser clientUser) {
        this.id = id;
        this.description = description;
        this.address = address;
        this.maxEnergyConsumtion = maxEnergyConsumtion;
        this.avgConsumtion = avgConsumtion;
        this.sensor = sensor;
        this.clientUser = clientUser;
    }

    public SmartDevice(Integer id, String description, String address, float maxEnergyConsumtion, float avgConsumtion) {
        this.id = id;
        this.description = description;
        this.address = address;
        this.maxEnergyConsumtion = maxEnergyConsumtion;
        this.avgConsumtion = avgConsumtion;

    }

    public SmartDevice() {
    }

    public SmartDevice( String description, String address, float maxEnergyConsumtion, float avgConsumtion) {

        this.description = description;
        this.address = address;
        this.maxEnergyConsumtion = maxEnergyConsumtion;
        this.avgConsumtion = avgConsumtion;

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

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public ClientUser getUser() {
        return clientUser;
    }

    public void setUser(ClientUser clientUser) {
        this.clientUser = clientUser;
    }
}
