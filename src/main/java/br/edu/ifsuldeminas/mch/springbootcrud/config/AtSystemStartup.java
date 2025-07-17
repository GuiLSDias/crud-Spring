package br.edu.ifsuldeminas.mch.springbootcrud.config;

import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.AddressRepository;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Address;
import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.User;
import jakarta.transaction.Transactional;

@Component
@Transactional
public class AtSystemStartup implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public void run(String... args) throws Exception {
		// Dados para Emerson
		Address aEmerson = new Address();
		aEmerson.setNumber("123"); // Agora é String
		aEmerson.setStreet("Rua A"); // Alterado de setPlace para setStreet
		aEmerson.setCity("Machado"); // Adicionado o campo city
		aEmerson.setZipCode("37750-000"); // CEP mais realista
		addressRepository.save(aEmerson);

		// Dados para Noe
		Address aNoe = new Address();
		aNoe.setNumber("100"); // Agora é String
		aNoe.setStreet("Rua B"); // Alterado de setPlace para setStreet
		aNoe.setCity("Alfenas"); // Adicionado o campo city
		aNoe.setZipCode("37130-000"); // CEP mais realista
		addressRepository.save(aNoe);

		// Dados para Luiza (renomeei de 'Lu' para 'Luiza' para consistência)
		Address aLuiza = new Address(); // Variável renomeada
		aLuiza.setNumber("101"); // Agora é String
		aLuiza.setStreet("Rua L"); // Alterado de setPlace para setStreet
		aLuiza.setCity("Pouso Alegre"); // Adicionado o campo city
		aLuiza.setZipCode("37550-000"); // CEP mais realista
		addressRepository.save(aLuiza);

		// Não é necessário addressRepository.flush() aqui se você está salvando os Users logo em seguida.
		// O flush acontece automaticamente com o @Transactional ou quando a transação é commitada.

		User emerson = new User();
		emerson.setName("Emerson A. Carvalho");
		emerson.setGender(User.Gender.M);
		emerson.setEmail("emerson@mail.com");
		emerson.setAddress(aEmerson);

		User luiza = new User(); // Variável renomeada
		luiza.setName("Luiza O. Carvalho");
		luiza.setGender(User.Gender.F);
		luiza.setEmail("lu@mail.com");
		luiza.setAddress(aLuiza); // Usando aNoe, corrigido para aLuiza

		User noe = new User();
		noe.setName("Noe L. Carvalho");
		noe.setGender(User.Gender.M);
		noe.setEmail("noe@mail.com");
		noe.setAddress(aNoe);

		userRepository.save(emerson);
		userRepository.save(luiza);
		userRepository.save(noe);
	}
}