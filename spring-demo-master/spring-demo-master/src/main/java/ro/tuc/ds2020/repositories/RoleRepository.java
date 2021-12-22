package ro.tuc.ds2020.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.Role;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface RoleRepository extends CrudRepository<Role,Integer> {

    Optional<Role> findRoleByName(String name);

}
