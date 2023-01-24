package job.accidents.service;

import job.accidents.model.AccidentType;
import job.accidents.repository.TypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleTypeService implements TypeService {
    private TypeRepository typeRepository;

    public SimpleTypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public List<AccidentType> getAllTypes() {
        return typeRepository.getAllTypes();
    }

    @Override
    public AccidentType findById(int id) {
        return typeRepository.findById(id);
    }
}
