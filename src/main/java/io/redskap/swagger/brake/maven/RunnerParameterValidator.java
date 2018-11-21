package io.redskap.swagger.brake.maven;

import org.apache.commons.lang3.StringUtils;

public class RunnerParameterValidator {
    public void validate(RunnerParameter parameter) {
        if (StringUtils.isBlank(parameter.getMavenRepoUrl())) {
            throw new IllegalArgumentException("mavenRepoUrl must be set");
        }
        if (StringUtils.isBlank(parameter.getNewApi())) {
            throw new IllegalArgumentException("newApi must be set");
        }
        if (StringUtils.isBlank(parameter.getGroupId())) {
            throw new IllegalArgumentException("groupId must be set");
        }
        if (StringUtils.isBlank(parameter.getArtifactId())) {
            throw new IllegalArgumentException("artifactId must be set");
        }
        if (StringUtils.isBlank(parameter.getOutputFilePath())) {
            throw new IllegalArgumentException("outputFilePath must be set");
        }
        if (StringUtils.isBlank(parameter.getOutputFormat())) {
            throw new IllegalArgumentException("outputFormat must be set");
        }
    }
}
