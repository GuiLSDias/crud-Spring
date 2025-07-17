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

		Address aEmerson = new Address();
		aEmerson.setNumber("123");
		aEmerson.setStreet("Rua A");
		aEmerson.setCity("Machado");
		aEmerson.setZipCode("37750-000");
		addressRepository.save(aEmerson);


		Address aNoe = new Address();
		aNoe.setNumber("100");
		aNoe.setStreet("Rua B");
		aNoe.setCity("Alfenas");
		aNoe.setZipCode("37130-000");
		addressRepository.save(aNoe);


		Address aLuiza = new Address();
		aLuiza.setNumber("101");
		aLuiza.setStreet("Rua L");
		aLuiza.setCity("Pouso Alegre");
		aLuiza.setZipCode("37550-000");
		addressRepository.save(aLuiza);


		User emerson = new User();
		emerson.setName("Emerson A. Carvalho");
		emerson.setGender(User.Gender.M);
		emerson.setEmail("emerson@mail.com");
		emerson.setAddress(aEmerson);

		User luiza = new User();
		luiza.setName("Luiza O. Carvalho");
		luiza.setGender(User.Gender.F);
		luiza.setEmail("lu@mail.com");
		luiza.setAddress(aLuiza);

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