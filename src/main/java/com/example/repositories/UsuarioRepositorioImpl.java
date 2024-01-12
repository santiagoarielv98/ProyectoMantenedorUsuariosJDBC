package com.example.repositories;

import com.example.models.ConexionDB;
import com.example.models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioRepositorioImpl implements Repository<Usuario> {
	@Override
	public List<Usuario> listar() {
		List<Usuario> listaUsuario = new ArrayList<>();
		String SQL = "SELECT * FROM usuarios";
		try (
				Connection conexion = ConexionDB.obtenerConexion();
				Statement statement = conexion.createStatement();
				ResultSet rs = statement.executeQuery(SQL)
		) {
			while (rs.next()) {
				Long usuarioId = rs.getLong("id");
				String nombre = rs.getString("nombre");
				String edad = rs.getString("edad");

				Usuario usuario = new Usuario(usuarioId, nombre, edad);

				listaUsuario.add(usuario);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return listaUsuario;
	}

	@Override
	public void crear(Usuario usuario) {
		String SQL = "INSERT INTO usuarios(nombre, edad) VALUES(?, ?)";
		try (
				Connection conexion = ConexionDB.obtenerConexion();
				PreparedStatement preparedStatement = conexion.prepareStatement(SQL)
		) {
			preparedStatement.setString(1, usuario.getNombre());
			preparedStatement.setString(2, usuario.getEdad());

			preparedStatement.executeUpdate();

			System.out.println("Se ha creado el Usuario correctamente");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Optional<Usuario> obtenerPorId(Long id) {
		Usuario usuario = null;
		String SQL = "SELECT * FROM usuarios WHERE id = ?";
		try (
				Connection conexion = ConexionDB.obtenerConexion();
				PreparedStatement preparedStatement = conexion.prepareStatement(SQL)
		) {
			preparedStatement.setLong(1, id);

			try (ResultSet rs = preparedStatement.executeQuery()) {
				if (rs.next()) {
					Long usuarioId = rs.getLong("id");
					String nombre = rs.getString("nombre");
					String edad = rs.getString("edad");

					usuario = new Usuario(usuarioId, nombre, edad);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return Optional.ofNullable(usuario);
	}

	@Override
	public void actualizar(Usuario usuario) {
		String SQL = "UPDATE usuarios SET nombre = ?, edad = ? WHERE id = ?";
		try (
				Connection conexion = ConexionDB.obtenerConexion();
				PreparedStatement preparedStatement = conexion.prepareStatement(SQL)
		) {
			preparedStatement.setString(1, usuario.getNombre());
			preparedStatement.setString(2, usuario.getEdad());
			preparedStatement.setLong(3, usuario.getId());

			preparedStatement.executeUpdate();

			System.out.println("Se ha actualizado el Usuario correctamente");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void eliminarPorId(Long id) {
		String SQL = "DELETE FROM usuarios WHERE id = ?";
		try (
				Connection conexion = ConexionDB.obtenerConexion();
				PreparedStatement preparedStatement = conexion.prepareStatement(SQL)
		) {
			preparedStatement.setLong(1, id);
			System.out.println("Se ha eliminado el Usuario correctamente");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
