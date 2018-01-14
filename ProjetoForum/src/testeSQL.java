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
		
		p = testePost.localizarPostByTitulo("Criando projeto web + JPA");
		
		if (user == null)
			System.out.println("Return null");
		
		System.out.println(user.getNomeUsuario());
		
		System.out.println(p.getPostagem());
		
		testeConect.fechar();

	}

}
