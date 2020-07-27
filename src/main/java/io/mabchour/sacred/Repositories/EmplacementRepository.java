package io.mabchour.sacred.Repositories;

import io.mabchour.sacred.Entities.Emplacement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface EmplacementRepository extends JpaRepository<Emplacement, Long> {
 public Page<Emplacement> findByNumEmplacementContainsIgnoreCase(String num, Pageable pageable);
}
