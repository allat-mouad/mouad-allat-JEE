package ma.enset.jpademo2.service;

import lombok.AllArgsConstructor;
import ma.enset.jpademo2.entities.Role;
import ma.enset.jpademo2.entities.User;
import ma.enset.jpademo2.repositories.RolesRepository;
import ma.enset.jpademo2.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RolesRepository rolesRepository;

    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public Role addNewRole(Role role) {
        return rolesRepository.save(role);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return rolesRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        User user=findUserByUserName(username);
        Role role=findRoleByRoleName(rolename);
        if(user.getRoles()!=null){
            user.getRoles().add(role);
            role.getUsers().add(user);
        }
      //  userRepository.save(user); no need


    }

    @Override

    public User authentificate(String username, String password) {

        User user =userRepository.findByUsername(username);

        if(user==null) throw  new RuntimeException("Bad credentials");
        if(user.getPassword().equals(password)){
            return  user;
        }
        throw  new RuntimeException("Bad credentials");
    }
}
