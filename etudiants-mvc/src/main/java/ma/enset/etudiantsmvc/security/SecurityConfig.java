package ma.enset.etudiantsmvc.security;

import ma.enset.etudiantsmvc.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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



        auth.userDetailsService(userDetailsService);

        }

    @Override//pour spicifier les droits d'acces
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeHttpRequests().antMatchers("/").permitAll();
        http.authorizeHttpRequests().antMatchers("/admin/**").hasAuthority("ADMIN");
        http.authorizeHttpRequests().antMatchers("/user/**").hasAuthority("USER");
        http.authorizeHttpRequests().antMatchers("/webjars/**").permitAll();
        http.authorizeHttpRequests().antMatchers("/assets/**").permitAll();
        http.authorizeHttpRequests().anyRequest().authenticated();//tous les requetes nessecite une authentification
        http.exceptionHandling().accessDeniedPage("/403");

    }


}
