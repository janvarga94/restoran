package model;

import java.util.ArrayList;


public class Restoran {
	private String naziv;
	private String vrsta;
	private ArrayList<Jelo> jelovnik;
	private Konfiguracija konfiguracija;
	private float Ocena;	
//	private HashMap<Jelo,float> OceneJela;

	public Restoran(String naziv, String vrsta, ArrayList<Jelo> jelovnik, model.Konfiguracija konfiguracija) {
		super();
		naziv = naziv;
		vrsta = vrsta;
		jelovnik = jelovnik;
		konfiguracija = konfiguracija;
	}
	public Restoran() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		naziv = naziv;
	}
	public String getVrsta() {
		return vrsta;
	}
	public void setVrsta(String vrsta) {
		vrsta = vrsta;
	}
	public ArrayList<Jelo> getJelovnik() {
		return jelovnik;
	}
	public void setJelovnik(ArrayList<Jelo> jelovnik) {
		jelovnik = jelovnik;
	}
	public Konfiguracija getKonfiguracija() {
		return konfiguracija;
	}
	public void setKonfiguracija(Konfiguracija konfiguracija) {
		konfiguracija = konfiguracija;
	}
	
	
	
}
