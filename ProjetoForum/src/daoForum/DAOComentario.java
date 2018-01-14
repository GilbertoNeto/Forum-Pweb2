package daoForum;

import model.Comentario;

import java.util.List;

import javax.persistence.Query;

import org.eclipse.persistence.exceptions.QueryException;

public class DAOComentario extends DAO<Comentario> {

	public Comentario localizarComentarioById(int codComentario) {
		
		try {
			
			Query q = manager.createQuery("Select c from Comentario c where c.idcomentario = '" + codComentario + "'");
			
			return (Comentario) q.getSingleResult();
			
		} catch (QueryException e) {
			return null;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Comentario> getComentarioByPosts(int codPost){
		
		try {
			
			Query q = manager.createQuery("Select c from Comentario c where c.postagem1.idpostagem = '" + codPost + "'");
			
			List<Comentario> coments = q.getResultList();
			
			return coments;
			
		} catch (QueryException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Comentario> getComentarioByUsuario(int codUsuario){
		
		try {
			
			Query q = manager.createQuery("Select c from Comentario c where c.usuario.idUsuario ='" + codUsuario + "'");
			
			List<Comentario> coments = q.getResultList();
			
			return coments;
			
		} catch (QueryException e) {
			return null;
		}
	}
	
	
}
