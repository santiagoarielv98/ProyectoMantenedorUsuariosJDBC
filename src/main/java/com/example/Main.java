package com.example;

import com.example.models.ConexionDB;
import com.example.models.Usuario;
import com.example.repositories.Repository;
import com.example.repositories.UsuarioRepositorioImpl;

import java.util.List;
import java.util.Optional;

public class Main {
	public static void main(String[] args) {
		Repository<Usuario> usuarioRepository = new UsuarioRepositorioImpl();

		Usuario usuario = new Usuario();


		usuario.setNombre("Santiago");
		usuario.setEdad("32");

		usuarioRepository.crear(usuario);

		usuario.setId(1L);

		usuario.setNombre("Ariel");
		usuario.setEdad("13");

		usuarioRepository.crear(usuario);

		Optional<Usuario> usuario2 = usuarioRepository.obtenerPorId(1L);

		if (usuario2.isPresent()) {
			System.out.println(usuario2.get()
					.getNombre());
		} else {
			System.out.println("No existe el usuario con el id 1");
		}

		List<Usuario> listaUsuario = usuarioRepository.listar();

		listaUsuario.forEach(System.out::println);

		usuario.setId(2L);

		usuarioRepository.actualizar(usuario);

		usuarioRepository.eliminarPorId(1L);

		ConexionDB.cerrarConexion();
	}
}

