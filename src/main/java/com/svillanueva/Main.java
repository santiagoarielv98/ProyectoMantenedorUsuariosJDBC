package com.svillanueva;


import com.svillanueva.model.Conexion;
import com.svillanueva.model.Usuario;
import com.svillanueva.repository.UsuarioRepositorioImpl;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	public static void main(String[] args) {

		UsuarioRepositorioImpl usuarioRepositorio = new UsuarioRepositorioImpl();

		Map<String, Integer> operaciones = new HashMap<>();
		operaciones.put("Actualizar", 1);
		operaciones.put("Eliminar", 2);
		operaciones.put("Agregar", 3);
		operaciones.put("Listar", 4);
		operaciones.put("Salir", 5);

		Object[] opArreglo = operaciones.keySet()
				.toArray();

		int opcionIndice = 0;

		do {
			Object opcion = JOptionPane.showInputDialog(null,
					"Seleccione una operación",
					"Mantenedor de Usuarios",
					JOptionPane.INFORMATION_MESSAGE, null, opArreglo, opArreglo[0]);

			if (opcion == null) {
				JOptionPane.showMessageDialog(null, "Debe seleccionar una operación");
				continue;
			}

			opcionIndice = operaciones.get(opcion.toString());

			switch (opcionIndice) {
				case 1:
					try {
						String idStr = JOptionPane.showInputDialog(null, "Ingrese el ID del usuario a actualizar:");
						if (idStr == null || idStr.trim()
								.isEmpty()) {
							JOptionPane.showMessageDialog(null, "El ID es necesario para actualizar");
							break;
						}
						Long id = Long.parseLong(idStr);

						if (usuarioRepositorio.obtenerPorId(id)
								.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Usuario no encontrado");
							break;
						}

						String nuevoUsername = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre de usuario:");
						String nuevaPassword = JOptionPane.showInputDialog(null, "Ingrese la nueva contraseña:");
						String nuevoEmail = JOptionPane.showInputDialog(null, "Ingrese el nuevo correo electrónico:");

						if (nuevoUsername == null || nuevaPassword == null || nuevoEmail == null) {
							JOptionPane.showMessageDialog(null, "Todos los campos son necesarios");
							break;
						}
						Usuario usuarioActualizado = new Usuario(id, nuevoUsername, nuevaPassword, nuevoEmail);
						usuarioRepositorio.guardar(usuarioActualizado);
						JOptionPane.showMessageDialog(null, "Usuario actualizado exitosamente");
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "ID inválido");
					}
					break;
				case 2:
					try {
						String idStr = JOptionPane.showInputDialog(null, "Ingrese el ID del usuario a eliminar:");
						if (idStr == null || idStr.trim()
								.isEmpty()) {
							JOptionPane.showMessageDialog(null, "El ID es necesario para eliminar");
							break;
						}
						Long id = Long.parseLong(idStr);

						if (usuarioRepositorio.obtenerPorId(id)
								.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Usuario no encontrado");
							break;
						}

						int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar al usuario con ID " + id + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
						if (confirmacion == JOptionPane.YES_OPTION) {
							usuarioRepositorio.eliminar(id);
							JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente");
						}

					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "ID inválido");
					}
					break;
				case 3:
					String username = JOptionPane.showInputDialog(null, "Ingrese el nombre de usuario:");
					if (username == null || username.trim()
							.isEmpty()) {
						JOptionPane.showMessageDialog(null, "El nombre de usuario es necesario");
						break;
					}

					String password = JOptionPane.showInputDialog(null, "Ingrese la contraseña:");
					if (password == null || password.trim()
							.isEmpty()) {
						JOptionPane.showMessageDialog(null, "La contraseña es necesaria");
						break;
					}

					String email = JOptionPane.showInputDialog(null, "Ingrese el correo electrónico:");
					if (email == null || email.trim()
							.isEmpty()) {
						JOptionPane.showMessageDialog(null, "El correo electrónico es necesario");
						break;
					}

					Usuario nuevoUsuario = new Usuario();
					nuevoUsuario.setUsername(username);
					nuevoUsuario.setPassword(password);
					nuevoUsuario.setEmail(email);

					usuarioRepositorio.guardar(nuevoUsuario);
					JOptionPane.showMessageDialog(null, "Usuario agregado exitosamente");
					break;
				case 4:
					List<Usuario> usuarios = usuarioRepositorio.listar();
					if (usuarios.isEmpty()) {
						JOptionPane.showMessageDialog(null, "No hay usuarios para mostrar");
					} else {
						StringBuilder sb = new StringBuilder();
						sb.append("Lista de Usuarios:\n");
						for (Usuario usuario : usuarios) {
							sb.append("ID: ")
									.append(usuario.getId())
									.append(", Username: ")
									.append(usuario.getUsername())
									.append(", Email: ")
									.append(usuario.getEmail())
									.append("\n");
						}
						JOptionPane.showMessageDialog(null, sb.toString());
					}
					break;
				case 5:
					Conexion.closeConexion();
					JOptionPane.showMessageDialog(null, "Programa finalizado");
					System.exit(0);
					break;
				default:
					JOptionPane.showMessageDialog(null, "Opción no válida");
			}
		} while (opcionIndice != 5);

	}


}