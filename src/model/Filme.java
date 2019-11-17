package model;

public class Filme {
	
	private int idFilme;
	private String nomeFilme;
	private Genero generoFilme;
	
	public int getIdFilme() {
		return idFilme;
	}
	public void setIdFilme(int idFilme) {
		this.idFilme = idFilme;
	}
	public String getNomeFilme() {
		return nomeFilme;
	}
	public void setNomeFilme(String nomeFilme) {
		this.nomeFilme = nomeFilme;
	}
	public Genero getGeneroFilme() {
		return generoFilme;
	}
	public void setGeneroFilme(Genero generoFilme) {
		this.generoFilme = generoFilme;
	}
	
	@Override
	public String toString() {
		return this.nomeFilme;
	}

}
