import java.time.LocalDate;
import bankprojekt.verarbeitung.GesperrtException;
import bankprojekt.verarbeitung.Girokonto;
import bankprojekt.verarbeitung.Konto;
import bankprojekt.verarbeitung.Kontoart;
import bankprojekt.verarbeitung.Kunde;
import bankprojekt.verarbeitung.Sparbuch;
import bankprojekt.verarbeitung.Student;


/**
 * Testprogramm für Konten
 * @author Doro
 *
 */
public class Kontentest {

	/**
	 * Testprogramm für Konten
	 * @param args wird nicht benutzt
	 */
	public static void main(String[] args) {
		Student ich = new Student("Lucas", "Gertsch", "Zuhause", LocalDate.parse("1988-09-20"), "Angewandte Informatik", "HTW Berlin", LocalDate.parse("2018-01-01"), 5);
		
		//Student Lucas = new Student("Lucas", "Gertsch", "Zuhause", LocalDate.parse("1988-09-20"), "Angewandte Informatik", "HTW Berlin", LocalDate.parse("2018-01-01"), 5);
		
		//System.out.println(Lucas.toString());
		
 		//Konto k = new Konto();		
		
		Girokonto meinGiro = new Girokonto(ich, 1234, 1000.0);
		meinGiro.einzahlen(50);
		System.out.println(meinGiro);
		
		Sparbuch meinSpar = new Sparbuch(ich, 9876);
		meinSpar.einzahlen(50);
		try
		{
			boolean hatGeklappt = meinSpar.abheben(70);
			System.out.println("Abhebung hat geklappt: " + hatGeklappt);
			System.out.println(meinSpar);
		}
		catch (GesperrtException e)
		{
			System.out.println("Zugriff auf gesperrtes Konto - Polizei rufen!");
		}
		
		Konto k = new Girokonto();  //kontostand müsste 0 sein
		try {
			k.abheben(100);  //-100
			//k.aufKonsole();
		} catch (GesperrtException e) {}
		
		int a= 100;
		int b = a;  //Kopie
		a = a+50;
		System.out.println(b);  // 100
		
		Konto zweites = k;  //Referenz, keine Kopie
		k.einzahlen(300);  //200
		System.out.println(zweites);   //hier auch 200!!!!
		
		ich.setAdresse("woandershin");
		System.out.println(meinGiro);
		
		Kontoart art, art2 = Kontoart.SPARBUCH;
		art = Kontoart.GIROKONTO;
		if(art == Kontoart.GIROKONTO)
		{ 
			System.out.println("Sie wollen ein Girokonto!");
		}
		else
			System.out.println("Sie kriegen ein Sparbuch!");
		
		System.out.println(art.name()); // "GIROKONTO"
		System.out.println(art.ordinal()); // 0
		
		art = Kontoart.valueOf("FESTGELDKONTO");
		System.out.println(art.getBeschreibung()); // "nur fuer die Fantasie
		
		System.out.println("Unser Prospekt:");
		for(int i=0; i < Kontoart.values().length; i++)
		{
			System.out.println(Kontoart.values()[i]);
		}
		
		System.out.println(ich.toString());
		
		
		
		
		
		
	}

}
