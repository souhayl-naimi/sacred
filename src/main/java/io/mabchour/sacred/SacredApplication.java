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
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;


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
        Melange melange = melangeRepository.findByNumLotContains("LOT3", PageRequest.of(0,1)).getContent().get(0);
        long months = ChronoUnit.DAYS.between(melange.getDateFabrication(),LocalDate.now());
        System.out.println(months);
    }
}
