package init.model;

import init.modelFromDB.OcenaRestoranaEntity;
import init.modelFromDB.RestoranEntity;

import java.util.ArrayList;

/**
 * This class was created by Jan Varga leta gospodnjeg 2016
 * something something
 */
public class RestoranOcenaDTO {

	private RestoranEntity restoranEntity;
	private double ocena;

    public RestoranEntity getRestoranEntity() {
        return restoranEntity;
    }

    public void setRestoranEntity(RestoranEntity restoranEntity) {
        this.restoranEntity = restoranEntity;
    }

    public double getOcena() {
        return ocena;
    }

    public void setOcena(double ocena) {
        this.ocena = ocena;
    }

    public RestoranOcenaDTO(RestoranEntity restoranEntity, double ocena) {
		super();
		this.restoranEntity = restoranEntity;
		this.ocena = ocena;
	}
	public RestoranOcenaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
}