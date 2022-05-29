package org.intelciaitsolution.workercompany.infrastructure.repositories.entreprise;

import org.intelciaitsolution.workercompany.domain.entreprise.Entreprise;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
@FeignClient(
        name = "service-public-entreprise",
        url = "${services.public.entreprise.url}")
public interface EntrepriseRepository {

    @GetMapping("/api/sirene/v3/etablissements/{siret}")
    Entreprise findOneBySIRET(@PathVariable String siret);
}
