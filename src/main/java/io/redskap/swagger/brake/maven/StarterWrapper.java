package io.redskap.swagger.brake.maven;

import java.util.Collection;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.runner.Options;
import io.redskap.swagger.brake.runner.Starter;

public class StarterWrapper {
    public Collection<BreakingChange> start(Options options) {
        return Starter.start(options);
    }
}
