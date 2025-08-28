package pl.coderslab.view;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.Taboret;
import pl.coderslab.TaboretRepository;

@Controller
@RequestMapping("/view/taborets")
public class TaboretViewController {

    private final TaboretRepository taboretRepository;

    public TaboretViewController(TaboretRepository taboretRepository) {
        this.taboretRepository = taboretRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("taborets", taboretRepository.findAll());
        return "taborets/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("taboret", new Taboret());
        return "taborets/form";
    }

    @PostMapping("/new")
    public String create(@Valid Taboret taboret, BindingResult result, RedirectAttributes ra) {
        if (result.hasErrors()) {
            return "taborets/form";
        }
        taboretRepository.save(taboret);
        ra.addFlashAttribute("message", "Taboret utworzony.");
        return "redirect:/view/taborets";
    }


    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        if (!taboretRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        taboretRepository.deleteById(id);
        ra.addFlashAttribute("message", "Taboret usunięty.");
        return "redirect:/view/taborets";
    }


}
