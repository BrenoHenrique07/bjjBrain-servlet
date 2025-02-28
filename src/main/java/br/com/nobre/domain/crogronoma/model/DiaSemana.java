package br.com.nobre.domain.crogronoma.model;

public enum DiaSemana {
	
	DOMINGO(1, "Branca"),
	SEGUNDA(2, "Azul"),
	TERCA(3, "Roxa"),
	QUARTA(4, "Marrom"),
	QUINTA(5, "Preta"),
	SEXTA(6, "Sexta-feira"),
	SABADO(7, "Sabado");
	
	private int id;
	private String nome;
	
	private DiaSemana(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public static DiaSemana fromId(int id) {
		for (DiaSemana dia : values()) {
			if (dia.getId() == id) {
				return dia;
			}
		}
		throw new IllegalArgumentException(String.format("Dia da semana %d não existe", id));
	}
	
	public static boolean diaSemanaExists(int id) {
		for (DiaSemana dia : values()) {
			if (dia.getId() == id) {
				return true;
			}
		}
		return false;
	}
	
}
