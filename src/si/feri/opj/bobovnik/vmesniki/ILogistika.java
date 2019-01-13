package si.feri.opj.bobovnik.vmesniki;

import si.feri.opj.bobovnik.izjeme.MaksimalnaObremenitevPresezenaException;
import si.feri.opj.bobovnik.izjeme.NevarniTovorException;
import si.feri.opj.bobovnik.izjeme.TovorniProstorPolnException;
import si.feri.opj.bobovnik.zaboji.Zabojnik;

public interface ILogistika {

	abstract public void natovoriZabojnik(Zabojnik zabojnik)
			throws TovorniProstorPolnException, MaksimalnaObremenitevPresezenaException, NevarniTovorException;

	abstract public void raztovoriZabojnik(Zabojnik zabojnik);

	abstract public void raztovoriZabojnik(String oznakaZabojnika);

	abstract public boolean jeZabojnikNatovorjen(String oznakaZabojnika);
}