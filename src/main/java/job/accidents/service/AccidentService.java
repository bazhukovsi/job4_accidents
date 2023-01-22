package job.accidents.service;

import job.accidents.model.Accident;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface AccidentService {
    Accident save(Accident accident);

    Collection<Accident> findAll();

    Optional<Accident> findById(int id);

    boolean update(Accident accident);

    boolean deleteById(int id);
}
