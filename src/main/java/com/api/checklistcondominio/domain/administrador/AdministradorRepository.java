package com.api.checklistcondominio.domain.administrador;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    Page<Administrador> findAllByAtivoTrue(Pageable paginacao);
}
