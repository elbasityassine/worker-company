package org.intelciaitsolution.workercompany.domain.entreprise;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UniteLegale {

    private Long id;

    private String nom;

    private String denomination;

    @JsonProperty("numero_tva_intra")
    private String tva;

}
