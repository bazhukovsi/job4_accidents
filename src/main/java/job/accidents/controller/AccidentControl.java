package job.accidents.controller;

import job.accidents.model.Accident;
import job.accidents.model.AccidentType;
import job.accidents.service.SimpleAccidentService;
import job.accidents.repository.TypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class AccidentControl {
    private final SimpleAccidentService accidentService;
    private final TypeRepository typeRepository;

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
        model.addAttribute("types", typeRepository.getAllTypes());
        return "accidents/createAccident";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Accident accident, Model model, @RequestParam("type.id") int id) {
        accident.setType(typeRepository.findById(id));
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
    public String getById(Model model, @PathVariable int id) {
        Optional accidentOptional = accidentService.findById(id);
        if (accidentOptional.isEmpty()) {
            model.addAttribute("message", "Инцидент с указанным идентификатором не найден!");
            return "errors/404";
        }
        AccidentType accidentType = typeRepository.findById(accidentService.findById(id).get().getType().getId());
        model.addAttribute("types", typeRepository.getAllTypes());
        model.addAttribute("typeID", accidentType.getId());
        model.addAttribute("accident", accidentOptional.get());
        return "accidents/editAccident";

    }

    @PostMapping("/updateAccident")
    public String update(@ModelAttribute Accident accident, Model model, @RequestParam("type.id") int id) {
        accident.setType(typeRepository.findById(id));
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

}
