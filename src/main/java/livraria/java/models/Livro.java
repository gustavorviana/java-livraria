package livraria.java.models;

public class Livro {
	private int id;
	private String titulo;
	private String autor;
	private String categoria;
	private float valor;
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId(){
	    return this.id;
	}

	public String getTitulo(){
	    return this.titulo;
	}

	public void setTitulo(String titulo){
	    this.titulo = titulo;
	}

	public String getAutor(){
	    return this.autor;
	}

	public void setAutor(String autor){
	    this.autor = autor;
	}

	public String getCategoria(){
	    return this.categoria;
	}

	public void setCategoria(String categoria){
	    this.categoria = categoria;
	}

	public float getValor(){
	    return this.valor;
	}

	public void setValor(float valor){
	    this.valor = valor;
	}
}
