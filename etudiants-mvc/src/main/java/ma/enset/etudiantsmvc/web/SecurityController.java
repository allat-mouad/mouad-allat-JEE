package ma.enset.etudiantsmvc.web;

import org.springframework.web.bind.annotation.GetMapping;

public class SecurityController {
    @GetMapping("/403")
    public String notAuthorized(){
        return "403";
    }
}
