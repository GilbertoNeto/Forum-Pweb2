package daoForum;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.eclipse.persistence.exceptions.QueryException;

import model.Postagem;

public class DAOPostagem extends DAO<Postagem> {

	public Postagem localizarPostById(int idPost) {
		
		try {
			Query q = manager.createQuery("Select p from Postagem p where p.idpostagem = '"+ idPost +"'");
			
			return (Postagem) q.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}
	
	

	public Postagem localizarPostByTitulo(String titulo) {
		
		try {
			
			Query q = manager.createQuery("Select p from Postagem p where p.postagem = '" + titulo + "'");
			
			return (Postagem) q.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Postagem> localizarPostbyUser(int idUsuario){
		
		try {
			
			Query q = manager.createQuery("Select p from Postagem p where p.usuario.idUsuario = '" + idUsuario + "'");
			
			List<Postagem> posts = q.getResultList();
			
			return posts;
			
		} catch (NoResultException e) {
			return null;
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Postagem> localizarPostByTema(String tema){
		try {
			
			Query q = manager.createQuery("Select p from Postagem p where p.tema.tema = '" + tema + "'");
			
			List<Postagem> posts = q.getResultList();
			
			return posts;
			
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
	
}
