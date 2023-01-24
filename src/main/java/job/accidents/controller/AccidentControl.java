package job.accidents.controller;

import jakarta.servlet.http.HttpServletRequest;
import job.accidents.model.Accident;
import job.accidents.model.Rule;
import job.accidents.service.SimpleAccidentService;
import job.accidents.service.SimpleRuleService;
import job.accidents.service.SimpleTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class AccidentControl {
    private final SimpleAccidentService accidentService;
    private final SimpleTypeService typeService;
    private final SimpleRuleService rulesService;

    /**
     * Вывод таблицы инцидентов
     *
     * @param model
     * @return
     */

    @GetMapping("/accidents")
    public String getListAccident(Model model) {
        model.addAttribute("accidents", accidentService.findAll());
        return "accidents/list";
    }

    /**
     * Ввод инцидента
     *
     * @return
     */

    @GetMapping("/addAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("rules", rulesService.findAll());
        return "accidents/createAccident";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Accident accident, Model model,
                         @RequestParam("type.id") int id, HttpServletRequest req) {
        accident.setType(typeService.findById(id).get());
        String[] rules = req.getParameterValues("rIds");
        Set<Rule> ruleSet = getRulesSet(rules);
        accident.setRules(ruleSet);
        accidentService.save(accident);
        return "redirect:/accidents";
    }

    /**
     * Редактирование инцидента (все поля)
     *
     * @param model
     * @param id
     * @return
     */

    @GetMapping("/accidents/{id}")
    public String getById(Model model,  @PathVariable int id) {
        Optional accidentOptional = accidentService.findById(id);
        if (accidentOptional.isEmpty()) {
            model.addAttribute("message", "Инцидент с указанным идентификатором не найден!");
            return "errors/404";
        }
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("accident", accidentOptional.get());
        return "accidents/editAccident";

    }

    @PostMapping("/updateAccident")
    public String update(@ModelAttribute Accident accident, Model model, @RequestParam("id") int id) {
        accident.setType(typeService.findById(id).get());
        var isUpdated = accidentService.update(accident);
        if (!isUpdated) {
            model.addAttribute("message", "Инцидент с указанным идентификатором не найден!");
            return "errors/404";
        }
        return "redirect:/accidents";
    }

    /**
     * Удаление инцидента
     *
     * @param model
     * @param id
     * @return
     */

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        Boolean isDeleted = accidentService.deleteById(id);
        if (!isDeleted) {
            model.addAttribute("message", "Инцидент с указанным идентификатором не найден!");
            return "errors/404";
        }
        return "redirect:/accidents";
    }

    /**
     * Редактирование инцидента (только название инцидента)
     *
     * @param model
     * @param id
     * @return
     */

    @GetMapping("/formUpdateAccident")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidentService.findById(id).get());
        return "accidents/formUpdateAccident";
    }

    @PostMapping("/updateAccidentName")
    public String save(@ModelAttribute Accident accident) {
        accidentService.update(accident);
        return "redirect:/index";
    }

    /**
     * Получить набор статей по инциденту
     * @param rules
     * @return
     */

    public Set<Rule> getRulesSet(String[] rules) {
        Set<Rule> retVal = new HashSet<>();
        for (String id : rules) {
            int idRule = Integer.parseInt(id);
            Optional<Rule> ruleOptional = rulesService.findById(idRule);
            ruleOptional.ifPresent(retVal::add);
        }
        return retVal;
    }
}
