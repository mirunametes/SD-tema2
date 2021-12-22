package ro.tuc.ds2020.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.SmartDevice;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface SmartDeviceRepository extends CrudRepository<SmartDevice,Integer> {
}