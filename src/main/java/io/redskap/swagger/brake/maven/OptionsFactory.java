package io.redskap.swagger.brake.maven;

import com.google.common.collect.ImmutableSet;
import io.redskap.swagger.brake.runner.Options;
import io.redskap.swagger.brake.runner.OutputFormat;

public class OptionsFactory {
    public static Options create(RunnerParameter parameter) {
        Options options = new Options();
        options.setMavenRepoUrl(parameter.getMavenRepoUrl());
        options.setMavenRepoUsername(parameter.getMavenRepoUsername());
        options.setMavenRepoPassword(parameter.getMavenRepoPassword());
        options.setGroupId(parameter.getGroupId());
        options.setArtifactId(parameter.getArtifactId());
        options.setNewApiPath(parameter.getNewApi());
        options.setOutputFilePath(parameter.getOutputFilePath());
        options.setOutputFormats(ImmutableSet.of(resolveOutputFormat(parameter)));
        return options;
    }

    private static OutputFormat resolveOutputFormat(RunnerParameter parameter) {
        return OutputFormat.valueOf(parameter.getOutputFormat().toUpperCase());
    }
}
