package job.accidents.service;

import job.accidents.model.Rule;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface RuleService {
    public Set<Rule> findAll();

    public void create(Rule rule);

    public void update(Rule rule);

    Optional<Rule> findById(int id);
}
