package com.example.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
	private static Connection conexion = null;
	private static final String URL = "jdbc:mysql://localhost:3306/example";
	private static final String USUARIO = "root";
	private static final String PASSWORD = "";

	private ConexionDB() {}

	public static Connection obtenerConexion() {
		if (conexion == null) {
			try {
				conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
				System.out.println("Se conecto correctamente a la base de datos!");
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return conexion;
	}

	public static void cerrarConexion() {
		if (conexion != null) {
			try {
				conexion.close();
				System.out.println("Se ha cerrado correctamente la base de datos!");
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
