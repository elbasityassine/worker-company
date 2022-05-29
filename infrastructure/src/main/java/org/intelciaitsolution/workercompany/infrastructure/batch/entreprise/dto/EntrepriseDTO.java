package org.intelciaitsolution.workercompany.infrastructure.batch.entreprise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntrepriseDTO {

    private Long id;
    private String nic;
    private String adresse;
    private String dateCreation;
    private String denomination;
    private String tva;
}
