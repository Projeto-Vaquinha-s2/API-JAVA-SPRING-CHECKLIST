package com.api.checklistcondominio.domain.condominio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CondominioRepository extends JpaRepository<Condominio, Long> {
    Page<Condominio> findAllByAtivoTrue(Pageable paginacao);

}
