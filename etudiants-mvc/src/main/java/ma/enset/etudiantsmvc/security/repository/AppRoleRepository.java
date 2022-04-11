package ma.enset.etudiantsmvc.security.repository;

import ma.enset.etudiantsmvc.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findByRoleName(String rolename);

}
