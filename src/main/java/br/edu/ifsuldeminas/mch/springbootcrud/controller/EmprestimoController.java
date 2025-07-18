package br.edu.ifsuldeminas.mch.springbootcrud.controller;

import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Emprestimo;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.EmprestimoRepository;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.LivroRepository;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/listar")
    public String listarEmprestimos(Model model) {
        model.addAttribute("emprestimos", emprestimoRepository.findAll());
        return "emprestimos/listaEmprestimos";
    }

    @GetMapping("/novo")
    public String novoEmprestimo(Model model) {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setDataDevolucaoPrevista(LocalDate.now().plusDays(15));

        model.addAttribute("emprestimo", emprestimo);
        model.addAttribute("livros", livroRepository.findAll());
        model.addAttribute("membros", userRepository.findAll());
        return "emprestimos/formEmprestimo";
    }

    @PostMapping("/salvar")
    public String salvarEmprestimo(@Valid Emprestimo emprestimo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("livros", livroRepository.findAll());
            model.addAttribute("membros", userRepository.findAll());
            return "emprestimos/formEmprestimo";
        }
        emprestimoRepository.save(emprestimo);
        return "redirect:/emprestimos/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarEmprestimo(@PathVariable Long id, Model model) {
        Optional<Emprestimo> emprestimo = emprestimoRepository.findById(id);
        if (emprestimo.isPresent()) {
            model.addAttribute("emprestimo", emprestimo.get());
            model.addAttribute("livros", livroRepository.findAll());
            model.addAttribute("membros", userRepository.findAll());
            return "emprestimos/formEmprestimo";
        }
        return "redirect:/emprestimos/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluirEmprestimo(@PathVariable Long id) {
        emprestimoRepository.deleteById(id);
        return "redirect:/emprestimos/listar";
    }


    @GetMapping("/devolver/{id}")
    public String marcarComoDevolvido(@PathVariable Long id) {
        Optional<Emprestimo> emprestimoOpt = emprestimoRepository.findById(id);
        if (emprestimoOpt.isPresent()) {
            Emprestimo emprestimo = emprestimoOpt.get();
            emprestimo.setDataDevolucaoReal(LocalDate.now());
            emprestimoRepository.save(emprestimo);
        }
        return "redirect:/emprestimos/listar";
    }
}