package br.edu.ifsuldeminas.mch.springbootcrud.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    @Size(min = 2, max = 200, message = "O título deve ter entre {min} e {max} caracteres")
    @Column(nullable = false, length = 200)
    private String titulo;

    @NotBlank(message = "O ISBN é obrigatório")
    @Size(min = 10, max = 17, message = "O ISBN deve ter entre {min} e {max} caracteres")
    @Column(nullable = false, unique = true, length = 17)
    private String isbn;

    @NotNull(message = "O ano de publicação é obrigatório")
    @Min(value = 1000, message = "O ano de publicação deve ser um valor válido (a partir de {value})")
    @Column(nullable = false)
    private Integer anoPublicacao;

    @NotBlank(message = "O gênero é obrigatório")
    @Size(min = 2, max = 100, message = "O gênero deve ter entre {min} e {max} caracteres")
    @Column(nullable = false, length = 100)
    private String genero;

    @NotNull(message = "O autor é obrigatório")
    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Emprestimo> emprestimos;

    // Construtor padrão
    public Livro() {
    }

    // Getters e Setters

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}