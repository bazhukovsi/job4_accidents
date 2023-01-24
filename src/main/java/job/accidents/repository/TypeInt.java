package job.accidents.repository;

import job.accidents.model.AccidentType;

import java.util.Optional;
import java.util.Set;

public interface TypeInt {
    public Set<AccidentType> findAll();

    public void create(AccidentType type);

    public void update(AccidentType type);

    public Optional<AccidentType> findById(int id);

}
