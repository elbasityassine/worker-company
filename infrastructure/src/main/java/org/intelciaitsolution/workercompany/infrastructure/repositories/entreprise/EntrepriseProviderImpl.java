package org.intelciaitsolution.workercompany.infrastructure.repositories.entreprise;

import org.intelciaitsolution.workercompany.domain.entreprise.Entreprise;
import org.intelciaitsolution.workercompany.domain.entreprise.provider.EntrepriseProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntrepriseProviderImpl implements EntrepriseProvider {

    private final EntrepriseRepository entrepriseRepository;

    @Autowired
    public EntrepriseProviderImpl(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    @Override
    public Entreprise getBySIRET(String siret) {
        return entrepriseRepository.findOneBySIRET(siret);
    }
}
