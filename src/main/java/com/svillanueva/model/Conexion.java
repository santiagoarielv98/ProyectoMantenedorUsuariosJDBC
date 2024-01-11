package com.svillanueva.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private static Connection conexion = null;

	private static final String URL = "jdbc:mysql://localhost:3306/proyectomantenedorusuarios";
	private static final String USUARIO = "root";
	private static final String CONTRASENIA = "";

	private Conexion(){
	}

	public static Connection getConexion() {
		if(conexion == null){
			try{
				conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENIA);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return conexion;
	}

	public static void closeConexion(){
		if(conexion != null){
			try {
				conexion.close();
				System.out.println("Conexion cerrada");
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
