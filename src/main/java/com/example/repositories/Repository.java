package com.example.repositories;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
	void crear(T t);
	List<T> listar();
	Optional<T> obtenerPorId(Long id);

	void actualizar(T t);
	void eliminarPorId(Long id);
}
