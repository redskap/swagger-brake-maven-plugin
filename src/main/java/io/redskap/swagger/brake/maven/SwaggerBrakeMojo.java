package io.redskap.swagger.brake.maven;

import io.redskap.swagger.brake.runner.Options;
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

    @Parameter(name = "mavenRepoUsername", required = false)
    private String mavenRepoUsername;

    @Parameter(name = "mavenRepoPassword", required = false)
    private String mavenRepoPassword;

    @Parameter(name = "groupId", required = false, defaultValue = "${project.groupId}")
    private String groupId;

    @Parameter(name = "artifactId", required = false, defaultValue = "${project.artifactId}")
    private String artifactId;

    @Parameter(name = "outputFilePath", required = false, defaultValue = "${project.build.directory}/swagger-brake")
    private String outputFilePath;

    @Parameter(name = "outputFormat", required = false, defaultValue = "HTML")
    private String outputFormat;

    @Parameter(name = "deprecatedApiDeletionAllowed", required = false)
    private Boolean deprecatedApiDeletionAllowed;

    private final RunnerParameterValidator parameterValidator = new RunnerParameterValidator();
    private final Executor executor = new Executor(new StarterWrapper(), getLog());

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        RunnerParameter parameter = createRunnerParameter();
        getLog().debug("The following parameters are set for Swagger Brake: " + parameter.toString());
        parameterValidator.validate(parameter);
        Options options = OptionsFactory.create(parameter);
        executor.execute(options);
    }

    private RunnerParameter createRunnerParameter() {
        return RunnerParameter.builder()
                .newApi(newApi)
                .mavenRepoUrl(mavenRepoUrl)
                .mavenRepoUsername(mavenRepoUsername)
                .mavenRepoPassword(mavenRepoPassword)
                .groupId(groupId)
                .artifactId(artifactId)
                .outputFilePath(outputFilePath)
                .outputFormat(outputFormat)
                .deprecatedApiDeletionAllowed(deprecatedApiDeletionAllowed)
                .build();
    }
}
