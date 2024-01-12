package com.example.models;

public class Usuario {
	private Long id;
	private String nombre;
	private String edad;

	public Usuario() {
	}

	public Usuario(Long id, String nombre, String edad) {
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}
}
