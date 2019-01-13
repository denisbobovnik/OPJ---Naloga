package si.feri.opj.bobovnik.prevoz;

import si.feri.opj.bobovnik.zaboji.Zabojnik;

public class Vlak extends PrevoznoSredstvo {
	private int steviloVagonov;

	public Vlak(String naziv, int povprecnaHitrost, int steviloVagonov) {
		super(naziv, povprecnaHitrost);
		this.steviloVagonov = steviloVagonov;
		super.poljeZabojnikov = new Zabojnik[steviloVagonov];
	}

	public int getSteviloVagonov() {
		return steviloVagonov;
	}

	public void setSteviloVagonov(int steviloVagonov) {
		this.steviloVagonov = steviloVagonov;
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

	@Override
	public String toString() {
		return super.toString() + " " + steviloVagonov;
	}
}