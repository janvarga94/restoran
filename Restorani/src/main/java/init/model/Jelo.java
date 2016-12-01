package init.model;

public class Jelo {
	
	private String naziv;
	private String tekst;
	private float cena;
	
	
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getTekst() {
		return tekst;
	}
	public Jelo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setTekst(String tekst) {
		this.tekst = tekst;
	}
	public float getCena() {
		return cena;
	}
	public void setCena(float cena) {
		this.cena = cena;
	}
	public Jelo(String naziv, String tekst, float cena) {
		this.naziv = naziv;
		this.tekst = tekst;
		this.cena = cena;
	}

}
