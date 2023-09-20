package ch.cern.todo.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class MetricsService {
    private final Counter taskAddedCounter;
    private final Counter taskDeletedCounter;
    private final Counter taskCategoryAddedCounter;
    private final Counter taskCategoryDeletedCounter;

    public MetricsService(MeterRegistry meterRegistry) {
        super();
        this.taskAddedCounter = Counter.builder("taskAddedCounter")
                .description("nb task added")
                .register(meterRegistry);
        this.taskDeletedCounter = Counter.builder("taskCategoryDeletedCounter")
                .description("nb task deleted")
                .register(meterRegistry);
        this.taskCategoryAddedCounter = Counter.builder("taskCategoryAddedCounter")
                .description("nb taskCategory added")
                .register(meterRegistry);
        this.taskCategoryDeletedCounter = Counter.builder("taskCategoryDeletedCounter")
                .description("nb taskCategory Deleted")
                .register(meterRegistry);
    }

    public void incrementTaskAddedCounter() {
        taskAddedCounter.increment();
    }

    public void incrementTaskCategoryAddedCounter() {
        taskCategoryAddedCounter.increment();
    }

    public void incrementTaskDeletedCounter() {
        taskAddedCounter.increment();
    }

    public void incrementTaskCategoryDeletedCounter() {
        taskCategoryAddedCounter.increment();
    }
}
