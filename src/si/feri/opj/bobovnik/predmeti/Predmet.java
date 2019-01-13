package si.feri.opj.bobovnik.predmeti;

import java.io.Serializable;

public class Predmet implements Serializable {
	private String crtnaKodaPredmeta, nazivPredmeta;
	private int teza;

	public Predmet(String crtnaKodaPredmeta, String nazivPredmeta, int teza) {
		this.crtnaKodaPredmeta = crtnaKodaPredmeta;
		this.nazivPredmeta = nazivPredmeta;
		this.teza = teza;
	}

	public String getCrtnaKodaPredmeta() {
		return crtnaKodaPredmeta;
	}
	public String getNazivPredmeta() {
		return nazivPredmeta;
	}
	public int getTeza() {
		return teza;
	}
	public void setNazivPredmeta(String nazivPredmeta) {
		this.nazivPredmeta = nazivPredmeta;
	}
	public void setTeza(int teza) {
		this.teza = teza;
	}

	@Override
	public String toString() {
		return crtnaKodaPredmeta + " " + nazivPredmeta + " " + teza;
	}
}