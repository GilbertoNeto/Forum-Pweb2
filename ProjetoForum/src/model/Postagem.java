package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the postagem database table.
 * 
 */
@Entity
@NamedQuery(name="Postagem.findAll", query="SELECT p FROM Postagem p")
public class Postagem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idpostagem;

	@Column(name="cod_comentario")
	private int codComentario;

	private String postagem;

	//bi-directional many-to-one association to Comentario
	@OneToMany(mappedBy="postagem1")
	private List<Comentario> comentarios;

	//bi-directional one-to-one association to Comentario
//	@OneToOne(mappedBy="postagem2")
//	private Comentario comentario;

	//bi-directional many-to-one association to Tema
	@ManyToOne
	@JoinColumn(name="cod_tema")
	private Tema tema;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="cod_usuario")
	private Usuario usuario;

	public Postagem() {
	}

	public int getIdpostagem() {
		return this.idpostagem;
	}

	public void setIdpostagem(int idpostagem) {
		this.idpostagem = idpostagem;
	}

	public int getCodComentario() {
		return this.codComentario;
	}

	public void setCodComentario(int codComentario) {
		this.codComentario = codComentario;
	}

	public String getPostagem() {
		return this.postagem;
	}

	public void setPostagem(String postagem) {
		this.postagem = postagem;
	}

	public List<Comentario> getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Comentario addComentario(Comentario comentario) {
		getComentarios().add(comentario);
		comentario.setPostagem1(this);

		return comentario;
	}

	public Comentario removeComentario(Comentario comentario) {
		getComentarios().remove(comentario);
		comentario.setPostagem1(null);

		return comentario;
	}

//	public Comentario getComentario() {
//		return this.comentario;
//	}
//
//	public void setComentario(Comentario comentario) {
//		this.comentario = comentario;
//	}

	public Tema getTema() {
		return this.tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}