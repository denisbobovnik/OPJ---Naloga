package si.feri.opj.bobovnik.izjeme;

public class NevarniTovorException extends Exception {
	public NevarniTovorException() {
		super("Na ladjo, ki ima natovorjen zabojnik s toksi�no vsebino, se ne sme nalagati zabojnik z �ivim tovorom!");
	}
}