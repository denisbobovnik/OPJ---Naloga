package si.feri.opj.bobovnik.prevoz;

import si.feri.opj.bobovnik.zaboji.Zabojnik;

public class Tovornjak extends PrevoznoSredstvo {
	private int maxHitrost;

	public Tovornjak(String naziv, int povprecnaHitrost, int maxHitrost) {
		super(naziv, povprecnaHitrost);
		this.maxHitrost = maxHitrost;
		super.poljeZabojnikov = new Zabojnik[1];
	}

	public int getMaxHitrost() {
		return maxHitrost;
	}

	@Override
	public int vrniKapaciteto() {
		return poljeZabojnikov.length;
	}

	@Override
	public double vrniZasedenost() {
		if (poljeZabojnikov[0] == null)
			return 0.0;
		else
			return 100.0;
	}

	@Override
	public String toString() {
		return super.toString() + " " + maxHitrost;
	}
}