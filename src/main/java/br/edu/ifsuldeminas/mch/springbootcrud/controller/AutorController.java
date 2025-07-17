package br.edu.ifsuldeminas.mch.springbootcrud.controller;


import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Autor;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.AutorRepository;
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
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @GetMapping("/listar")
    public String listarAutores(Model model) {
        model.addAttribute("autores", autorRepository.findAll());
        return "autores/listaAutores";
    }

    @GetMapping("/novo")
    public String novoAutor(Model model) {
        model.addAttribute("autor", new Autor());
        return "autores/formAutor";
    }

    @PostMapping("/salvar")
    public String salvarAutor(@Valid Autor autor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "autores/formAutor";
        }
        autorRepository.save(autor);
        return "redirect:/autores/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarAutor(@PathVariable Long id, Model model) {
        Optional<Autor> autor = autorRepository.findById(id);
        if (autor.isPresent()) {
            model.addAttribute("autor", autor.get());
            return "autores/formAutor";
        }
        return "redirect:/autores/listar"; // Redireciona se o autor não for encontrado
    }

    @GetMapping("/excluir/{id}")
    public String excluirAutor(@PathVariable Long id) {
        autorRepository.deleteById(id);
        return "redirect:/autores/listar";
    }
}