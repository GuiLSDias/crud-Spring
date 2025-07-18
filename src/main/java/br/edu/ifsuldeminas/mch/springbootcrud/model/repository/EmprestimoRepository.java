package br.edu.ifsuldeminas.mch.springbootcrud.model.repository;

import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
}