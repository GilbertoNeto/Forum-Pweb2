import java.util.List;

import daoForum.DAOPostagem;
import daoForum.DAOUsuario;
import model.Postagem;
import model.Usuario;

public class testeSQL {

	public static void main(String[] args) {
		
		DAOUsuario testeConect = new DAOUsuario();
		
		DAOPostagem testePost = new DAOPostagem();
		
		testeConect.abrir();
		
		testePost.abrir();
		
		Postagem p = testePost.localizarPostById(1);
		
		Usuario user = testeConect.localizarUsuarioById(1);
		
		List<Postagem> posts = testePost.localizarPostByTema("Java + JSP");
		
		if (user == null)
			System.out.println("Return null");
		
		System.out.println(user.getNomeUsuario());
		
		p = posts.get(0);
		
		System.out.println(p.getTema().getTema());
		
		testeConect.fechar();

	}

}
