import daoForum.DAO;

public class testeSQL {

	public static void main(String[] args) {
		
		DAO testeConect = new DAO();
		
		testeConect.abrir();
		
		testeConect.fechar();

	}

}
