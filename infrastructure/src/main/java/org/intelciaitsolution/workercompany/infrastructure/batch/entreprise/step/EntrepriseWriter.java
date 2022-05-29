package org.intelciaitsolution.workercompany.infrastructure.batch.entreprise.step;

import lombok.extern.slf4j.Slf4j;
import org.intelciaitsolution.workercompany.infrastructure.batch.entreprise.dto.EntrepriseDTO;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@Slf4j
public class EntrepriseWriter {

    @Value("${feature.batch.output-folder}")
    private String outputFolder;

    public static final String WRITER_NAME = "entrepriseWriter";

    public static final String FILE_NAME_CSV = "extract_entreprises.csv";

    @Bean
    public FlatFileItemWriter<EntrepriseDTO> write(){
        log.info("Export entreprises data to CSV file");

        String outputFile = String.format("%s/%s_%s", outputFolder, System.currentTimeMillis(), FILE_NAME_CSV);

        return new FlatFileItemWriterBuilder<EntrepriseDTO>()
                .name(WRITER_NAME)
                .resource(new FileSystemResource(outputFile))
                .lineAggregator(getLineAggregator())
                .build();
    }

    private DelimitedLineAggregator<EntrepriseDTO> getLineAggregator(){
        BeanWrapperFieldExtractor<EntrepriseDTO> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(getNames());
        fieldExtractor.afterPropertiesSet();

        DelimitedLineAggregator<EntrepriseDTO> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setFieldExtractor(fieldExtractor);
        return lineAggregator;
    }

    private String[] getNames() {
        return new String[] {"id", "nic", "adresse", "dateCreation", "denomination", "tva"};
    }
}
