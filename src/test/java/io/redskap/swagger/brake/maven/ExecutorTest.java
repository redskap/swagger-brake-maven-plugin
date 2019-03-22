package io.redskap.swagger.brake.maven;

import static java.util.Collections.emptyList;
import static java.util.Collections.singleton;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.runner.Options;
import io.redskap.swagger.brake.runner.exception.LatestArtifactDownloadException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExecutorTest {
    @Mock
    private StarterWrapper starter;

    @Mock
    private Log log;

    @InjectMocks
    private Executor underTest;

    @Test
    public void testExecuteShouldLogOnlyWhenLatestArtifactResolutionFails() throws MojoFailureException {
        // given
        Options options = new Options();
        given(starter.start(options)).willThrow(LatestArtifactDownloadException.class);
        // when
        underTest.execute(options);
        // then
        then(log).should(times(2)).info(anyString());
    }

    @Test
    public void testExecuteShouldWork() throws MojoFailureException {
        // given
        Options options = new Options();
        given(starter.start(options)).willReturn(emptyList());
        // when
        underTest.execute(options);
        // then
        then(starter).should().start(options);
        verifyZeroInteractions(log);
    }

    @Test(expected = MojoFailureException.class)
    public void testExecuteShouldThrowMojoFailureExceptionWhenBreakingChangeIsFound() throws MojoFailureException {
        // given
        BreakingChange bc = mock(BreakingChange.class);
        Options options = new Options();
        given(starter.start(options)).willReturn(singleton(bc));
        // when
        underTest.execute(options);
        // then exception thrown
    }
}