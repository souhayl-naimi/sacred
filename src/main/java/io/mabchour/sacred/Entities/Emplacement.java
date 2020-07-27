package io.mabchour.sacred.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Table(name = "emplacements")
public class Emplacement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numEmplacement;
    @OneToMany(mappedBy = "emplacement")
    private Collection<MelangeRef> melangeRefs;

}
