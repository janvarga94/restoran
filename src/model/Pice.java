package model;

public class Pice {
	private String naziv;
	private String tekst;
	private float cena;
	
	public Pice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pice(String naziv, String tekst, float cena) {
		super();
		naziv = naziv;
		tekst = tekst;
		cena = cena;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		naziv = naziv;
	}

	public String getTekst() {
		return tekst;
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
	
}
