package ma.enset.patientsmvc.security;

import ma.enset.patientsmvc.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration//spring va instancier les classes avec l'annotation Configuration avant tous
@EnableWebSecurity//activer la securiter web
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //on precise comment spring security va cercher les users et les roles
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        /*
        //inMemoryAuthentication est tres pratique pour tester les applications
        auth.inMemoryAuthentication()//les users qui ont le droit d'acceder sont stocket dans la memoire
                .withUser("user1").password(passwordEncoder.encode("1234")).roles("USER");
        auth.inMemoryAuthentication().withUser("user2").password(passwordEncoder.encode("2345")).roles("USER");

        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("1111")).roles("USER","ADMIN");


 auth.userDetailsService(new UserDetailsService() {
           @Override
           public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
               return null;
           }
       });

       auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT username as principal,password as credentials,active FROM users WHERE username=?")
                .authoritiesByUsernameQuery("SELECT username as principal,role as role FROM  user_role WHERE username=?")
                .rolePrefix("ROLE_")
                .passwordEncoder(passwordEncoder);

       */
        //user detail service permet a la couche service de d'occuper des l'authentification

        auth.userDetailsService(userDetailsService);

        }

    @Override//pour spicifier les droits d'acces
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeHttpRequests().antMatchers("/").permitAll();
        http.authorizeHttpRequests().antMatchers("/admin/**").hasAuthority("ADMIN");
        http.authorizeHttpRequests().antMatchers("/user/**").hasAuthority("USER");
        http.authorizeHttpRequests().antMatchers("/webjars/**").permitAll();
        http.authorizeHttpRequests().anyRequest().authenticated();//tous les requetes nessecite une authentification
        http.exceptionHandling().accessDeniedPage("/403");

    }


}
