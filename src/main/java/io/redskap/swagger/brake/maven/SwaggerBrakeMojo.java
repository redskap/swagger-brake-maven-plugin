package io.redskap.swagger.brake.maven;

import io.redskap.swagger.brake.runner.Options;
import io.redskap.swagger.brake.runner.OptionsValidator;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "check", defaultPhase = LifecyclePhase.PROCESS_CLASSES)
public class SwaggerBrakeMojo extends AbstractMojo {
    @Parameter(name = "newApi", required = true)
    private String newApi;

    @Parameter(name = "mavenRepoUrl", required = true)
    private String mavenRepoUrl;

    @Parameter(name = "mavenSnapshotRepoUrl", required = true)
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

    @Parameter(name = "outputFilePath", required = false, defaultValue = "${project.build.directory}/swagger-brake")
    private String outputFilePath;

    @Parameter(name = "outputFormat", required = false, defaultValue = "HTML")
    private String outputFormat;

    @Parameter(name = "deprecatedApiDeletionAllowed", required = false)
    private Boolean deprecatedApiDeletionAllowed;

    @Parameter(name = "betaApiExtensionName", required = false)
    private String betaApiExtensionName;

    @Parameter(name = "apiFilename", required = false)
    private String apiFilename;

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
        return RunnerParameter.builder()
                .newApi(newApi)
                .mavenRepoUrl(mavenRepoUrl)
                .mavenSnapshotRepoUrl(mavenSnapshotRepoUrl)
                .mavenRepoUsername(mavenRepoUsername)
                .mavenRepoPassword(mavenRepoPassword)
                .groupId(groupId)
                .artifactId(artifactId)
                .currentVersion(currentVersion)
                .outputFilePath(outputFilePath)
                .outputFormat(outputFormat)
                .deprecatedApiDeletionAllowed(deprecatedApiDeletionAllowed)
                .betaApiExtensionName(betaApiExtensionName)
                .apiFilename(apiFilename)
                .build();
    }
}
