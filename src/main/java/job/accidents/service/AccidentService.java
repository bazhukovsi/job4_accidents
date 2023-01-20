package job.accidents.service;

import job.accidents.model.Accident;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface AccidentService {
    Accident save(Accident accident);

    Collection<Accident> findAll();
}
