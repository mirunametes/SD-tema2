package ro.tuc.ds2020.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.Consumtion;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ConsumtionRepository extends JpaRepository<Consumtion,Integer>, CrudRepository<Consumtion,Integer> {

}
