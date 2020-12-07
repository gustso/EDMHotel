package ar.edu.unju.edm.service;

import java.util.List;
import java.util.Optional;
import ar.edu.unju.edm.model.User;

public interface IUsuarioService {
	
	public void guardarUsuario(User usuario);
	
	public List<User> obtenerUsuarios();
	
	public Optional<User> obtenerUnUsuario(Long id);
	
	public void eliminarUsuario(Long id);
	
	public User buscarUsuarioPorNombreUsuario(String nombre);
}

