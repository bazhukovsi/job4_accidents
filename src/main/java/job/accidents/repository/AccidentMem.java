package job.accidents.repository;

import job.accidents.model.Accident;
import job.accidents.model.AccidentType;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem implements AccidentInt {

    private final AtomicInteger nextId = new AtomicInteger(1);
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    AccidentType typeAccident1 = new AccidentType(1, "Две машины");
    AccidentType typeAccident2 = new AccidentType(2, "Машина и человек");
    AccidentType typeAccident3 = new AccidentType(3, "Машина и велосипед");

    public AccidentMem() {
        save(new Accident(0, "Парковка", "Неправильная парковка - Toyota LandCruiser",
                "г. Петрозаводск, Зайцева, 9А", typeAccident1));
        save(new Accident(0, "Стоянка на пешеходном переходе", "Тоyota RAV-4",
                "г. Петрозаводск Лесная, 6", typeAccident1));
        save(new Accident(0, "Парковка", "Неправильная парковка - Toyota LandCruiser",
                "г. Петрозаводск, Зайцева, 9А", typeAccident1));
        save(new Accident(0, "Стоянка на пешеходном переходе", "Тоyota RAV-4",
                "г. Петрозаводск Лесная, 6", typeAccident2));
        save(new Accident(0, "Парковка", "Неправильная парковка - Toyota LandCruiser",
                "г. Петрозаводск, Зайцева, 9А", typeAccident3));
        save(new Accident(0, "Стоянка на пешеходном переходе", "Reno Clio III",
                "г. Сегежа Монтажников, 5А", typeAccident2));
        save(new Accident(0, "Парковка", "Неправильная парковка - Toyota LandCruiser",
                "г. Петрозаводск, Зайцева, 9А", typeAccident2));
        save(new Accident(0, "Стоянка на пешеходном переходе", "Тоyota RAV-4",
                "г. Петрозаводск Лесная, 6", typeAccident3));
        save(new Accident(0, "Парковка", "Неправильная парковка - Toyota LandCruiser",
                "г. Петрозаводск, Зайцева, 9А", typeAccident2));
        save(new Accident(0, "Стоянка на пешеходном переходе", "Тоyota RAV-4",
                "г. Петрозаводск Лесная, 6", typeAccident3));
        save(new Accident(0, "Парковка", "Неправильная парковка - Toyota LandCruiser",
                "г. Петрозаводск, Зайцева, 9А", typeAccident2));
        save(new Accident(0, "Стоянка на пешеходном переходе", "Reno Clio III",
                "г. Сегежа Монтажников, 5А", typeAccident1));
    }

    @Override
    public Accident save(Accident accident) {
        accident.setId(nextId.getAndIncrement());
        accidents.put(accident.getId(), accident);
        return accident;
    }

    @Override
    public Collection<Accident> findAll() {
        return accidents.values();
    }

    @Override
    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }

    @Override
    public boolean update(Accident accident) {
        return accidents.computeIfPresent(accident.getId(),
                (id, oldAccident) ->
                        new Accident(oldAccident.getId(), accident.getName(), accident.getDescription(),
                                accident.getAddress(), accident.getType())) != null;
    }

    @Override
    public boolean deleteById(int id) {
        return accidents.remove(id) != null;
    }
}
