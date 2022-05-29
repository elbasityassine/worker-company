package org.intelciaitsolution.workercompany.infrastructure.batch.entreprise.step;

import lombok.extern.slf4j.Slf4j;
import org.intelciaitsolution.workercompany.infrastructure.batch.entreprise.dto.EntrepriseDTO;
import org.intelciaitsolution.workercompany.domain.entreprise.Entreprise;
import org.intelciaitsolution.workercompany.infrastructure.utils.AppUtils;
import org.modelmapper.ModelMapper;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EntrepriseProcessor implements ItemProcessor<Entreprise, EntrepriseDTO> {

    private final ModelMapper modelMapper;

    @Autowired
    public EntrepriseProcessor(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public EntrepriseDTO process(Entreprise entreprise) {
        log.debug("Processing data from BATCH Entreprise");
        EntrepriseDTO entrepriseDTO = modelMapper.map(entreprise.getEtablissement(), EntrepriseDTO.class);
        entrepriseDTO.setDateCreation(AppUtils.getFormattedDate(entreprise.getEtablissement().getDateCreation()));
        entrepriseDTO.setDenomination(entreprise.getEtablissement().getUniteLegale().getDenomination());
        entrepriseDTO.setTva(entreprise.getEtablissement().getUniteLegale().getTva());
        return entrepriseDTO;
    }
}
