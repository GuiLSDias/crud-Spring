package br.edu.ifsuldeminas.mch.springbootcrud.controller;

import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.User;
import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.User.Gender;
import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Address;
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

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/listar")
	public String listarUsers(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "user/user";
	}

	@GetMapping("/novo")
	public String novoUser(Model model) {
		User user = new User();

		if (user.getAddress() == null) {
			user.setAddress(new Address());
		}
		model.addAttribute("user", user);
		model.addAttribute("genders", Gender.values());
		return "user/user_form";
	}

	@PostMapping("/salvar")
	public String salvarUser(@Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("genders", Gender.values());
			return "user/user_form";
		}
		userRepository.save(user);
		return "redirect:/users/listar";
	}

	@GetMapping("/editar/{id}")
	public String editarUser(@PathVariable Long id, Model model) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			if (user.getAddress() == null) {
				user.setAddress(new Address());
			}
			model.addAttribute("user", user);
			model.addAttribute("genders", Gender.values());
			return "user/user_form";
		} else {

			return "redirect:/users/listar";
		}
	}

	@GetMapping("/excluir/{id}")
	public String excluirUser(@PathVariable Long id) {
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {

			System.err.println("Erro ao excluir usuário: " + e.getMessage());
		}
		return "redirect:/users/listar";
	}
}