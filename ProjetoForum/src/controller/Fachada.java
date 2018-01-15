package controller;

import java.util.List;

import daoForum.DAO;
import daoForum.DAOComentario;
import daoForum.DAOPostagem;
import daoForum.DAOUsuario;
import model.Comentario;
import model.Usuario;

public class Fachada {
	
	
	private static DAOUsuario daousuario = new DAOUsuario();
	private static DAOPostagem daopostagem = new DAOPostagem();
	private static DAOComentario daocomentario = new DAOComentario();
	
	public static void inicializar(){
		DAO.abrir();
	}
	
	public static void finalizar(){
		DAO.fechar();
	}
	
	
	 /*INICIO DOS M�TODOS PARA USUARIOS*/
	
	public static Usuario cadastrarUsuario  (String nome, String email, String senha) throws Exception {
		
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
			return u;
			
		} else {
			DAO.cancelar();
			throw new Exception ("Usu�rio j� cadastrado");	
		}		
	}
	
	public static void removerUsuario (String email) throws Exception {
		
		DAO.iniciar();
		
		Usuario u = daousuario.localizarUsuarioByEmail(email);
		
		if (u == null) {
			throw new Exception ("Usu�rio n�o encontrado no f�rum.");
		}
		
		if (u.getComentarios() != null) {
			List<Comentario> c = u.getComentarios();
			for (Comentario aux: c) {
				aux.setUsuario(null);
				u.removeComentario(aux);
				daocomentario.apagar(aux);
			}
		}
		
		daousuario.apagar(u);
		DAO.efetivar();
	}
	
	
	public static void atualizarDadosUsuario (String nome, String email, String senha) throws Exception {
		
		DAO.iniciar();
		
		Usuario	u = daousuario.localizarUsuarioByNome(nome);
		
		if (u == null)
			throw new Exception ("Usu�rio n�o cadastrado.");
		
		u.setEmail(email);
		
		if (senha != null)
			u.setSenha(senha);
		else {
			u.setSenha(u.getSenha());
		}
		
		daousuario.atualizar(u);
		
		DAO.efetivar();
	}
	/*FIM DOS M�TODOS PARA USUARIOS*/

}
