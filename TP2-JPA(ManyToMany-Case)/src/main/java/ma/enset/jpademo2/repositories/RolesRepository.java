package ma.enset.jpademo2.repositories;

import ma.enset.jpademo2.entities.Role;
import ma.enset.jpademo2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role,Long> {
    Role findByRoleName(String roleName);
}
