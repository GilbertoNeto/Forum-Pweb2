package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import daoForum.DAO;
import daoForum.DAOComentario;
import daoForum.DAOPostagem;
import daoForum.DAOTema;
import daoForum.DAOUsuario;
import model.Comentario;
import model.Postagem;
import model.Tema;
import model.Usuario;

/**
 * @author Gilberto Neto
 *
 */
public class Fachada {
	
	
	private static DAOUsuario daousuario = new DAOUsuario();
	private static DAOPostagem daopostagem = new DAOPostagem();
	private static DAOComentario daocomentario = new DAOComentario();
	private static DAOTema daotema = new DAOTema();
	
	public static void inicializar(){
		DAO.abrir();
	}
	
	public static void finalizar(){
		DAO.fechar();
	}
	
	
	 /*INICIO DOS MÉTODOS PARA USUARIOS*/
	
	public static Usuario cadastrarUsuario  (String nome, String email, String senha) throws Exception {
		
		DAO.abrir();
		
		DAO.iniciar();
		
		
		Usuario u = daousuario.localizarUsuarioByNome(nome);
		
		u = daousuario.localizarUsuarioByEmail(email);
		
		if (u == null) {
			
			u = new Usuario();
			u.setNomeUsuario(nome);
			u.setEmail(email);
			u.setSenha(senha);
			daousuario.persistir(u);
			DAO.efetivar();
			DAO.fechar();
			return u;
			
		} else {
			DAO.cancelar();
			throw new Exception ("Usuário já cadastrado");	
		}		
	}
	
	public static void removerUsuario (String email) throws Exception {
		
		DAO.abrir();
		
		DAO.iniciar();
		
		Usuario u = daousuario.localizarUsuarioByEmail(email);
		
		if (u == null) {
			throw new Exception ("Usuário não encontrado no fórum.");
		}
		
		if (u.getComentarios() != null) {
			List<Comentario> c = u.getComentarios();
			for (Comentario aux: c) {
				aux.setUsuario(null);
				u.removeComentario(aux);
				daocomentario.atualizar(aux);
				daousuario.atualizar(u);
//				daocomentario.apagar(aux);
			}
		}
		
		if(u.getPostagems() != null) {
			List<Postagem> p = u.getPostagems();
			for(Postagem aux:p) {
				aux.setUsuario(null);
				u.removePostagem(aux);
				daousuario.atualizar(u);
				daopostagem.atualizar(aux);
				daopostagem.apagar(aux);
			}
			
//			for (Postagem aux:p) {
//				
//				if (p.size() == 1) {
//					u.removePostagem(aux);
//					aux.setUsuario(null);
//					daopostagem.apagar(aux);
//					break;
//				} else {
//				aux.setUsuario(null);
//				u.removePostagem(aux);
//				daopostagem.apagar(aux);
//				}
//			}
		}
		
		daousuario.apagar(u);
		DAO.efetivar();
		
		DAO.fechar();
	}
	
	
	public static void atualizarDadosUsuario (String nome, String email, String senha) throws Exception {
		
		DAO.abrir();
		
		DAO.iniciar();
		
		Usuario	u = daousuario.localizarUsuarioByNome(nome);
		
		if (u == null)
			throw new Exception ("Usuário não cadastrado.");
		
		u.setEmail(email);
		
		if (senha != null)
			u.setSenha(senha);
		else {
			u.setSenha(u.getSenha());
		}
		
		daousuario.atualizar(u);
		
		DAO.efetivar();
	}
	/*FIM DOS MÉTODOS PARA USUARIOS*/
	
	
	/*INICIO DOS MÉTODOS PARA POSTAGENS*/
	
	public static void Postar(String nomeUsuario, String postagem, String temaPostagem) throws Exception {
		
		DAO.abrir();
		
		DAO.iniciar();
		
		Usuario u = daousuario.localizarUsuarioByNome(nomeUsuario);
		
		if (u == null)
			throw new Exception ("Usuário não cadastrado.");
		
		Postagem p = daopostagem.localizarPostByTitulo(postagem);
		
		if (p != null && u != null) {
			throw new Exception("A postagem já existe no fórum.");
		}
		
		Tema t = daotema.localizarTemabyNome(temaPostagem);
		
		if (t == null)
			throw new Exception("O tema informado não esta cadastrado no fórum");
		
		if (p == null) {
			
			p = new Postagem();
			p.setPostagem(postagem);
			p.setTema(t);
			p.setUsuario(u);
			u.addPostagem(p);
			t.addPostagem(p);
			
			daopostagem.persistir(p);
			daousuario.atualizar(u);
			daotema.atualizar(t);
		}
		
		DAO.efetivar();
	}
	
	public static void RemoverPostagem(String email, String postagem) throws Exception {
		
		DAO.abrir();
		
		DAO.iniciar();
		
		Usuario u = daousuario.localizarUsuarioByEmail(email);
		
		Postagem post = new Postagem();
		
		ArrayList<Comentario> coments = new ArrayList();
		
		if (u == null)
			throw new Exception("Usuário não cadastrado no fórum.");
		
		if(u.getPostagems() != null) {
			List<Postagem> p = u.getPostagems();
			
			for (Postagem aux:p) {
				post = daopostagem.localizarPostByTitulo(postagem);
			}
		}
		
		
		//Apagando comentários existentes na postagem
		
		if (post.getComentarios() != null) 
			coments = post.getComentarios();
		
		Iterator i = post.getComentarios().iterator();
		while (i.hasNext()) {
			Comentario c = (Comentario) i.next();
			c.setUsuario(null);
			c.setPostagem1(null);
			post.removeComentario(c);
			daopostagem.atualizar(post);
			daocomentario.apagar(c);
			i.remove();
		}
		
//		for (Comentario c: coments) {
//			if (coments.size() > 0) {
//			c.setPostagem1(null);
//			post.removeComentario(c);
//			c.setUsuario(null);
//			daocomentario.apagar(c);
//			daopostagem.atualizar(post);
//			} else {
//				daopostagem.apagar(post);
//			}
//		}
		
		daopostagem.apagar(post);
			
		
		DAO.efetivar();
	}
	
	
	public static void Comentar(String email, String postagem, String comentario) throws Exception {
		
		DAO.abrir();
		
		DAO.iniciar();
		
		Usuario u = daousuario.localizarUsuarioByEmail(email);
		if (u == null)
			throw new Exception("Usuario nao cadastrado.");
		
		Postagem p = daopostagem.localizarPostByTitulo(postagem);
		if (p == null)
			throw new Exception("Postagem não encontrada.");
		
		Comentario c = new Comentario();
		c.setComentario(comentario);
		c.setUsuario(u);
		c.setPostagem1(p);
		u.addComentario(c);
		p.addComentario(c);
		daocomentario.persistir(c);
		daousuario.atualizar(u);
		daopostagem.atualizar(p);
		
		DAO.efetivar();
	
	}
	
	
	public static void removerComentario(String email, String postagem, int codComentario) throws Exception{
		
		DAO.abrir();
		
		DAO.iniciar();
		
		Usuario u = daousuario.localizarUsuarioByEmail(email);
		if (u == null)
			throw new Exception("Usuario nao cadastrado.");
		
		Postagem p = daopostagem.localizarPostByTitulo(postagem);
		if (p == null)
			throw new Exception("Postagem não encontrada.");
		
		
		Comentario c = daocomentario.localizarComentarioById(codComentario);
		p.removeComentario(c);
		u.removeComentario(c);
		
		daousuario.atualizar(u);
		daopostagem.atualizar(p);
		
			c.setPostagem1(null);
			c.setUsuario(null);
			
		daocomentario.apagar(c);
		
		DAO.efetivar();
		
	}
	

}
