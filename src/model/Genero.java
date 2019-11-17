package model;

public class Genero {

	private int codGenero;
	private String tipoGenero;
	
	public int getCodGenero() {
		return codGenero;
	}
	public void setCodGenero(int codGenero) {
		this.codGenero = codGenero;
	}
	public String getTipoGenero() {
		return tipoGenero;
	}
	public void setTipoGenero(String tipoGenero) {
		this.tipoGenero = tipoGenero;
	}
	@Override
	public String toString() {
		return this.tipoGenero;
	}
	
}
