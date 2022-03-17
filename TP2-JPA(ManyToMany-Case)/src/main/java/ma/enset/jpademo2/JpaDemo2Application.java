package ma.enset.jpademo2;

import ma.enset.jpademo2.entities.Role;
import ma.enset.jpademo2.entities.User;
import ma.enset.jpademo2.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class JpaDemo2Application {

    public static void main(String[] args) {
        SpringApplication.run(JpaDemo2Application.class, args);
    }
    @Bean
    CommandLineRunner start(UserService userService){
        return args -> {
            User u=new User();
            u.setUsername("user1");
            u.setPassword("123224");
            userService.addNewUser(u);

            User u2=new User();
            u2.setUsername("admin");
            u2.setPassword("768688");
            userService.addNewUser(u2);

            Stream.of("ADMIN","USER","STUDENT").forEach(r->{
                Role role1=new Role();
                role1.setRoleName(r);
                userService.addNewRole(role1);
            });

            userService.addRoleToUser("user1","ADMIN");
            userService.addRoleToUser("user1","USER");
            userService.addRoleToUser("admin","USER");
            userService.addRoleToUser("admin","ADMIN");

            try {

                User user = userService.authentificate("user1","123224");

                System.out.println(user.getUserId());
                System.out.println(user.getUsername());

                user.getRoles().forEach(role ->
                        System.out.println("Role -->" + role));
            } catch (Exception err) {
                err.printStackTrace();
            }

        };
    }

}
