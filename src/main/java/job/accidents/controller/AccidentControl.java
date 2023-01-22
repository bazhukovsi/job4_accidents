package job.accidents.controller;

import job.accidents.model.Accident;
import job.accidents.service.SimpleAccidentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class AccidentControl {
    private final SimpleAccidentService accidentService;

    /**
     * Вывод таблицы инцидентов
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
     * @return
     */

    @GetMapping("/addAccident")
    public String viewCreateAccident() {
        return "accidents/createAccident";
    }

    @PostMapping("/saveAccident")
    public String create(@ModelAttribute Accident accident, Model model) {
        try {
            accidentService.save(accident);
            return "redirect:/accidents";
        } catch (Exception exception) {
            model.addAttribute("message", exception.getMessage());
            return "errors/404";
        }
    }

    /**
     * Редактирование инцидента
     * @param model
     * @param id
     * @return
     */

    @GetMapping("/accidents/{id}")
    public String getById(Model model, @PathVariable int id) {
        Optional accidentOptional = accidentService.findById(id);
        if (accidentOptional.isEmpty()) {
            model.addAttribute("message", "Инцидент с указанным идентификатором не найден!");
            return "errors/404";
        }
        model.addAttribute("accident", accidentOptional.get());
        return "accidents/saveAccident";

    }

    @PostMapping("/updateAccident")
    public String update(@ModelAttribute Accident accident, Model model) {
        try {
            var isUpdated = accidentService.update(accident);
            if (!isUpdated) {
                model.addAttribute("message", "Инцидент с указанным идентификатором не найден!");
                return "errors/404";
            }
            return "redirect:/accidents";
        } catch (Exception exception) {
            model.addAttribute("message", exception.getMessage());
            return "errors/404";
        }
    }

    /**
     * Удаление инцидента
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
}
