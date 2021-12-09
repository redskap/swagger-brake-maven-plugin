package io.redskap.swagger.brake.maven;

import com.google.common.collect.ImmutableList;
import io.redskap.swagger.brake.runner.Options;
import io.redskap.swagger.brake.runner.OptionsValidator;
import io.redskap.swagger.brake.runner.OutputFormat;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Mojo(name = "check", defaultPhase = LifecyclePhase.PROCESS_CLASSES)
public class SwaggerBrakeMojo extends AbstractMojo {
    @Parameter(name = "oldApi", required = false)
    private String oldApi;

    @Parameter(name = "newApi", required = true)
    private String newApi;

    @Parameter(name = "mavenRepoUrl", required = false)
    private String mavenRepoUrl;

    @Parameter(name = "mavenSnapshotRepoUrl", required = false)
    private String mavenSnapshotRepoUrl;

    @Parameter(name = "mavenRepoUsername", required = false)
    private String mavenRepoUsername;

    @Parameter(name = "mavenRepoPassword", required = false)
    private String mavenRepoPassword;

    @Parameter(name = "groupId", required = false, defaultValue = "${project.groupId}")
    private String groupId;

    @Parameter(name = "artifactId", required = false, defaultValue = "${project.artifactId}")
    private String artifactId;

    @Parameter(name = "currentVersion", required = false, defaultValue = "${project.version}")
    private String currentVersion;

    @Parameter(name = "packaging", required = false, defaultValue = "${project.packaging}")
    private String packaging;

    @Parameter(name = "outputFilePath", required = false, defaultValue = "${project.build.directory}/swagger-brake")
    private String outputFilePath;

    @Parameter(name = "outputFormats", required = false)
    private List<String> outputFormats;

    @Parameter(name = "deprecatedApiDeletionAllowed", required = false)
    private Boolean deprecatedApiDeletionAllowed;

    @Parameter(name = "betaApiExtensionName", required = false)
    private String betaApiExtensionName;

    @Parameter(name = "apiFilename", required = false)
    private String apiFilename;

    @Parameter(name = "excludedPaths", required = false)
    private List<String> excludedPaths;

    private final Executor executor = new Executor(new StarterWrapper(), getLog());
    
    private final OptionsValidator optionsValidator = new OptionsValidator();

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        RunnerParameter parameter = createRunnerParameter();
        getLog().debug("The following parameters are set for Swagger Brake: " + parameter.toString());
        Options options = OptionsFactory.create(parameter);
        optionsValidator.validate(options);
        executor.execute(options);
    }

    private RunnerParameter createRunnerParameter() {
        List<String> oFormats = getOutputFormats();
        return RunnerParameter.builder()
                .oldApi(oldApi)
                .newApi(newApi)
                .mavenRepoUrl(mavenRepoUrl)
                .mavenSnapshotRepoUrl(mavenSnapshotRepoUrl)
                .mavenRepoUsername(mavenRepoUsername)
                .mavenRepoPassword(mavenRepoPassword)
                .groupId(groupId)
                .artifactId(artifactId)
                .currentVersion(currentVersion)
                .artifactPackaging(packaging)
                .outputFilePath(outputFilePath)
                .outputFormats(oFormats)
                .deprecatedApiDeletionAllowed(deprecatedApiDeletionAllowed)
                .betaApiExtensionName(betaApiExtensionName)
                .apiFilename(apiFilename)
                .excludedPaths(excludedPaths)
                .build();
    }

    private List<String> getOutputFormats() {
        if (CollectionUtils.isNotEmpty(outputFormats)) {
            return outputFormats;
        } else {
            return ImmutableList.of(OutputFormat.HTML.name());
        }
    }
}
