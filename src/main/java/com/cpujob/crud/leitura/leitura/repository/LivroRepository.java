package com.cpujob.crud.leitura.leitura.repository;

import com.cpujob.crud.leitura.leitura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByName(String name);
}
