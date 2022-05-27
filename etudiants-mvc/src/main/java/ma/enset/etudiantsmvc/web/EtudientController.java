package ma.enset.etudiantsmvc.web;

import lombok.AllArgsConstructor;
import ma.enset.etudiantsmvc.entities.Etudiant;
import ma.enset.etudiantsmvc.repositories.EtudiantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@Controller
@AllArgsConstructor
public class EtudientController {
    private EtudiantRepository etudiantRepository;

    @GetMapping(path = "/user/index")
    public String etudiants(Model model,
                            @RequestParam(name = "page", defaultValue = "0") int page,
                            @RequestParam(name = "size", defaultValue = "5") int size,
                            @RequestParam(name = "keyword", defaultValue = "") String keyword
    ) {
        Page<Etudiant> etudiantPage = etudiantRepository.findByNomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("listEtudiants", etudiantPage.getContent());
        model.addAttribute("pages", new int[etudiantPage.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "etudiants";
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }
    @CrossOrigin
    @GetMapping("/etudiants")
    @ResponseBody
    public List<Etudiant> etudiantList() {
        return etudiantRepository.findAll();
    }


    @PostMapping("/admin/save")
    public String save(Model model, @Valid Etudiant etudiant, BindingResult bindResult,
                       @RequestParam(defaultValue = "") String keyword,
                       @RequestParam(defaultValue = "0") int page) {
        if (bindResult.hasErrors()) return "formEtudiants";
        etudiantRepository.save(etudiant);
        return "redirect:/user/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("/admin/formEtudiants")
    public String formEtudiants(Model model) {
        model.addAttribute("etudiant", new Etudiant());
        return "formEtudiants";
    }

    @GetMapping("/admin/delete")
    public String delete(long id, String keyword, int page) {
        etudiantRepository.deleteById(id);
        return "redirect:/user/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("/admin/editEtudiants")
    public String editEtudiants(Model model, Long id, String keyword, int page) {
        Etudiant etudiant = etudiantRepository.findById(id).orElse(null);
        if (etudiant == null) throw new RuntimeException("etudiant introuvable");
        model.addAttribute("etudiant", etudiant);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "editEtudiants";
    }


}
