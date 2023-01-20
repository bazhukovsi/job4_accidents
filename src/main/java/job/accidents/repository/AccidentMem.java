package job.accidents.repository;

import job.accidents.model.Accident;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem implements AccidentInt {

    private final AtomicInteger nextId = new AtomicInteger(1);
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public AccidentMem() {
        save(new Accident(0, "Парковка", "Неправильная парковка - Toyota LandCruiser",
                "г. Петрозаводск, Зайцева, 9А"));
        save(new Accident(0, "Стоянка на пешеходном переходе", "Тоyota RAV-4",
                "г. Петрозаводск Лесная, 6"));
        save(new Accident(0, "Парковка", "Неправильная парковка - Toyota LandCruiser",
                "г. Петрозаводск, Зайцева, 9А"));
        save(new Accident(0, "Стоянка на пешеходном переходе", "Тоyota RAV-4",
                "г. Петрозаводск Лесная, 6"));
        save(new Accident(0, "Парковка", "Неправильная парковка - Toyota LandCruiser",
                "г. Петрозаводск, Зайцева, 9А"));
        save(new Accident(0, "Стоянка на пешеходном переходе", "Reno Clio III",
                "г. Сегежа Монтажников, 5А"));
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
}
