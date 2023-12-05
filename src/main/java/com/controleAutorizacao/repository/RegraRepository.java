package com.controleAutorizacao.repository;

import com.controleAutorizacao.entidade.Regra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegraRepository extends JpaRepository<Regra, Long> {
    List<Regra> findAllByUsuario(String usuario);

    List<Regra>findAll();

    boolean existsByIdAndUsuario(Long id, String usuario);

    boolean existsById(Long id);

    Optional<Regra> findByIdAndUsuario(Long id, String usuario);

    Optional<Regra> findById(Long id);

    List<Regra> findAllByCdProcedimentoAndIdadeAndSexo(Long cdProcedimento, Long idade, String sexo);

    List<Regra> findAllByUsuarioAndCdProcedimentoAndIdadeAndSexo(String usuario, Long cdProcedimento, Long idade, String sexo);
}
