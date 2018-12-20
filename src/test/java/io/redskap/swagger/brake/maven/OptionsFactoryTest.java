package io.redskap.swagger.brake.maven;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.collect.ImmutableSet;
import io.redskap.swagger.brake.runner.Options;
import io.redskap.swagger.brake.runner.OutputFormat;
import org.junit.Test;

public class OptionsFactoryTest {
    @Test
    public void testCreateShouldCopyValuesProperly() {
        // given
        RunnerParameter parameter = RunnerParameter.builder()
            .newApi("newApi")
            .mavenRepoUrl("repoUrl")
            .mavenRepoUsername("username")
            .mavenRepoPassword("password")
            .groupId("groupId")
            .artifactId("artifactId")
            .outputFilePath("outputFilePath")
            .outputFormat("HTML")
            .build();
        // when
        Options result = OptionsFactory.create(parameter);
        // then
        assertThat(result.getNewApiPath()).isEqualTo(parameter.getNewApi());
        assertThat(result.getMavenRepoUrl()).isEqualTo(parameter.getMavenRepoUrl());
        assertThat(result.getMavenRepoUsername()).isEqualTo(parameter.getMavenRepoUsername());
        assertThat(result.getMavenRepoPassword()).isEqualTo(parameter.getMavenRepoPassword());
        assertThat(result.getGroupId()).isEqualTo(parameter.getGroupId());
        assertThat(result.getArtifactId()).isEqualTo(parameter.getArtifactId());
        assertThat(result.getOutputFilePath()).isEqualTo(parameter.getOutputFilePath());
        assertThat(result.getOutputFormats()).isEqualTo(ImmutableSet.of(OutputFormat.HTML));
    }

    @Test
    public void testCreateShouldHandleLowercaseOutputFormat() {
        // given
        RunnerParameter parameter = RunnerParameter.builder()
            .newApi("newApi")
            .mavenRepoUrl("repoUrl")
            .mavenRepoUsername("username")
            .mavenRepoPassword("password")
            .groupId("groupId")
            .artifactId("artifactId")
            .outputFilePath("outputFilePath")
            .outputFormat("html")
            .build();
        // when
        Options result = OptionsFactory.create(parameter);
        // then
        assertThat(result.getNewApiPath()).isEqualTo(parameter.getNewApi());
        assertThat(result.getMavenRepoUrl()).isEqualTo(parameter.getMavenRepoUrl());
        assertThat(result.getMavenRepoUsername()).isEqualTo(parameter.getMavenRepoUsername());
        assertThat(result.getMavenRepoPassword()).isEqualTo(parameter.getMavenRepoPassword());
        assertThat(result.getGroupId()).isEqualTo(parameter.getGroupId());
        assertThat(result.getArtifactId()).isEqualTo(parameter.getArtifactId());
        assertThat(result.getOutputFilePath()).isEqualTo(parameter.getOutputFilePath());
        assertThat(result.getOutputFormats()).isEqualTo(ImmutableSet.of(OutputFormat.HTML));
    }
}