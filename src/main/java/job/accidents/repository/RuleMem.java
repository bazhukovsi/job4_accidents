package job.accidents.repository;

import job.accidents.model.Rule;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class RuleMem implements RuleInt {
    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>();
    private final AtomicInteger ids = new AtomicInteger(3);

    public RuleMem() {
        rules.put(1, new Rule(1, "Статья. 1"));
        rules.put(2, new Rule(2, "Статья. 2"));
        rules.put(3, new Rule(3, "Статья. 3"));
    }

    public Set<Rule> findAll() {
        return new HashSet<>(rules.values());
    }

    public void create(Rule rule) {
        rule.setId(ids.incrementAndGet());
        rules.put(rule.getId(), rule);
    }

    public void update(Rule rule) {
        rules.replace(rule.getId(), rule);
    }

    public Optional<Rule> findById(int id) {
        Rule rule = rules.get(id);
        if (rule == null) {
            return Optional.empty();
        }
        return Optional.of(rule);
    }
}
