package br.edu.ifsuldeminas.mch.springbootcrud.controller;


import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Livro;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.AutorRepository;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.LivroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @GetMapping("/listar")
    public String listarLivros(Model model) {
        model.addAttribute("livros", livroRepository.findAll());
        return "livros/listaLivros";
    }

    @GetMapping("/novo")
    public String novoLivro(Model model) {
        model.addAttribute("livro", new Livro());
        model.addAttribute("autores", autorRepository.findAll());
        return "livros/formLivro";
    }

    @PostMapping("/salvar")
    public String salvarLivro(@Valid Livro livro, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("autores", autorRepository.findAll());
            return "livros/formLivro";
        }
        livroRepository.save(livro);
        return "redirect:/livros/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarLivro(@PathVariable Long id, Model model) {
        Optional<Livro> livro = livroRepository.findById(id);
        if (livro.isPresent()) {
            model.addAttribute("livro", livro.get());
            model.addAttribute("autores", autorRepository.findAll());
            return "livros/formLivro";
        }
        return "redirect:/livros/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluirLivro(@PathVariable Long id) {
        livroRepository.deleteById(id);
        return "redirect:/livros/listar";
    }
}