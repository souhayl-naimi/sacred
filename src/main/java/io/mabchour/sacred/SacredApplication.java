package io.mabchour.sacred;


import io.mabchour.sacred.Entities.Emplacement;
import io.mabchour.sacred.Entities.Melange;
import io.mabchour.sacred.Entities.MelangeRef;
import io.mabchour.sacred.Repositories.EmplacementRepository;
import io.mabchour.sacred.Repositories.MelangeRefRepository;
import io.mabchour.sacred.Repositories.MelangeRepository;
import io.mabchour.sacred.Services.IInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication
public class SacredApplication implements CommandLineRunner {
    @Autowired
    EmplacementRepository emplacementRepository;

    @Autowired
    MelangeRepository melangeRepository;

    @Autowired
    MelangeRefRepository melangeRefRepository;

    @Autowired
    private IInitService iInitService;
    public static void main(String[] args) {
        SpringApplication.run(SacredApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

/*        iInitService.initEmplacements();
        iInitService.initMelangeRefs();
        iInitService.initMelanges();

        melangeRepository.findByMelangeRef_Emplacement_IdAndMelangeRef_Id(Long.valueOf(1),Long.valueOf(1),PageRequest.of(0,1)).getContent().forEach(melange -> {
            System.out.println(melange.getNumLot());
        });*/

        Emplacement emplacement1 = new Emplacement(null,"EMPLACEMENT1",null);
        Emplacement emplacement2 = new Emplacement(null,"EMPLACEMENT2",null);

        emplacementRepository.save(emplacement1);
        emplacementRepository.save(emplacement2);

        MelangeRef melangeRef = new MelangeRef();
        melangeRef.setNumRef("REFERENCE1");
        melangeRef.setEmplacement(emplacement1);

        melangeRefRepository.save(melangeRef);

    }
}
