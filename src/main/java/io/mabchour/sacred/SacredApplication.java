package io.mabchour.sacred;


import io.mabchour.sacred.Entities.Emplacement;
import io.mabchour.sacred.Entities.Melange;
import io.mabchour.sacred.Entities.MelangeRef;
import io.mabchour.sacred.Repositories.EmplacementRepository;
import io.mabchour.sacred.Repositories.MelangeRefRepository;
import io.mabchour.sacred.Repositories.MelangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SacredApplication implements CommandLineRunner {

    @Autowired
    EmplacementRepository emplacementRepository;

    @Autowired
    MelangeRepository melangeRepository;

    @Autowired
    MelangeRefRepository melangeRefRepository;

    public static void main(String[] args) {
        SpringApplication.run(SacredApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
