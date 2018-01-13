package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the comentario database table.
 * 
 */
@Entity
@NamedQuery(name="Comentario.findAll", query="SELECT c FROM Comentario c")
public class Comentario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idcomentario;

	private String comentario;

	//bi-directional many-to-one association to Postagem
	@ManyToOne
	@JoinColumn(name="cod_post")
	private Postagem postagem1;

	//bi-directional one-to-one association to Postagem
//	@OneToOne
//	@JoinColumn(name="cod_post")
//	private Postagem postagem2;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="codusuario")
	private Usuario usuario;

	public Comentario() {
	}

	public int getIdcomentario() {
		return this.idcomentario;
	}

	public void setIdcomentario(int idcomentario) {
		this.idcomentario = idcomentario;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Postagem getPostagem1() {
		return this.postagem1;
	}

	public void setPostagem1(Postagem postagem1) {
		this.postagem1 = postagem1;
	}

//	public Postagem getPostagem2() {
//		return this.postagem2;
//	}
//
//	public void setPostagem2(Postagem postagem2) {
//		this.postagem2 = postagem2;
//	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}