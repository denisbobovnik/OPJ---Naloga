package si.feri.opj.bobovnik.izjeme;

public class TovorniProstorPolnException extends Exception {
	public TovorniProstorPolnException() {
		super("Tovorni prostor je poln!");
	}
}