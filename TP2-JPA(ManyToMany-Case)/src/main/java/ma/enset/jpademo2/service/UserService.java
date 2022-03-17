package ma.enset.jpademo2.service;

import ma.enset.jpademo2.entities.Role;
import ma.enset.jpademo2.entities.User;

public interface UserService {
    User addNewUser(User user);
    User findUserByUserName(String userName);
    Role addNewRole(Role role);
    Role findRoleByRoleName(String roleName);
    void addRoleToUser(String username, String rolename);
    User authentificate(String username, String rolename);

}
