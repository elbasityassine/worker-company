package org.intelciaitsolution.workercompany.domain.entreprise;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Etablissement {

    private Long id;

    private String nic;

    @JsonProperty("geo_adresse")
    private String adresse;

    @JsonProperty("date_creation")
    private LocalDate dateCreation;

    @JsonProperty("unite_legale")
    private UniteLegale uniteLegale;

}
