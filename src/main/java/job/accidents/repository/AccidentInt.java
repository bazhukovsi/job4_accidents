package job.accidents.repository;

import job.accidents.model.Accident;

import java.util.Collection;
import java.util.Optional;

public interface AccidentInt {
        Accident save(Accident accident);

        Collection<Accident> findAll();

        Optional<Accident> findById(int id);

        boolean update(Accident accident);

        boolean deleteById(int id);
}
