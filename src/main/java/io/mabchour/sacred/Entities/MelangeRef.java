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
@Table(name = "melangeRefs")
public class MelangeRef {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numRef;
    @ManyToOne
    private Emplacement emplacement;
    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "melangeRef")
    private Collection<Melange> melanges;
}
