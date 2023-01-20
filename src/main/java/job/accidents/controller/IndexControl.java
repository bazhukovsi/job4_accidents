package job.accidents.controller;

import job.accidents.service.SimpleAccidentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexControl {

    SimpleAccidentService simpleAccidentService;

    public IndexControl(SimpleAccidentService simpleAccidentService) {
        this.simpleAccidentService = simpleAccidentService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("user", "Petr Arsentev");
        model.addAttribute("accidents", simpleAccidentService.findAll());
        return "index";
    }
}
