package job.accidents.service;

import job.accidents.model.Rule;
import job.accidents.repository.RuleMem;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class SimpleRuleService implements RuleService {
    RuleMem ruleMem;

    public SimpleRuleService(RuleMem ruleMem) {
        this.ruleMem = ruleMem;
    }

    @Override
    public Set<Rule> findAll() {
        return ruleMem.findAll();
    }

    @Override
    public void create(Rule rule) {
         ruleMem.create(rule);
    }

    @Override
    public void update(Rule rule) {
         ruleMem.update(rule);
    }

    @Override
    public Optional<Rule> findById(int id) {
        return ruleMem.findById(id);
    }
}
