package si.feri.opj.bobovnik.relacija;

import java.time.LocalDate;
import java.lang.IllegalArgumentException;
import si.feri.opj.bobovnik.prevoz.PrevoznoSredstvo;

public class Pot {
	private LocalDate datumZacetkaPoti;
	private int dolzinaPoti;
	private Kraj krajOdhoda, krajCilja;
	private PrevoznoSredstvo prevoznoSredstvo;

	public Pot() {

	}

	public Pot(Kraj krajOdhoda, Kraj krajCilja) {
		this.krajOdhoda = krajOdhoda;
		this.krajCilja = krajCilja;
	}

	public LocalDate getDatumZacetkaPoti() {
		return datumZacetkaPoti;
	}

	public void setDatumZacetkaPoti(LocalDate datumZacetkaPoti) {
		this.datumZacetkaPoti = datumZacetkaPoti;
	}

	public int getDolzinaPoti() {
		return dolzinaPoti;
	}

	public void setDolzinaPoti(int dolzinaPoti) {
		this.dolzinaPoti = dolzinaPoti;
	}

	public Kraj getKrajOdhoda() {
		return krajOdhoda;
	}

	public Kraj getKrajCilja() {
		return krajCilja;
	}

	public PrevoznoSredstvo getPrevoznoSredstvo() {
		return prevoznoSredstvo;
	}

	public void setPrevoznoSredstvo(PrevoznoSredstvo prevoznoSredstvo) {
		this.prevoznoSredstvo = prevoznoSredstvo;
	}

	public LocalDate izracunajDanPrihoda() throws IllegalArgumentException {
		if (getDolzinaPoti() < 0)
			throw new IllegalArgumentException("Neveljavna dolžina poti!");
		else if (this.prevoznoSredstvo.getPovprecnaHitrost() < 0)
			throw new IllegalArgumentException("Neveljavna povpreèna hitrost prevoznega sredstva!");
		int cas = (getDolzinaPoti() / this.prevoznoSredstvo.getPovprecnaHitrost()) / 24;
		return (getDatumZacetkaPoti().plusDays(cas));
	}

	@Override
	public String toString() {
		return datumZacetkaPoti + " " + dolzinaPoti + " " + krajOdhoda + " " + krajCilja + " " + prevoznoSredstvo;
	}
}