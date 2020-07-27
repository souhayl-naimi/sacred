package io.mabchour.sacred.Services;

import io.mabchour.sacred.Entities.Emplacement;
import io.mabchour.sacred.Entities.Melange;
import io.mabchour.sacred.Entities.MelangeRef;
import io.mabchour.sacred.Repositories.EmplacementRepository;
import io.mabchour.sacred.Repositories.MelangeRefRepository;
import io.mabchour.sacred.Repositories.MelangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Stream;
@Service
public class InitService implements IInitService {
    @Autowired
    EmplacementRepository emplacementRepository;

    @Autowired
    MelangeRepository melangeRepository;

    @Autowired
    MelangeRefRepository melangeRefRepository;
    @Override
    public void initEmplacements() {
        for(int i=1;i<4;i++){
            Emplacement emplacement = new Emplacement();
            emplacement.setNumEmplacement("E"+i);
            emplacementRepository.save(emplacement);
        }
    }

    @Override
    public void initMelangeRefs() {
            emplacementRepository.findAll().forEach(emplacement -> {
                    MelangeRef melangeRef = new MelangeRef();
                    melangeRef.setEmplacement(emplacement);
                    melangeRef.setNumRef("REF"+emplacement.getNumEmplacement().substring(1));
                    melangeRefRepository.save(melangeRef);
            });
    }

    @Override
    public void initMelanges() {
        melangeRefRepository.findAll().forEach(melangeRef -> {
            for(int i=1;i<6;i++) {
                Melange melange = new Melange();
                melange.setMelangeRef(melangeRef);
                melange.setNumLot("LOT"+i);
                melange.setDateFabrication(new Date());
                melange.setDateReception(new Date());
                melangeRepository.save(melange);
            }
        });
    }
}
