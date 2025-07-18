package br.edu.ifsuldeminas.mch.springbootcrud.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "emprestimos")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O livro é obrigatório.")
    @ManyToOne
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;

    @NotNull(message = "O membro é obrigatório.")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User membro;

    @NotNull(message = "A data de empréstimo é obrigatória.")
    @PastOrPresent(message = "A data de empréstimo não pode ser futura.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate dataEmprestimo;

    @NotNull(message = "A data de devolução prevista é obrigatória.")
    @FutureOrPresent(message = "A data de devolução prevista não pode ser passada.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate dataDevolucaoPrevista;

    @Column(nullable = true)
    @PastOrPresent(message = "A data de devolução real não pode ser futura.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataDevolucaoReal;

    // Construtor padrão
    public Emprestimo() {
        this.dataEmprestimo = LocalDate.now();

    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public User getMembro() {
        return membro;
    }

    public void setMembro(User membro) {
        this.membro = membro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public LocalDate getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public void setDataDevolucaoReal(LocalDate dataDevolucaoReal) {
        this.dataDevolucaoReal = dataDevolucaoReal;
    }
}