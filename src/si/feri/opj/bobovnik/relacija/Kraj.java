package si.feri.opj.bobovnik.relacija;

public class Kraj {
	private String kraj, drzava;

	public Kraj() {

	}

	public Kraj(String kraj, String drzava) {
		this.kraj = kraj;
		this.drzava = drzava;
	}

	public String getKraj() {
		return kraj;
	}

	public String getDrzava() {
		return drzava;
	}

	@Override
	public String toString() {
		return kraj + " " + drzava;
	}
}