package si.feri.opj.bobovnik.prevoz;

import java.io.Serializable;

import si.feri.opj.bobovnik.izjeme.MaksimalnaObremenitevPresezenaException;
import si.feri.opj.bobovnik.izjeme.NevarniTovorException;
import si.feri.opj.bobovnik.izjeme.TovorniProstorPolnException;
import si.feri.opj.bobovnik.tipi.Nevarnost;
import si.feri.opj.bobovnik.zaboji.NevarniZabojnik;
import si.feri.opj.bobovnik.zaboji.Zabojnik;
import si.feri.opj.bobovnik.vmesniki.ILogistika;

public class TovornaLadja extends PrevoznoSredstvo implements ILogistika, Serializable {
	private int maxObremenitev, curObremenitev = 0, sirinaPrtljaznegaProstora, dolzinaPrtljaznegaProstora;
	private boolean toksicno = false;

	public TovornaLadja(String naziv, int povprecnaHitrost, int maxObremenitev, int sirinaPrtljaznegaProstora,
			int dolzinaPrtljaznegaProstora) {
		super(naziv, povprecnaHitrost);
		this.maxObremenitev = maxObremenitev;
		this.sirinaPrtljaznegaProstora = sirinaPrtljaznegaProstora;
		this.dolzinaPrtljaznegaProstora = dolzinaPrtljaznegaProstora;
		super.poljeZabojnikov = new Zabojnik[sirinaPrtljaznegaProstora * dolzinaPrtljaznegaProstora];
	}

	public int getMaxObremenitev() {
		return maxObremenitev;
	}

	public int getSirinaPrtljaznegaProstora() {
		return sirinaPrtljaznegaProstora;
	}

	public int getDolzinaPrtljaznegaProstora() {
		return dolzinaPrtljaznegaProstora;
	}
	
	/*za gui*/
	public void setMaxObremenitev(int maxObremenitev) {
		this.maxObremenitev = maxObremenitev;
	}

	public void setSirinaPrtljaznegaProstora(int sirinaPrtljaznegaProstora) {
		this.sirinaPrtljaznegaProstora = sirinaPrtljaznegaProstora;
	}

	public void setDolzinaPrtljaznegaProstora(int dolzinaPrtljaznegaProstora) {
		this.dolzinaPrtljaznegaProstora = dolzinaPrtljaznegaProstora;
	}
	public void setVelikostProstora(int velikostProstora) {
		super.poljeZabojnikov = new Zabojnik[velikostProstora];
	}
	public Zabojnik[] getPoljeZabojnikov() {
		return super.poljeZabojnikov;
	}
	public void setPoljeZabojnikov(Zabojnik[] temp) {
		super.poljeZabojnikov = temp;
	}

	@Override
	public int vrniKapaciteto() {
		return poljeZabojnikov.length;
	}

	@Override
	public double vrniZasedenost() {
		double stevec = 0;
		for (int i = 0; i < poljeZabojnikov.length; i++)
			if (poljeZabojnikov[i] != null)
				stevec++;
		return (stevec / poljeZabojnikov.length) * 100;
	}

	public void natovoriZabojnik(Zabojnik zabojnik)
			throws TovorniProstorPolnException, MaksimalnaObremenitevPresezenaException, NevarniTovorException {
		for (int i = 0; i < super.poljeZabojnikov.length; i++)
			if ((zabojnik.getCurTeza() > this.maxObremenitev)
					|| (this.curObremenitev + zabojnik.getCurTeza() > this.maxObremenitev)) {
				System.out.print("\n");
				throw new MaksimalnaObremenitevPresezenaException();
			} else if ((zabojnik instanceof NevarniZabojnik)
					&& (((NevarniZabojnik) zabojnik).getNevarnost() == Nevarnost.ŽIVI_TOVOR) && (toksicno)) {
				System.out.print("\n");
				throw new NevarniTovorException();
			} else if ((super.poljeZabojnikov[i] != null) && ((i + 1) == super.poljeZabojnikov.length)) {
				System.out.print("\n");
				throw new TovorniProstorPolnException();
			} else if (super.poljeZabojnikov[i] == null) {
				super.poljeZabojnikov[i] = zabojnik;
				this.curObremenitev += zabojnik.getCurTeza();
				if ((zabojnik instanceof NevarniZabojnik)
						&& (((NevarniZabojnik) zabojnik).getNevarnost() == Nevarnost.TOKSIÈNO))
					this.toksicno = true;
				System.out.print("\nTo sporoèilo pomeni, da se je zabojnik uspešno natovoril na tovorno ladjo!\n");
				break;
			}
	}

	public void raztovoriZabojnik(Zabojnik zabojnik) {
		for (int i = 0; i < super.poljeZabojnikov.length; i++)
			if ((zabojnik.toString()).compareTo(super.poljeZabojnikov[i].toString()) == 0) {
				this.curObremenitev -= super.poljeZabojnikov[i].getCurTeza();
				super.poljeZabojnikov[i] = null;
				System.out.print("\nTo sporoèilo pomeni, da se je zabojnik uspešno raztovoril iz tovorne ladje!\n");
				break;
			}
	}

	public void raztovoriZabojnik(String oznakaZabojnika) {
		for (int i = 0; i < super.poljeZabojnikov.length; i++) {
			if(super.poljeZabojnikov[i] == null)
				continue;
			if (oznakaZabojnika.compareTo(super.poljeZabojnikov[i].getOznaka()) == 0) {
				this.curObremenitev -= super.poljeZabojnikov[i].getCurTeza();
				super.poljeZabojnikov[i] = null;
				System.out.print("\nTo sporoèilo pomeni, da se je zabojnik uspešno raztovoril iz tovorne ladje!\n");
				break;
			}
		}
	}

	public boolean jeZabojnikNatovorjen(String oznakaZabojnika) {
		for (int i = 0; i < super.poljeZabojnikov.length; i++)
			if (oznakaZabojnika.compareTo(super.poljeZabojnikov[i].getOznaka()) == 0)
				return true;
		return false;
	}

	@Override
	public String toString() {
		return super.getNaziv() + " " + super.getPovprecnaHitrost() + " " + maxObremenitev + " " + sirinaPrtljaznegaProstora + " "
				+ dolzinaPrtljaznegaProstora;
	}
}