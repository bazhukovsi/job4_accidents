package job.accidents.service;

import job.accidents.model.AccidentType;

import java.util.List;

public interface TypeService {
    public List<AccidentType> getAllTypes();

    public AccidentType findById(int id);
}
