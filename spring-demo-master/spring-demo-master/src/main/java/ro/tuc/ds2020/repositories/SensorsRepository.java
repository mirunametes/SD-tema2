package ro.tuc.ds2020.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.Sensor;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface SensorsRepository extends CrudRepository<Sensor,Integer> {

    Optional<Sensor> findSensorById(Integer smartSensorId);

    Optional<Sensor> findByDescription(String sensorName);
}