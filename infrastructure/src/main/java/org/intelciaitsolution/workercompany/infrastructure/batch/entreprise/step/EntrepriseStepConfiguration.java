package org.intelciaitsolution.workercompany.infrastructure.batch.entreprise.step;


import org.intelciaitsolution.workercompany.domain.entreprise.Entreprise;
import org.intelciaitsolution.workercompany.infrastructure.batch.entreprise.dto.EntrepriseDTO;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
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

    private final ItemReader<Entreprise> reader;

    private final ItemProcessor<Entreprise, EntrepriseDTO> processor;

    @Autowired
    public EntrepriseStepConfiguration(StepBuilderFactory stepBuilderFactory, FlatFileItemWriter<EntrepriseDTO> flatFileItemWriter, ItemReader<Entreprise> reader, ItemProcessor<Entreprise, EntrepriseDTO> processor) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.flatFileItemWriter = flatFileItemWriter;
        this.reader = reader;
        this.processor = processor;
    }

    @Bean(name = FIRST_STEP_NAME)
    public Step firstStep() {
        return stepBuilderFactory.get(FIRST_STEP_NAME)
                .<Entreprise, EntrepriseDTO>chunk(chunkSize)
                .reader(reader)
                .processor(processor)
                .writer(flatFileItemWriter)
                .build();
    }
}
