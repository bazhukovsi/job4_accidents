package job.accidents.service;

import job.accidents.model.AccidentType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TypeService {
    private final Map<Integer, AccidentType> types = new HashMap<>();

    public TypeService() {
        types.put(1, new AccidentType(1, "Две машины"));
        types.put(2, new AccidentType(2, "Машина и человек"));
        types.put(3, new AccidentType(3, "Машина и велосипед"));
        types.put(4, new AccidentType(4, "Грузовик и машина"));
        types.put(5, new AccidentType(5, "Мотоцикл и машина"));
    }

    public List<AccidentType> getAllTypes() {
        return new ArrayList<>(types.values());
    }

    public AccidentType findById(int id) {
        return types.get(id);
    }
}
