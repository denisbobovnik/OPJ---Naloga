package si.feri.opj.bobovnik.zaboji;

import si.feri.opj.bobovnik.tipi.Nevarnost;
import si.feri.opj.bobovnik.zaboji.Zabojnik;

public class NevarniZabojnik extends Zabojnik {
	private Nevarnost nevarnost;

	public NevarniZabojnik(String oznaka, int maxTeza, Nevarnost nevarnost) {
		super(oznaka, maxTeza);
		this.nevarnost = nevarnost;
	}

	public Nevarnost getNevarnost() {
		return nevarnost;
	}

	@Override
	public String toString() {
		return super.toString() + " " + nevarnost;
	}
}