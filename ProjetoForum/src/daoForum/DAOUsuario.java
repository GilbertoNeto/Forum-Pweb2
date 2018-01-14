package daoForum;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import model.Usuario;

public class DAOUsuario extends DAO<Usuario> {

	public Usuario localizarUsuarioById(int id) {
		
		try {
			Query q = manager.createQuery("Select u from Usuario u where u.idUsuario = '" + id + "'");
			return (Usuario) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
}
