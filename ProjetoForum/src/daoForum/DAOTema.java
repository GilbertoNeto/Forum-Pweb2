package daoForum;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import model.Tema;

public class DAOTema extends DAO<Tema> {


	public Tema localizarTemabyNome(String tema) {
		
		try {
			
			Query q = manager.createQuery("Select t from Tema t where t.tema = '" + tema + "'");
			return (Tema) q.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		}
	}
	
}
