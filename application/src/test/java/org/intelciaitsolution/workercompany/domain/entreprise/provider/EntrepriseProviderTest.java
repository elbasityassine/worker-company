package org.intelciaitsolution.workercompany.domain.entreprise.provider;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.intelciaitsolution.workercompany.application.WorkerCompanyApplication;
import org.intelciaitsolution.workercompany.domain.entreprise.Entreprise;
import org.intelciaitsolution.workercompany.domain.entreprise.Etablissement;
import org.intelciaitsolution.workercompany.domain.entreprise.UniteLegale;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = WorkerCompanyApplication.class)
class EntrepriseProviderTest {

    @ClassRule
    public static WireMockRule wireMockRule = new WireMockRule(8900);

    @Autowired
    private EntrepriseProvider entrepriseProvider;

    @PostConstruct
    public void init() {
        wireMockRule.start();
    }

    @PreDestroy
    public void stop() {
        wireMockRule.stop();
    }

    @Before
    public void resetAll() {
        wireMockRule.resetAll();
    }

    @Test
    void get_entreprise_by_siret_ok() {

        // GIVEN
        Entreprise extectedEntreprise = initialEntrepriseBySIRET("31302979500017");

        // When
        Entreprise entreprise = entrepriseProvider.getBySIRET("31302979500017");

        // Then
        assertNotNull(entreprise);
        assertEquals(extectedEntreprise, entreprise);
    }

    private Entreprise initialEntrepriseBySIRET(String siret) {
        Etablissement etablissement = new Etablissement();
        etablissement.setId(449321770L);
        etablissement.setNic(siret);
        etablissement.setAdresse("261 Chemin des Colles 06140 Vence");
        etablissement.setDateCreation(LocalDate.of(2002, 6, 1));
        UniteLegale uniteLegale = new UniteLegale();
        uniteLegale.setDenomination("Big COMPANY");
        uniteLegale.setTva("FR96313029795");
        etablissement.setUniteLegale(uniteLegale);
        Entreprise entreprise = new Entreprise();
        entreprise.setEtablissement(etablissement);
        return entreprise;
    }
}
