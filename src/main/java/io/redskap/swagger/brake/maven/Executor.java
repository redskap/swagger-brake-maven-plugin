package io.redskap.swagger.brake.maven;

import static java.lang.String.format;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

import java.util.Collection;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.runner.Options;
import io.redskap.swagger.brake.runner.exception.LatestArtifactDownloadException;
import lombok.RequiredArgsConstructor;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;

@RequiredArgsConstructor
public class Executor {
    private final StarterWrapper starter;
    private final Log log;

    public void execute(Options options) throws MojoFailureException {
        try {
            Collection<BreakingChange> bcs = starter.start(options);
            if (isNotEmpty(bcs)) {
                throw new MojoFailureException("Breaking change has been found. See report further details at " + options.getOutputFilePath());
            }
        } catch (LatestArtifactDownloadException e) {
            log.info(format("Latest version of the artifact could not be retrieved from %s with %s:%s",
                options.getMavenRepoUrl(), options.getGroupId(), options.getArtifactId()));
            log.info("Assuming this is the first version of the artifact, skipping check for breaking changes", e);
        }
    }
}
