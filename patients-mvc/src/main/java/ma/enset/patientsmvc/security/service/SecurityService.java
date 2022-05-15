package ma.enset.patientsmvc.security.service;

import ma.enset.patientsmvc.security.entities.AppRole;
import ma.enset.patientsmvc.security.entities.AppUser;

public interface SecurityService {
    AppUser saveNewUser(String username,String password,String rePassword);
    AppRole saveNewRole(String rolename,String description);
    void AddRoleToUser(String username,String roleName);
    void removeRoleToUser(String username,String roleName);
    AppUser loadUserByUserName(String username);

}
