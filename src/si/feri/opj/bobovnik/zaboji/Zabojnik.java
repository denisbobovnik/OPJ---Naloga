package si.feri.opj.bobovnik.zaboji;

import java.io.Serializable;

import si.feri.opj.bobovnik.izjeme.ZabojnikPolnException;
import si.feri.opj.bobovnik.izjeme.ZabojnikPreobremenjenException;
import si.feri.opj.bobovnik.predmeti.Predmet;

public class Zabojnik implements Serializable {
	private String oznaka;
	private int curTeza = 0;
	private int maxTeza;
	private Predmet[] poljePredmetov = new Predmet[3];

	public Zabojnik() {

	}

	public Zabojnik(String oznaka, int maxTeza) {
		this.oznaka = oznaka;
		this.maxTeza = maxTeza;
	}

	public void dodajPredmet(Predmet predmet) throws ZabojnikPolnException, ZabojnikPreobremenjenException {
		for (int i = 0; i < this.poljePredmetov.length; i++)
			if ((predmet.getTeza() > this.maxTeza) || (this.curTeza + predmet.getTeza() > this.maxTeza)) {
				System.out.print("\n");
				throw new ZabojnikPreobremenjenException();
			} else if ((this.poljePredmetov[i] != null) && ((i + 1) == this.poljePredmetov.length)) {
				System.out.print("\n");
				throw new ZabojnikPolnException();
			} else if (this.poljePredmetov[i] == null) {
				this.poljePredmetov[i] = predmet;
				this.curTeza += predmet.getTeza();
				System.out.print("\nTo sporoèilo pomeni, da se je predmet uspešno dodal v zabojnik!");
				break;
			}
	}

	public Predmet vrniPredmet(String crtnaKodaPredmeta) {
		for (int i = 0; i < poljePredmetov.length; i++)
			if (crtnaKodaPredmeta.compareTo(this.poljePredmetov[i].getCrtnaKodaPredmeta()) == 0)
				return poljePredmetov[i];
		return null;
	}

	public void odstraniPredmet(String crtnaKodaPredmeta) {
		for (int i = 0; i < poljePredmetov.length; i++) {
			if (poljePredmetov[i]==null) /*za GUI*/
				continue;
			if (crtnaKodaPredmeta.compareTo(this.poljePredmetov[i].getCrtnaKodaPredmeta()) == 0) {
				this.curTeza -= this.poljePredmetov[i].getTeza();
				this.poljePredmetov[i] = null;
				break;
			}
		}
	}

	public void odstraniPredmet(Predmet predmet) {
		for (int i = 0; i < poljePredmetov.length; i++)
			if ((predmet.toString()).compareTo(this.poljePredmetov[i].toString()) == 0) {
				this.curTeza -= this.poljePredmetov[i].getTeza();
				poljePredmetov[i] = null;
				break;
			}
	}

	public int izracunajSkupnoMasoTovora() {
		int temp = 0;
		for (int i = 0; i < poljePredmetov.length; i++)
			if (this.poljePredmetov[i] != null)
				temp += this.poljePredmetov[i].getTeza();
		return temp;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public int getMaxTeza() {
		return maxTeza;
	}

	public int getCurTeza() {
		return curTeza;
	}

	public void setMaxTeza(int maxTeza) {
		this.maxTeza = maxTeza;
	}
	//za gui
	public Predmet[] getPoljePredmetov() {
		return poljePredmetov;
	}
	public void setPoljePredmetov(Predmet[] poljePredmetov) {
		this.poljePredmetov = poljePredmetov;
	}

	@Override
	public String toString() {
		String niz = oznaka + " " + curTeza + " " + maxTeza;
		niz += " ";
		for (int i = 0; i < poljePredmetov.length; i++)
			if ((i != 14) && (poljePredmetov[i] != null))
				niz += (poljePredmetov[i] + " ");
			else if ((i == 14) && (poljePredmetov[i] != null))
				niz += (poljePredmetov[i]);
		return niz;
	}
}