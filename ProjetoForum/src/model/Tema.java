package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tema database table.
 * 
 */
@Entity
@NamedQuery(name="Tema.findAll", query="SELECT t FROM Tema t")
public class Tema implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idtema;

	private String tema;

	//bi-directional many-to-one association to Postagem
	@OneToMany(mappedBy="tema")
	private List<Postagem> postagems;

	public Tema() {
	}

	public int getIdtema() {
		return this.idtema;
	}

	public void setIdtema(int idtema) {
		this.idtema = idtema;
	}

	public String getTema() {
		return this.tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public List<Postagem> getPostagems() {
		return this.postagems;
	}

	public void setPostagems(List<Postagem> postagems) {
		this.postagems = postagems;
	}

	public Postagem addPostagem(Postagem postagem) {
		getPostagems().add(postagem);
		postagem.setTema(this);

		return postagem;
	}

	public Postagem removePostagem(Postagem postagem) {
		getPostagems().remove(postagem);
		postagem.setTema(null);

		return postagem;
	}

}