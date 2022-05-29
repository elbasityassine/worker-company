package org.intelciaitsolution.workercompany.infrastructure.batch.entreprise.step;

import lombok.extern.slf4j.Slf4j;
import org.intelciaitsolution.workercompany.domain.entreprise.Entreprise;
import org.intelciaitsolution.workercompany.domain.entreprise.provider.EntrepriseProvider;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Slf4j
public class EntrepriseReader implements ItemReader<Entreprise> {

    @Value("${feature.batch.entreprise.siret-companies-file-path}")
    private String siretCompaniesFilePath;

    private final EntrepriseProvider entrepriseProvider;

    private final AtomicInteger nextEntrepriseIndex;
    private List<Entreprise> entrepriseData;
    private final List<String> siretList;

    @Autowired
    public EntrepriseReader(EntrepriseProvider entrepriseProvider) {
        this.entrepriseProvider = entrepriseProvider;
        nextEntrepriseIndex = new AtomicInteger();
        siretList = new ArrayList<>();
    }

    @BeforeStep
    public void loadSiretCompaniesFromFlatFile(StepExecution stepExecution) {
        log.info("[Start] reading flat file {}", siretCompaniesFilePath);
        try (Stream<String> stream = Files.lines(Paths.get(siretCompaniesFilePath))) {
            stream.forEach(siret -> {
                if (StringUtils.hasText(siret)) siretList.add(siret);
            });
            log.info("[End] reading flat file");
        } catch (IOException e) {
            log.error("Error reading file {}", e.toString());
        }
    }

    @Override
    public Entreprise read() {
        log.debug("[Start] reading data from API public entreprise");
        Entreprise nextEntreprise = null;

        if (Objects.isNull(entrepriseData)) {
            entrepriseData = siretList.stream().map(entrepriseProvider::getBySIRET).collect(Collectors.toList());
        }

        if (nextEntrepriseIndex.get() < entrepriseData.size()) {
            nextEntreprise = entrepriseData.get(nextEntrepriseIndex.get());
            nextEntrepriseIndex.incrementAndGet();
        } else {
            nextEntrepriseIndex.set(0);
            entrepriseData = null;
        }
        log.debug("[END] reading data from API public entreprise");

        return nextEntreprise;
    }
}
