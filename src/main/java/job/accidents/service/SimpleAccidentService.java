package job.accidents.service;

import job.accidents.model.Accident;
import job.accidents.repository.AccidentMem;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class SimpleAccidentService implements AccidentService {

    private AccidentMem accidentMem;

    public SimpleAccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    @Override
    public Accident save(Accident accident) {
        return accidentMem.save(accident);
    }

    @Override
    public Collection<Accident> findAll() {
        return accidentMem.findAll();
    }

    @Override
    public Optional<Accident> findById(int id) {
        return accidentMem.findById(id);
    }

    @Override
    public boolean update(Accident accident) {
        return accidentMem.update(accident);
    }

    @Override
    public boolean deleteById(int id) {
        return accidentMem.deleteById(id);
    }
}
