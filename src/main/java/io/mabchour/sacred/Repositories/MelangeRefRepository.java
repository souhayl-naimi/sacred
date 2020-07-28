package io.mabchour.sacred.Repositories;

import io.mabchour.sacred.Entities.MelangeRef;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MelangeRefRepository extends JpaRepository<MelangeRef, Long> {
    public Page<MelangeRef> findByEmplacement_Id(Long id, Pageable pageable);
    public Page<MelangeRef> findByNumRefContainsIgnoreCase(String numRef, Pageable pageable);
}
