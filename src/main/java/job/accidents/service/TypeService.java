package job.accidents.service;

import job.accidents.model.AccidentType;

import java.util.Optional;
import java.util.Set;

public interface TypeService {
    public Set<AccidentType> findAll();

    public void create(AccidentType type);

    public void update(AccidentType type);

    public Optional<AccidentType> findById(int id);
}
