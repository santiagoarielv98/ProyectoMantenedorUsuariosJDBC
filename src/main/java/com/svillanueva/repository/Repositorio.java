package com.svillanueva.repository;

import java.util.List;
import java.util.Optional;

public interface Repositorio<T> {
	List<T> listar();
	Optional<T> obtenerPorId(Long id);

	void guardar(T t);

	void eliminar(Long id);
}
