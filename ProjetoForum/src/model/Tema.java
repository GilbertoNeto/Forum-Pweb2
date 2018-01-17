package model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


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
	private ArrayList<Postagem> postagems;

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

	public ArrayList<Postagem> getPostagems() {
		return this.postagems;
	}

	public void setPostagems(ArrayList<Postagem> postagems) {
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