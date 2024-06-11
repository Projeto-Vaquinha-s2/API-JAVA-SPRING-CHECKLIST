package com.api.checklistcondominio.domain.registro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroRepository extends JpaRepository<Registro, Long> {
    Page<Registro> findAllByAtivoTrue(Pageable paginacao);
}
