package si.feri.opj.bobovnik;

import java.time.LocalDate;
import si.feri.opj.bobovnik.izjeme.MaksimalnaObremenitevPresezenaException;
import si.feri.opj.bobovnik.izjeme.NevarniTovorException;
import si.feri.opj.bobovnik.izjeme.TovorniProstorPolnException;
import si.feri.opj.bobovnik.izjeme.ZabojnikPolnException;
import si.feri.opj.bobovnik.izjeme.ZabojnikPreobremenjenException;
import si.feri.opj.bobovnik.predmeti.Predmet;
import si.feri.opj.bobovnik.prevoz.TovornaLadja;
import si.feri.opj.bobovnik.relacija.Kraj;
import si.feri.opj.bobovnik.relacija.Pot;
import si.feri.opj.bobovnik.tipi.Nevarnost;
import si.feri.opj.bobovnik.zaboji.NevarniZabojnik;
import si.feri.opj.bobovnik.zaboji.Zabojnik;

public class Zagonski {

	public static void main(String[] args) {
		// test metod ILogistike, implementiranih v razredu TovornaLadja
		TovornaLadja borisSHIP = new TovornaLadja("borisSHIP", 50, 500000, 15, 50);
		Zabojnik borisov = new Zabojnik("XX-016", 200);
		try {
			borisov.dodajPredmet(new Predmet("345678909876", "majoneza", 1));
		} catch (ZabojnikPreobremenjenException e) {
			e.printStackTrace();
		} catch (ZabojnikPolnException e1) {
			e1.printStackTrace();
		}
		try {
			borisSHIP.natovoriZabojnik(borisov);
		} catch (TovorniProstorPolnException e2) {
			e2.printStackTrace();
		} catch (MaksimalnaObremenitevPresezenaException e3) {
			e3.printStackTrace();
		} catch (NevarniTovorException e4) {
			e4.printStackTrace();
		}
		System.out.println(borisSHIP);
		borisSHIP.raztovoriZabojnik(borisov);
		System.out.println(borisSHIP);

		// test vraèanja lastnih izjem
		Zabojnik tezek = new Zabojnik("tezek", 200);
		try {
			tezek.dodajPredmet(new Predmet("jdsnfjkdsnf", "lala", 50));
			tezek.dodajPredmet(new Predmet("jdsnfjkdsnf", "lala", 50));
			tezek.dodajPredmet(new Predmet("jdsnfjkdsnf", "lala", 50));
			// tezek.dodajPredmet(new Predmet("jdsnfjkdsnf", "lala", 2));
		} catch (ZabojnikPreobremenjenException e5) {
			e5.printStackTrace();
		} catch (ZabojnikPolnException e6) {
			e6.printStackTrace();
		}

		// test izjeme NevarniTovorException
		System.out.print("\n");
		TovornaLadja naloga4 = new TovornaLadja("naloga4", 50, 50000, 1, 3);
		NevarniZabojnik prvi1 = new NevarniZabojnik("ooos", 5000000, Nevarnost.TOKSIÈNO);
		Zabojnik prvi2 = new Zabojnik("ooos", 5000000);
		NevarniZabojnik toksicen1 = new NevarniZabojnik("toksicen1", 5000000, Nevarnost.TOKSIÈNO);
		try {
			prvi1.dodajPredmet(new Predmet("jdsnfjkdsnf", "lala", 49000));
			prvi2.dodajPredmet(new Predmet("jdsnfjkdsnf", "lala", 500));
			toksicen1.dodajPredmet(new Predmet("jdsnfjkdsnf", "lala", 400));

		} catch (ZabojnikPolnException e7) {
			e7.printStackTrace();
		} catch (ZabojnikPreobremenjenException e8) {
			e8.printStackTrace();
		}
		try {
			naloga4.natovoriZabojnik(toksicen1);
			naloga4.natovoriZabojnik(prvi1);
			naloga4.natovoriZabojnik(prvi2);
		} catch (TovorniProstorPolnException e9) {
			e9.printStackTrace();
		} catch (MaksimalnaObremenitevPresezenaException e10) {
			e10.printStackTrace();
		} catch (NevarniTovorException e11) {
			e11.printStackTrace();
		}
		System.out.println(naloga4.toString());
		
		
		
		
		
		
		
		
		
		
		//test prve naloge v povezavi s 4-to
		LocalDate datumZacetkaPoti = LocalDate.parse("2012-12-31");
		int dolzinaPoti = 1000;
		String krajO = "Maribor";
		String drzavaO = "Slovenija";
		String krajC = "Beograd";
		String drzavaC = "Srbija";
		TovornaLadja prevoznoSredstvo = new TovornaLadja("Lada", 50, 50000, 1, 3);
		int povprecnaHitrost = 20;
		Kraj krajOdhoda = new Kraj(krajO, drzavaO);
		Kraj krajCilja = new Kraj(krajC, drzavaC);
		Pot prva = new Pot(krajOdhoda, krajCilja);
		prva.setDatumZacetkaPoti(datumZacetkaPoti);		
		prva.setDolzinaPoti(dolzinaPoti);
		prva.setPrevoznoSredstvo(prevoznoSredstvo);
		prva.getPrevoznoSredstvo().setPovprecnaHitrost(povprecnaHitrost);
		System.out.print("\nDatum zaèetka poti: " + prva.getDatumZacetkaPoti() + " (oblika YYYY-MM-DD)\n");
		System.out.print("Dolžina poti: " + prva.getDolzinaPoti() + " km\n");
		System.out.print("Kraj in država odhoda: " + prva.getKrajOdhoda().getKraj() + ", " + prva.getKrajOdhoda().getDrzava() + "\n");
		System.out.print("Kraj in država cilja: " + prva.getKrajCilja().getKraj() + ", " + prva.getKrajCilja().getDrzava() + "\n");
		System.out.print("Naziv in povpreèna hitrost prevoznega sredstva: " + prva.getPrevoznoSredstvo().getNaziv() + ", " + prva.getPrevoznoSredstvo().getPovprecnaHitrost() + " km/h\n");
		System.out.print("Predviden datum prihoda na cilj: " + prva.izracunajDanPrihoda() + " (oblika YYYY-MM-DD)\n");

	}
}