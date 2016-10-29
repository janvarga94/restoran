package model;

public class Pice {
	public String Naziv;
	public String Tekst;
	public float Cena;
	
	public Pice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pice(String naziv, String tekst, float cena) {
		super();
		Naziv = naziv;
		Tekst = tekst;
		Cena = cena;
	}

	public String getNaziv() {
		return Naziv;
	}

	public void setNaziv(String naziv) {
		Naziv = naziv;
	}

	public String getTekst() {
		return Tekst;
	}

	public void setTekst(String tekst) {
		Tekst = tekst;
	}

	public float getCena() {
		return Cena;
	}

	public void setCena(float cena) {
		Cena = cena;
	}
	
}
