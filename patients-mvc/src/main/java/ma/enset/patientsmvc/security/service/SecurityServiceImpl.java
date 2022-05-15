package ma.enset.patientsmvc.security.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.patientsmvc.security.entities.AppRole;
import ma.enset.patientsmvc.security.entities.AppUser;
import ma.enset.patientsmvc.security.repository.AppRoleRepository;
import ma.enset.patientsmvc.security.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class SecurityServiceImpl implements SecurityService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder mypasswordEncoder;



    @Override
    public AppUser saveNewUser(String username, String password, String rePassword) {
        if(!password.equals(rePassword)) throw new RuntimeException("passwords not match");
        String hashedPWD=mypasswordEncoder.encode(password);
        AppUser appUser=new AppUser();
        appUser.setUserId(UUID.randomUUID().toString());
        appUser.setUsername(username);
        appUser.setPassword(hashedPWD);
        appUser.setActive(true);
        AppUser savedAppUser=appUserRepository.save(appUser);
        return savedAppUser;

    }

    @Override
    public AppRole saveNewRole(String rolename, String description) {
        AppRole appRole=appRoleRepository.findByRoleName(rolename);
        if(appRole!=null)throw new RuntimeException("role already exists");
        appRole=new AppRole();
        appRole.setRoleName(rolename);
        appRole.setDescription(description);
        AppRole savedAppRole=appRoleRepository.save(appRole);
        return savedAppRole;
    }

    @Override
    public void AddRoleToUser(String username, String roleName) {
        AppUser appUser=appUserRepository.findByUsername(username);
        if(appUser==null)throw new RuntimeException("user not found");
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        if(appRole==null)throw new RuntimeException("role not found");
        appUser.getRoleList().add(appRole);

    }

    @Override
    public void removeRoleToUser(String username, String roleName) {
        AppUser appUser=appUserRepository.findByUsername(username);
        if(appUser==null)throw new RuntimeException("user not found");
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        if(appRole==null)throw new RuntimeException("role not found");
        appUser.getRoleList().remove(appRole);
    }

    @Override
    public AppUser loadUserByUserName(String username) {
        return appUserRepository.findByUsername(username);
    }
}
