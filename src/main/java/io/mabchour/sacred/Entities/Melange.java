package io.mabchour.sacred.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
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
    private double poids;
    @Temporal(TemporalType.DATE)
    private Date dateReception ;
    @Temporal(TemporalType.DATE)
    private Date dateFabrication ;
    @ManyToOne
    private MelangeRef melangeRef;

}
