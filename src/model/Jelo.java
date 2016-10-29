package model;

public class Jelo {
	
	public String Naziv;
	public String Tekst;
	public float Cena;
	
	
	public String getNaziv() {
		return Naziv;
	}
	public void setNaziv(String naziv) {
		Naziv = naziv;
	}
	public String getTekst() {
		return Tekst;
	}
	public Jelo() {
		super();
		// TODO Auto-generated constructor stub
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
	public Jelo(String naziv, String tekst, float cena) {
		super();
		Naziv = naziv;
		Tekst = tekst;
		Cena = cena;
	}

}
