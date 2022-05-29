package org.intelciaitsolution.workercompany.domain.entreprise.provider;

import org.intelciaitsolution.workercompany.domain.entreprise.Entreprise;

public interface EntrepriseProvider {

    Entreprise getBySIRET(String siret);

}
