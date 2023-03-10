package job.accidents.controller;

import job.accidents.service.SimpleAccidentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexControl {

    private SimpleAccidentService simpleAccidentService;

    public IndexControl(SimpleAccidentService simpleAccidentService) {
        this.simpleAccidentService = simpleAccidentService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("user", "Petr Arsentev");
        model.addAttribute("accidents", simpleAccidentService.findAll());
        return "index";
    }
}
