package si.feri.opj.bobovnik.zaboji;

import si.feri.opj.bobovnik.zaboji.Zabojnik;

public class HladilniZabojnik extends Zabojnik {
	private double temperatura;

	public HladilniZabojnik(String oznaka, int maxTeza, double temperatura) {
		super(oznaka, maxTeza);
		this.temperatura = temperatura;
	}

	public double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}

	@Override
	public String toString() {
		return super.toString() + " " + temperatura;
	}
}