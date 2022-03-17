package ma.enset.jpademo2.repositories;

import ma.enset.jpademo2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    User findByUsername(String userName);
}
