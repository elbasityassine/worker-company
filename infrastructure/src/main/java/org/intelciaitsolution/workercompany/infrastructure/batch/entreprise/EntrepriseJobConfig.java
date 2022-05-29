package org.intelciaitsolution.workercompany.infrastructure.batch.entreprise;

import org.intelciaitsolution.workercompany.infrastructure.batch.entreprise.step.EntrepriseStepConfiguration;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class EntrepriseJobConfig {

    public static final String JOB_NAME = "entrepriseJob";

    private final JobBuilderFactory jobBuilderFactory;

    @Qualifier(EntrepriseStepConfiguration.FIRST_STEP_NAME)
    private final Step step;

    @Autowired
    public EntrepriseJobConfig(JobBuilderFactory jobBuilderFactory, Step step) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.step = step;
    }

    @Bean(name = JOB_NAME)
    public Job entrepriseJob() {
        return jobBuilderFactory.get(JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }
}
