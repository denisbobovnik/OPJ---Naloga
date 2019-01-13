package si.feri.opj.bobovnik.prevoz;

import si.feri.opj.bobovnik.zaboji.Zabojnik;

abstract public class PrevoznoSredstvo {
	private String naziv;
	private int povprecnaHitrost;
	protected Zabojnik[] poljeZabojnikov;

	public PrevoznoSredstvo() {

	}

	public PrevoznoSredstvo(String naziv) {
		this.naziv = naziv;
	}

	public PrevoznoSredstvo(String naziv, int povprecnaHitrost) {
		this(naziv);
		this.povprecnaHitrost = povprecnaHitrost;
	}

	public String getNaziv() {
		return naziv;
	}

	public int getPovprecnaHitrost() {
		return povprecnaHitrost;
	}

	public void setPovprecnaHitrost(int povprecnaHitrost) {
		this.povprecnaHitrost = povprecnaHitrost;
	}

	abstract public int vrniKapaciteto();

	abstract public double vrniZasedenost();

	@Override
	public String toString() {
		String niz = naziv + " " + povprecnaHitrost;
		niz += " ";
		/*for (int i = 0; i < poljeZabojnikov.length; i++) {
			if (poljeZabojnikov[i] == null)
				continue;
			niz += (poljeZabojnikov[i].toString());
		}*/
		return niz;
	}
}