package org.intelciaitsolution.workercompany.infrastructure.batch.entreprise;

import org.intelciaitsolution.workercompany.domain.entreprise.provider.EntrepriseProvider;
import org.intelciaitsolution.workercompany.infrastructure.batch.entreprise.step.EntrepriseProcessor;
import org.intelciaitsolution.workercompany.infrastructure.batch.entreprise.step.EntrepriseReader;
import org.intelciaitsolution.workercompany.infrastructure.batch.entreprise.step.EntrepriseStepConfiguration;
import org.intelciaitsolution.workercompany.infrastructure.batch.entreprise.step.EntrepriseWriter;
import org.intelciaitsolution.workercompany.infrastructure.config.ModelMapperConfig;
import org.intelciaitsolution.workercompany.infrastructure.repositories.entreprise.EntrepriseProviderImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.batch.core.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
@SpringBatchTest
@EnableAutoConfiguration
@ContextConfiguration(classes = {
        EntrepriseJobConfig.class,
        EntrepriseStepConfiguration.class,
        EntrepriseReader.class,
        EntrepriseProcessor.class,
        EntrepriseWriter.class,
        EntrepriseProviderImpl.class,
        ModelMapperConfig.class
}, initializers = ConfigDataApplicationContextInitializer.class)
class EntrepriseBatchIntegrationTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JobExplorer jobExplorer;

    @Autowired
    private Step step;

    @MockBean
    private EntrepriseProvider entrepriseProvider;

    private static final String JOB_NAME = "entrepriseJob";
    private static final String FIRST_STEP_NAME = "entrepriseProcessingStep";

    @Test
    void should_load_batch() {
        assertNotNull(jobLauncherTestUtils.getJob());
        assertEquals(jobLauncherTestUtils.getJob().getName(), JOB_NAME);
    }

    @Test
    void should_execute_one_step_with_status_complete() throws Exception {

        // when
        JobExecution jobExecution = jobLauncherTestUtils.launchStep(FIRST_STEP_NAME);
        Collection<StepExecution> actualStepExecutions = jobExecution.getStepExecutions();
        ExitStatus actualJobExitStatus = jobExecution.getExitStatus();


        // then
        assertEquals(actualStepExecutions.size(), 1);
        assertEquals(actualJobExitStatus.getExitCode(), "COMPLETED");
    }
}
