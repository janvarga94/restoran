package model;

public class Jelo {
	
	private String naziv;
	private String tekst;
	private float cena;
	
	
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		naziv = naziv;
	}
	public String getTekst() {
		return tekst;
	}
	public Jelo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setTekst(String tekst) {
		tekst = tekst;
	}
	public float getCena() {
		return cena;
	}
	public void setCena(float cena) {
		cena = cena;
	}
	public Jelo(String naziv, String tekst, float cena) {
		naziv = naziv;
		tekst = tekst;
		cena = cena;
	}

}
