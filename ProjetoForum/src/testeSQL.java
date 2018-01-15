import java.util.List;

import daoForum.DAOComentario;
import daoForum.DAOPostagem;
import daoForum.DAOUsuario;
import model.Comentario;
import model.Postagem;
import model.Usuario;
import controller.Fachada;

public class testeSQL {

	public static void main(String[] args) throws Exception {
		
		DAOUsuario testeConect = new DAOUsuario();
		
		DAOPostagem testePost = new DAOPostagem();
		
		DAOComentario testeComent = new DAOComentario();
		
		Fachada fachada = new Fachada();
		
		testeConect.abrir();
		
		testePost.abrir();
//		
//		Postagem p = testePost.localizarPostById(1);
		
		Usuario user = testeConect.localizarUsuarioById(1);
		
//		Usuario usuario = Fachada.cadastrarUsuario("Gilberto Neto", "eu@email.com", "1111");
		
		Fachada.atualizarDadosUsuario("Gilberto Neto", "mudar@email.com", "mudar123");
		
//		List<Comentario> c = testeComent.getComentarioByUsuario(1);
//		
//		List<Postagem> posts = testePost.localizarPostByTema("Java + JSP");
//		
//		p = testePost.localizarPostByTitulo("Criando projeto web + JPA");
//		
//		Comentario coment = c.get(0);
//		
//		if (user == null)
//			System.out.println("Return null");
//		
//		System.out.println(user.getNomeUsuario());
//		
//		System.out.println(p.getPostagem());
//		
//		System.out.println(coment.getComentario());
		
		testeConect.fechar();

	}

}
