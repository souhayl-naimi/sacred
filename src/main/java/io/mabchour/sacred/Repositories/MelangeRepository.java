package io.mabchour.sacred.Repositories;

import io.mabchour.sacred.Entities.Melange;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MelangeRepository extends JpaRepository<Melange, Long> {
    public Page<Melange> findByMelangeRef_Emplacement_IdAndMelangeRef_Id(Long idEmp, Long idRef, Pageable pageable);
    public Page<Melange> findByNumLotContains(String numLot, Pageable pageable);
}
