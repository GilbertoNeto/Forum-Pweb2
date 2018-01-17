package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private int idUsuario;

//	@Column(name="cod_coment")
//	private int codComent;
//
//	@Column(name="cod_postagem")
//	private int codPostagem;

	private String email;

	@Column(name="nome_usuario")
	private String nomeUsuario;

	private String senha;

	//bi-directional many-to-one association to Comentario
	@OneToMany(mappedBy="usuario", cascade=CascadeType.ALL)
	private ArrayList<Comentario> comentarios;

	//bi-directional many-to-one association to Postagem
	@OneToMany(mappedBy="usuario", cascade=CascadeType.ALL)
	private ArrayList<Postagem> postagems;

	public Usuario() {
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

//	public int getCodComent() {
//		return this.codComent;
//	}
//
//	public void setCodComent(int codComent) {
//		this.codComent = codComent;
//	}
//
//	public int getCodPostagem() {
//		return this.codPostagem;
//	}
//
//	public void setCodPostagem(int codPostagem) {
//		this.codPostagem = codPostagem;
//	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeUsuario() {
		return this.nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Comentario> getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(ArrayList<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Comentario addComentario(Comentario comentario) {
		getComentarios().add(comentario);
		comentario.setUsuario(this);

		return comentario;
	}

	public Comentario removeComentario(Comentario comentario) {
		getComentarios().remove(comentario);
		comentario.setUsuario(null);

		return comentario;
	}

	public ArrayList<Postagem> getPostagems() {
		return this.postagems;
	}

	public void setPostagems(ArrayList<Postagem> postagems) {
		this.postagems = postagems;
	}

	public Postagem addPostagem(Postagem postagem) {
		getPostagems().add(postagem);
		postagem.setUsuario(this);

		return postagem;
	}

	public Postagem removePostagem(Postagem postagem) {
		getPostagems().remove(postagem);
		postagem.setUsuario(null);

		return postagem;
	}

}