package com.svillanueva.repository;

import com.svillanueva.model.Conexion;
import com.svillanueva.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioRepositorioImpl implements Repositorio<Usuario> {
	@Override
	public List<Usuario> listar() {
		List<Usuario> listaUsuario = new ArrayList<>();
		String SQL_SELECT = "SELECT * FROM usuarios";
		try (
				Connection conexion = Conexion.getConexion();
				Statement statement = conexion.createStatement();
				ResultSet rs = statement.executeQuery(SQL_SELECT)
		) {
			while (rs.next()) {
				Long id = rs.getLong("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");

				Usuario usuario = new Usuario(id, username, password, email);

				listaUsuario.add(usuario);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return listaUsuario;
	}

	@Override
	public Optional<Usuario> obtenerPorId(Long id) {
		Usuario usuario = null;
		String SQL_SELECT_BY_ID = "SELECT * FROM usuarios WHERE id = ?";
		try (
				Connection conexion = Conexion.getConexion();
				PreparedStatement preparedStatement = conexion.prepareStatement(SQL_SELECT_BY_ID)
		) {
			preparedStatement.setLong(1, id);
			try (ResultSet rs = preparedStatement.executeQuery()) {
				while (rs.next()) {
					String username = rs.getString("username");
					String password = rs.getString("password");
					String email = rs.getString("email");

					usuario = new Usuario(id, username, password, email);
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return Optional.ofNullable(usuario);
	}

	@Override
	public void guardar(Usuario usuario) {
		String SQL_INSERT = "INSERT INTO usuarios (username, password, email) VALUES (?, ?, ?)";
		String SQL_UPDATE = "UPDATE usuarios SET username = ?, password = ?, email = ? WHERE id = ?";
		try (
				Connection conexion = Conexion.getConexion();
				PreparedStatement preparedStatement = usuario.getId() == null ?
						conexion.prepareStatement(SQL_INSERT) :
						conexion.prepareStatement(SQL_UPDATE)
		) {
			preparedStatement.setString(1, usuario.getUsername());
			preparedStatement.setString(2, usuario.getPassword());
			preparedStatement.setString(3, usuario.getEmail());

			if (usuario.getId() != null) {
				preparedStatement.setLong(4, usuario.getId());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void eliminar(Long id) {
		String SQL_DELETE = "DELETE FROM usuarios WHERE id = ?";
		try (
				Connection conexion = Conexion.getConexion();
				PreparedStatement preparedStatement = conexion.prepareStatement(SQL_DELETE)
		) {
			preparedStatement.setLong(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
