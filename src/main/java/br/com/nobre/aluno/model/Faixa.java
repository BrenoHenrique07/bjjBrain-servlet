package br.com.nobre.aluno.model;

public enum Faixa {
	
	BRANCA(1, "Branca"),
	AZUL(2, "Azul"),
	ROXA(3, "Roxa"),
	MARROM(4, "Marrom"),
	PRETA(5, "Preta");
	
	private int id;
	private String nome;
	
	private Faixa(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public static Faixa fromId(int id) {
		for (Faixa faixa : values()) {
			if (faixa.getId() == id) {
				return faixa;
			}
		}
		throw new IllegalArgumentException("Faixa com id " + id + " não encontrada.");
	}
}


