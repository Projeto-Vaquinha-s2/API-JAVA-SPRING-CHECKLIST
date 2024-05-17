package com.api.checklistcondominio.domain.sindico;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SindicoRepository extends JpaRepository<Sindico, Long> {
    Page<Sindico> findAllByAtivoTrue(Pageable paginacao);

}
