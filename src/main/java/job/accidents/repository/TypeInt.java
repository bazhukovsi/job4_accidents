package job.accidents.repository;

import job.accidents.model.AccidentType;
import java.util.List;

public interface TypeInt {
    public List<AccidentType> getAllTypes();

    public AccidentType findById(int id);

}
