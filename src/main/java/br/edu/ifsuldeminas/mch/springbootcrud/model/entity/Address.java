package br.edu.ifsuldeminas.mch.springbootcrud.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "addresses")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "A rua é obrigatória.")
	@Size(max = 200, message = "A rua não pode exceder {max} caracteres.")
	@Column(nullable = false, length = 200)
	private String street;

	@NotBlank(message = "O número é obrigatório.")
	@Size(max = 20, message = "O número não pode exceder {max} caracteres.")
	@Column(nullable = false, length = 20)
	private String number;

	@NotBlank(message = "A cidade é obrigatória.")
	@Size(max = 100, message = "A cidade não pode exceder {max} caracteres.")
	@Column(nullable = false, length = 100)
	private String city;

	@NotBlank(message = "O CEP é obrigatório.")
	@Size(min = 8, max = 9, message = "O CEP deve ter entre {min} e {max} caracteres.")
	@Column(nullable = false, length = 9)
	private String zipCode;

	// Construtor
	public Address() {
		this.street = "";
		this.number = "";
		this.city = "";
		this.zipCode = "";
	}

	// Getters e Setters
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getStreet() { return street; }
	public void setStreet(String street) { this.street = street; }
	public String getNumber() { return number; }
	public void setNumber(String number) { this.number = number; }
	public String getCity() { return city; }
	public void setCity(String city) { this.city = city; }
	public String getZipCode() { return zipCode; }
	public void setZipCode(String zipCode) { this.zipCode = zipCode; }
}