package br.edu.ifsuldeminas.mch.springbootcrud.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O nome do usuário não pode ser vazio.")
	@Size(min = 3, max = 100, message = "O nome deve ter entre {min} e {max} caracteres.")
	@Column(nullable = false, length = 100)
	private String name;

	@NotNull(message = "O gênero não pode ser nulo.")
	@Column(nullable = false, length = 1)
	private Gender gender;

	@NotBlank(message = "O email do usuário não pode ser vazio.")
	@Email(message = "O email é inválido.")
	@Column(nullable = false, unique = true, length = 100)
	private String email;


	@OneToOne(optional = false, cascade = CascadeType.ALL, orphanRemoval = true)
	@Valid
	private Address address;


	@Transient
	private String log;

	public User() {

		this.name = "";
		this.gender = Gender.F; // Valor padrão
		this.email = "";
		this.address = new Address();
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	// Você pode adicionar um getter/setter para 'log' se precisar manipulá-lo no controller/service
	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	// Enum Gender
	public enum Gender {
		M('M'),
		F('F');

		private final Character genderChar; // Renomeado para evitar conflito com getGender() do User

		private Gender(Character genderChar) {
			this.genderChar = genderChar;
		}

		public Character getGenderChar() { // Getter para o caractere
			return genderChar;
		}

		// Método estático para converter um caractere para o enum (útil em alguns casos de formulário)
		public static Gender fromChar(Character c) {
			if (c == null) {
				return null;
			}
			for (Gender gender : Gender.values()) {
				if (gender.getGenderChar().equals(c)) {
					return gender;
				}
			}
			throw new IllegalArgumentException("Gênero inválido: " + c);
		}
	}
}