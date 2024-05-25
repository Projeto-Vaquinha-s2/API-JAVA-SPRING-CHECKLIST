package com.api.checklistcondominio.domain.Torre;

import com.api.checklistcondominio.domain.condominio.Condominio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TorreRepository extends JpaRepository <Torre, Long> {
    Page<Torre> findAllByAtivoTrue(Pageable paginacao);
    long countByCondominioAndAtivoTrue(Condominio condominio);
}
