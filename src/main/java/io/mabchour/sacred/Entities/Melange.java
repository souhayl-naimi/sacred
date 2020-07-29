package io.mabchour.sacred.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Table(name = "melanges")
public class Melange {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numLot;
    private double dimensions;
    private long days;
    private double poids;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateReception ;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFabrication ;
    @ManyToOne
    private MelangeRef melangeRef;

}
