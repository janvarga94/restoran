package init.model;

import java.util.ArrayList;

/**
 * This class is created by Jan Varga leta gospodnjeg 2016
 */
public class Restoran {
	private String naziv;
	private String vrsta;
	private ArrayList<Jelo> jelovnik;
	private Konfiguracija konfiguracija;
	private float Ocena;	
//	private HashMap<Jelo,float> OceneJela;

	public Restoran(String naziv, String vrsta, ArrayList<Jelo> jelovnik, init.model.Konfiguracija konfiguracija) {
		super();
		this.naziv = naziv;
		this.vrsta = vrsta;
		this.jelovnik = jelovnik;
		this.konfiguracija = konfiguracija;
	}
	public Restoran() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getVrsta() {
		return vrsta;
	}
	public void setVrsta(String vrsta) {
		this.vrsta = vrsta;
	}
	public ArrayList<Jelo> getJelovnik() {
		return jelovnik;
	}
	public void setJelovnik(ArrayList<Jelo> jelovnik) {
		this.jelovnik = jelovnik;
	}
	public Konfiguracija getKonfiguracija() {
		return konfiguracija;
	}
	public void setKonfiguracija(Konfiguracija konfiguracija) { this.konfiguracija = konfiguracija; }
	
	
	
}
