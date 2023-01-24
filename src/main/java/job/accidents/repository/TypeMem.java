package job.accidents.repository;

import job.accidents.model.AccidentType;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class TypeMem implements TypeInt {
    private final Map<Integer, AccidentType> types = new HashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(6);

    public TypeMem() {
        types.put(1, new AccidentType(1, "Две машины"));
        types.put(2, new AccidentType(2, "Машина и человек"));
        types.put(3, new AccidentType(3, "Машина и велосипед"));
        types.put(4, new AccidentType(4, "Грузовик и машина"));
        types.put(5, new AccidentType(5, "Мотоцикл и машина"));
    }

    public Set<AccidentType> findAll() {
        return new HashSet<>(types.values());
    }

    public void create(AccidentType type) {
        type.setId(nextId.incrementAndGet());
        types.put(type.getId(), type);
    }

    public void update(AccidentType type) {
        types.replace(type.getId(), type);
    }

    public Optional<AccidentType> findById(int id) {
        AccidentType type = types.get(id);
        if (type == null) {
            return Optional.empty();
        }
        return Optional.of(type);
    }
}
