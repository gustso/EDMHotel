package ar.edu.unju.edm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.edu.unju.edm.model.User;

public interface IUsuarioDAO extends JpaRepository<User, Long>{

	@Query("from Usuario e order by e.nombreUsuario")
	public List<User> obtenerUsuarios();
	
	User findByNombreUsuario(String nombre);
	
}
