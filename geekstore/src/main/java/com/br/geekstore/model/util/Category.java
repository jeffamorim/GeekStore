package com.br.geekstore.model.util;

public enum Category {

	COLECIONAVEIS(1,"Colecionáveis"),
	VESTUARIO(2,"Vestuário"),
	LIVROS(3,"Livros"),
	PERIFERICOS(4,"Periféricos"),
	DECORACAO(5,"Decoração");

	private String nomeCategoria;
	private Integer id;

	Category (Integer id, String nome){
		this.id = id;
		this.nomeCategoria = nome;
	}

	public static Category create(Integer id){
		if(id==1) return Category.COLECIONAVEIS;
		else if(id==2) return Category.VESTUARIO;
		else if(id==3) return Category.LIVROS;
		else if(id==4) return Category.PERIFERICOS;
		else if(id==5) return Category.DECORACAO;
		else return null;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public Integer getId() {
		return id;
	}

}
