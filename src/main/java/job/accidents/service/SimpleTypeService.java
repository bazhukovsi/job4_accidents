package job.accidents.service;

import job.accidents.model.AccidentType;
import job.accidents.repository.TypeMem;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class SimpleTypeService implements TypeService {
    private final TypeMem typeRepository;

    public SimpleTypeService(TypeMem typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public Set<AccidentType> findAll() {
        return typeRepository.findAll();
    }

    @Override
    public void create(AccidentType type) {
        typeRepository.create(type);
    }

    @Override
    public void update(AccidentType type) {
         typeRepository.update(type);
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return typeRepository.findById(id);
    }
}
