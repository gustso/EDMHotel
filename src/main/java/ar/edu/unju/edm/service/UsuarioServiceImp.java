package ar.edu.unju.edm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.User;
import ar.edu.unju.edm.repository.IUsuarioDAO;

@Service
public class UsuarioServiceImp implements IUsuarioService {

	@Autowired
	IUsuarioDAO usuarioDAOimp;
	
	@Override
	public void guardarUsuario(User usuario) {
		usuarioDAOimp.save(usuario);
	}

	@Override
	public List<User> obtenerUsuarios() {
		return usuarioDAOimp.obtenerUsuarios();
	}

	@Override
	public Optional<User> obtenerUnUsuario(Long id) {
		Optional<User> usuario= usuarioDAOimp.findById(id);
		return usuario;
	}

	@Override
	public void eliminarUsuario(Long id) {
		usuarioDAOimp.deleteById(id);
	}
	
	@Override
	public User buscarUsuarioPorNombreUsuario(String nombre) {
		return usuarioDAOimp.findByNombreUsuario(nombre);
	}



}
