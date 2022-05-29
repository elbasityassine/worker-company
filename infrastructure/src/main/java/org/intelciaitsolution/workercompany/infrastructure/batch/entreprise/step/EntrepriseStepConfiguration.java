package org.intelciaitsolution.workercompany.infrastructure.batch.entreprise.step;


import org.intelciaitsolution.workercompany.domain.entreprise.Entreprise;
import org.intelciaitsolution.workercompany.infrastructure.batch.entreprise.dto.EntrepriseDTO;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EntrepriseStepConfiguration {

    @Value("${feature.batch.chunk-size}")
    private int chunkSize;

    public static final String FIRST_STEP_NAME = "entrepriseProcessingStep";

    private final StepBuilderFactory stepBuilderFactory;

    @Qualifier(EntrepriseWriter.WRITER_NAME)
    private final FlatFileItemWriter<EntrepriseDTO> flatFileItemWriter;

    private final EntrepriseReader entrepriseReader;

    private final EntrepriseProcessor entrepriseProcessor;

    @Autowired
    public EntrepriseStepConfiguration(StepBuilderFactory stepBuilderFactory, EntrepriseReader entrepriseReader, EntrepriseProcessor entrepriseProcessor, FlatFileItemWriter<EntrepriseDTO> flatFileItemWriter) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.entrepriseReader = entrepriseReader;
        this.entrepriseProcessor = entrepriseProcessor;
        this.flatFileItemWriter = flatFileItemWriter;
    }

    @Bean(name = FIRST_STEP_NAME)
    public Step firstStep() {
        return stepBuilderFactory.get(FIRST_STEP_NAME)
                .<Entreprise, EntrepriseDTO>chunk(chunkSize)
                .reader(entrepriseReader)
                .processor(entrepriseProcessor)
                .writer(flatFileItemWriter)
                .build();
    }
}
