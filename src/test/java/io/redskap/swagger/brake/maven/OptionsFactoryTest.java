package io.redskap.swagger.brake.maven;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import io.redskap.swagger.brake.runner.ArtifactPackaging;
import io.redskap.swagger.brake.runner.Options;
import io.redskap.swagger.brake.runner.OutputFormat;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

public class OptionsFactoryTest {
    @Test
    public void testCreateShouldCopyValuesProperly() {
        // given
        RunnerParameter parameter = RunnerParameter.builder()
            .newApi("newApi")
            .mavenRepoUrl("repoUrl")
            .mavenSnapshotRepoUrl("snapshotRepoUrl")
            .mavenRepoUsername("username")
            .mavenRepoPassword("password")
            .groupId("groupId")
            .artifactId("artifactId")
            .currentVersion("1.0.0")
            .artifactPackaging("jar")
            .outputFilePath("outputFilePath")
            .outputFormats(ImmutableList.of("HTML"))
            .deprecatedApiDeletionAllowed(true)
            .betaApiExtensionName("something.yaml")
            .excludedPaths(Sets.newSet("/pet"))
            .ignoredBreakingChangeRules(Sets.newSet("R001", "R002"))
            .build();
        // when
        Options result = OptionsFactory.create(parameter);
        // then
        assertThat(result.getNewApiPath()).isEqualTo(parameter.getNewApi());
        assertThat(result.getMavenRepoUrl()).isEqualTo(parameter.getMavenRepoUrl());
        assertThat(result.getMavenSnapshotRepoUrl()).isEqualTo(parameter.getMavenSnapshotRepoUrl());
        assertThat(result.getMavenRepoUsername()).isEqualTo(parameter.getMavenRepoUsername());
        assertThat(result.getMavenRepoPassword()).isEqualTo(parameter.getMavenRepoPassword());
        assertThat(result.getGroupId()).isEqualTo(parameter.getGroupId());
        assertThat(result.getArtifactId()).isEqualTo(parameter.getArtifactId());
        assertThat(result.getCurrentArtifactVersion()).isEqualTo(parameter.getCurrentVersion());
        assertThat(result.getArtifactPackaging()).isEqualTo(ArtifactPackaging.JAR);
        assertThat(result.getOutputFilePath()).isEqualTo(parameter.getOutputFilePath());
        assertThat(result.getOutputFormats()).isEqualTo(ImmutableSet.of(OutputFormat.HTML));
        assertThat(result.getDeprecatedApiDeletionAllowed()).isEqualTo(parameter.getDeprecatedApiDeletionAllowed());
        assertThat(result.getBetaApiExtensionName()).isEqualTo(parameter.getBetaApiExtensionName());
        assertThat(result.getApiFilename()).isEqualTo(parameter.getApiFilename());
        assertThat(result.getExcludedPaths()).isEqualTo(parameter.getExcludedPaths());
        assertThat(result.getIgnoredBreakingChangeRules()).isEqualTo(parameter.getIgnoredBreakingChangeRules());
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
            .outputFormats(ImmutableList.of("html"))
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
    public void testCreateShouldHandleLowercaseArtifactPackaging() {
        // given
        RunnerParameter parameter = RunnerParameter.builder()
                .newApi("newApi")
                .mavenRepoUrl("repoUrl")
                .mavenRepoUsername("username")
                .mavenRepoPassword("password")
                .groupId("groupId")
                .artifactId("artifactId")
                .artifactPackaging("jar")
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
        assertThat(result.getArtifactPackaging()).isEqualTo(ArtifactPackaging.JAR);
    }

    @Test
    public void testCreateShouldHandleUppercaseArtifactPackaging() {
        // given
        RunnerParameter parameter = RunnerParameter.builder()
                .newApi("newApi")
                .mavenRepoUrl("repoUrl")
                .mavenRepoUsername("username")
                .mavenRepoPassword("password")
                .groupId("groupId")
                .artifactId("artifactId")
                .artifactPackaging("JAR")
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
        assertThat(result.getArtifactPackaging()).isEqualTo(ArtifactPackaging.JAR);
    }

    @Test
    public void testCreateShouldHandleNonTrimmedUppercaseArtifactPackaging() {
        // given
        RunnerParameter parameter = RunnerParameter.builder()
                .newApi("newApi")
                .mavenRepoUrl("repoUrl")
                .mavenRepoUsername("username")
                .mavenRepoPassword("password")
                .groupId("groupId")
                .artifactId("artifactId")
                .artifactPackaging("   JAR  ")
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
        assertThat(result.getArtifactPackaging()).isEqualTo(ArtifactPackaging.JAR);
    }

    @Test
    public void testCreateShouldHandleUppercaseWarArtifactPackaging() {
        // given
        RunnerParameter parameter = RunnerParameter.builder()
                .newApi("newApi")
                .mavenRepoUrl("repoUrl")
                .mavenRepoUsername("username")
                .mavenRepoPassword("password")
                .groupId("groupId")
                .artifactId("artifactId")
                .artifactPackaging("WAR")
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
        assertThat(result.getArtifactPackaging()).isEqualTo(ArtifactPackaging.WAR);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateShouldThrowExceptionWhenNonSupportedPackagingIsUsed() {
        // given
        RunnerParameter parameter = RunnerParameter.builder()
                .newApi("newApi")
                .mavenRepoUrl("repoUrl")
                .mavenRepoUsername("username")
                .mavenRepoPassword("password")
                .groupId("groupId")
                .artifactId("artifactId")
                .artifactPackaging("pom")
                .build();
        // when
        OptionsFactory.create(parameter);
        // then exception thrown
    }
}