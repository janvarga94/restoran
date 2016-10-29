package model;

import java.util.ArrayList;


public class Restoran {
	private String Naziv;
	private String Vrsta;
	private ArrayList<Jelo> Jelovnik;
	private Konfiguracija Konfiguracija;
	private float Ocena;	
//	private HashMap<Jelo,float> OceneJela;

	public Restoran(String naziv, String vrsta, ArrayList<Jelo> jelovnik, model.Konfiguracija konfiguracija) {
		super();
		Naziv = naziv;
		Vrsta = vrsta;
		Jelovnik = jelovnik;
		Konfiguracija = konfiguracija;
	}
	public Restoran() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNaziv() {
		return Naziv;
	}
	public void setNaziv(String naziv) {
		Naziv = naziv;
	}
	public String getVrsta() {
		return Vrsta;
	}
	public void setVrsta(String vrsta) {
		Vrsta = vrsta;
	}
	public ArrayList<Jelo> getJelovnik() {
		return Jelovnik;
	}
	public void setJelovnik(ArrayList<Jelo> jelovnik) {
		Jelovnik = jelovnik;
	}
	public Konfiguracija getKonfiguracija() {
		return Konfiguracija;
	}
	public void setKonfiguracija(Konfiguracija konfiguracija) {
		Konfiguracija = konfiguracija;
	}
	
	
	
}
