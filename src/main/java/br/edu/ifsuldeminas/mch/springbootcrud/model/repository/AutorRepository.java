package br.edu.ifsuldeminas.mch.springbootcrud.model.repository;


import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
}