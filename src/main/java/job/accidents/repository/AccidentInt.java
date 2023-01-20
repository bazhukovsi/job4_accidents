package job.accidents.repository;

import job.accidents.model.Accident;

import java.util.Collection;

public interface AccidentInt {
        Accident save(Accident accident);

        Collection<Accident> findAll();
}
