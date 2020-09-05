package io.redskap.swagger.brake.maven;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Collection;

@Getter
@ToString
@Builder
public class RunnerParameter {
    private String oldApi;
    private String newApi;
    private String mavenRepoUrl;
    private String mavenSnapshotRepoUrl;
    private String mavenRepoUsername;
    private String mavenRepoPassword;
    private String groupId;
    private String artifactId;
    private String currentVersion;
    private String outputFilePath;
    private Collection<String> outputFormats;
    private Boolean deprecatedApiDeletionAllowed;
    private String betaApiExtensionName;
    private String apiFilename;
    private Collection<String> excludedPaths;
}
