package io.redskap.swagger.brake.maven;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class RunnerParameter {
    private String newApi;
    private String mavenRepoUrl;
    private String mavenRepoUsername;
    private String mavenRepoPassword;
    private String groupId;
    private String artifactId;
    private String outputFilePath;
    private String outputFormat;
    private Boolean deprecatedApiDeletionAllowed;
    private String betaApiExtensionName;
    private String apiFilename;
}
