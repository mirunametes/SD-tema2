package ro.tuc.ds2020.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.ClientUser;

import javax.transaction.Transactional;
import java.util.Optional;



@Repository
@Transactional
public interface ClientUserRepository extends CrudRepository<ClientUser,Integer> {
    ClientUser getById(Integer id);

    Optional<ClientUser> findUserByName(String userName);

    Optional<ClientUser> findUserByUsername(String username);

    //Optional<User> findById(Integer id);
}

