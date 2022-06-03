package org.intelciaitsolution.workercompany.infrastructure.batch.entreprise;

import org.intelciaitsolution.workercompany.domain.entreprise.Entreprise;
import org.intelciaitsolution.workercompany.infrastructure.batch.entreprise.dto.EntrepriseDTO;
import org.intelciaitsolution.workercompany.infrastructure.batch.entreprise.step.EntrepriseStepConfiguration;
import org.intelciaitsolution.workercompany.infrastructure.batch.entreprise.step.EntrepriseWriter;
import org.intelciaitsolution.workercompany.infrastructure.config.ModelMapperConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
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
        ModelMapperConfig.class,
        EntrepriseWriter.class
}, initializers = ConfigDataApplicationContextInitializer.class)
class EntrepriseBatchIntegrationTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @MockBean
    private ItemReader<Entreprise> reader;

    @MockBean
    private ItemProcessor<Entreprise, EntrepriseDTO> processor;

    private JobExecution jobExecution;

    private static final String COMPLETED = "COMPLETED";
    private static final String JOB_NAME = "entrepriseJob";

    @BeforeEach
    void setUp()  throws Exception {
        jobExecution = jobLauncherTestUtils.launchJob();
    }

    @Test
    void should_load_batch() {
        assertNotNull(jobLauncherTestUtils.getJob());
        assertEquals(jobLauncherTestUtils.getJob().getName(), JOB_NAME);
    }

    @Test
    void should_execute_one_step_with_status_complete() {
        Collection<StepExecution> actualStepExecutions = jobExecution.getStepExecutions();

        assertEquals(actualStepExecutions.size(), 1);
        assertEquals(jobExecution.getExitStatus().getExitCode(), COMPLETED);
    }
}
