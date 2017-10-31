
package bankprojekt.verarbeitung;

/**
 * stellt ein allgemeines Konto dar
 * @author ich wars
 */
public abstract class Konto 
{
	/** 
	 * der Kontoinhaber
	 */
	private Kunde inhaber;

	/**
	 * die Kontonummer
	 */
	private final long nummer;

	/**
	 * der aktuelle Kontostand
	 */
	private double kontostand;

	/*
	 * Die Währung in der Konto geführt wird
	 */
	protected Waehrung waehrung = Waehrung.EUR;
	
	/**
	 * setzt den aktuellen Kontostand
	 * @param kontostand neuer Kontostand
	 */
	protected void setKontostand(double kontostand) {
		this.kontostand = kontostand;
	}

	/**
	 * Wenn das Konto gesperrt ist (gesperrt = true), können keine Aktionen daran mehr vorgenommen werden,
	 * die zum Schaden des Kontoinhabers wären (abheben, Inhaberwechsel)
	 */
	private boolean gesperrt;

	/**
	 * Setzt die beiden Eigenschaften kontoinhaber und kontonummer auf die angegebenen Werte,
	 * der anfängliche Kontostand wird auf 0 gesetzt.
	 *
	 * @param inhaber Kunde
	 * @param kontonummer long
	 * @throws IllegalArgumentException wenn der Inhaber null
	 */
	public Konto(Kunde inhaber, long kontonummer) {
		if(inhaber == null)
			throw new IllegalArgumentException("Inhaber darf nicht null sein!");
		this.inhaber = inhaber;
		this.nummer = kontonummer;
		this.kontostand = 0;
		this.gesperrt = false;
	}
	
	/**
	 * setzt alle Eigenschaften des Kontos auf Standardwerte
	 */
	public Konto() {
		this(Kunde.MUSTERMANN, 1234567);
		// noch was tun
	}

	/**
	 * liefert den Kontoinhaber zurück
	 * @return   Kunde
	 */
	public final Kunde getInhaber() {
		return this.inhaber;
	}
	
	/**
	 * setzt den Kontoinhaber
	 * @param kinh   neuer Kontoinhaber
	 * @throws GesperrtException wenn das Konto gesperrt ist
	 * @throws IllegalArgumentException wenn kinh null ist
	 */
	public final void setInhaber(Kunde kinh) throws GesperrtException{
		if (kinh == null)
			throw new IllegalArgumentException("Der Inhaber darf nicht null sein!");
		if(this.gesperrt)
			throw new GesperrtException(this.nummer);        
		this.inhaber = kinh;

	}
	
	/**
	 * liefert den aktuellen Kontostand
	 * @return   double
	 */
	public final double getKontostand() {
		return kontostand;
	}

	/**
	 * liefert die Kontonummer zurück
	 * @return   long
	 */
	public final long getKontonummer() {
		return nummer;
	}

	/**
	 * liefert zurück, ob das Konto gesperrt ist oder nicht
	 * @return
	 */
	public final boolean isGesperrt() {
		return gesperrt;
	}
	
	/**
	 * Erhöht den Kontostand um den eingezahlten Betrag.
	 *
	 * @param betrag double
	 * @throws IllegalArgumentException wenn der betrag negativ ist 
	 */
	public void einzahlen(double betrag) {
		if (betrag < 0) {
			throw new IllegalArgumentException("Negativer Betrag");
		}
		setKontostand(getKontostand() + betrag);
	}
	
	/**
	 * Gibt eine Zeichenkettendarstellung der Kontodaten zurück.
	 */
	@Override
	public String toString() {
		String ausgabe;
		ausgabe = "Kontonummer: " + this.getKontonummerFormatiert()
				+ System.getProperty("line.separator");
		ausgabe += "Inhaber: " + this.inhaber;
		ausgabe += "Aktueller Kontostand: " + this.kontostand + " Euro ";
		ausgabe += this.getGesperrtText() + System.getProperty("line.separator");
		return ausgabe;
	}

	/**
	 * Mit dieser Methode wird der geforderte Betrag vom Konto abgehoben, wenn es nicht gesperrt ist.
	 *
	 * @param betrag double
	 * @throws GesperrtException wenn das Konto gesperrt ist
	 * @throws IllegalArgumentException wenn der betrag negativ ist 
	 * @return true, wenn die Abhebung geklappt hat, 
	 * 		   false, wenn sie abgelehnt wurde
	 */
	public boolean abheben(double betrag, Waehrung w) throws GesperrtException, IllegalArgumentException {
		double kontostandInEuro = this.getKontostand() * this.waehrung.getKurs();
		double abhebeBetragInEuro = betrag * w.getKurs();
		double abhebeBetragInKontoWaehrung = this.waehrung.umrechnen(abhebeBetragInEuro); 
		if (betrag < 0 ) {
			throw new IllegalArgumentException();
		}
		if(this.isGesperrt())
			throw new GesperrtException(this.getKontonummer());
		
		if (kontostandInEuro >= abhebeBetragInEuro)
		{
			setKontostand(getKontostand() - abhebeBetragInKontoWaehrung);
			return true;
		}
		else
			return false;
	}
	
	/**
	 * sperrt das Konto, Aktionen zum Schaden des Benutzers sind nicht mehr möglich.
	 */
	public final void sperren() {
		this.gesperrt = true;
	}

	/**
	 * entsperrt das Konto, alle Kontoaktionen sind wieder möglich.
	 */
	public final void entsperren() {
		this.gesperrt = false;
	}
	
	
	/**
	 * liefert eine String-Ausgabe, wenn das Konto gesperrt ist
	 * @return "GESPERRT", wenn das Konto gesperrt ist, ansonsten ""
	 */
	public final String getGesperrtText()
	{
		if (this.gesperrt)
		{
			return "GESPERRT";
		}
		else
		{
			return "";
		}
	}
	
	/**
	 * liefert die ordentlich formatierte Kontonummer
	 * @return auf 10 Stellen formatierte Kontonummer
	 */
	public String getKontonummerFormatiert()
	{
		return String.format("%10d", this.nummer);
	}
	
	/**
	 * liefert den ordentlich formatierten Kontostand
	 * @return formatierter Kontostand mit 2 Nachkommastellen und Währungssymbol €
	 */
	public String getKontostandFormatiert()
	{
		return String.format("%10.2f %s"  , this.getKontostand(),  this.waehrung);
	}
	
	/**
	 * Vergleich von this mit other; Zwei Konten gelten als gleich,
	 * wen sie die gleiche Kontonummer haben
	 * @param other
	 * @return true, wenn beide Konten die gleiche Nummer haben
	 */
	@Override
	public boolean equals(Object other)
	{
		if(this == other)
			return true;
		if(other == null)
			return false;
		if(this.getClass() != other.getClass())
			return false;
		if(this.nummer == ((Konto)other).nummer)
			return true;
		else
			return false;
	}
	
	@Override
	public int hashCode()
	{
		return 31 + (int) (this.nummer ^ (this.nummer >>> 32));
	}

	/*
	public void aufKonsole()
	{
		System.out.println(this.toString());
	}
	*/
	
	/**
	 * Erhöht den Kontostand um den eingezahlten Betrag in der angegebenen Waehrung.
	 *
	 * @param betrag double
	 * @param w die Waehrung des eingezahlten Betrags
	 * @throws IllegalArgumentException wenn der betrag negativ ist 
	 */
	public void einzahlen(double betrag, Waehrung w) {
		if (betrag < 0) {
			throw new IllegalArgumentException("Negativer Betrag");
		}
		double einzahlungsBetragInEuro = betrag * w.getKurs();
		double einzahlungsBetragInKontoWaehrung = einzahlungsBetragInEuro / this.waehrung.getKurs();
		setKontostand(getKontostand() + einzahlungsBetragInKontoWaehrung);
	}
	
	/**
	 * Gibt die Waehrung zurück in der das Konto aktuell geführt wird
	 * @return Objekt der aktuelle Waehrung
	 */
	public Waehrung getAktuelleWaehrung(){
		return this.waehrung;
	}
	
	/**
	 * Wechselt die Währung, in der das Konto aktuell geführt wird.
	 * @param neu Waehrung in der das Konto geführt werden soll
	 */
	public void waehrungswechsel(Waehrung neu){
		double aktuellerKontostandInEuro = getKontostand() / this.waehrung.getKurs();
		double umgerechneterKontostand = aktuellerKontostandInEuro * neu.getKurs();
		setKontostand(umgerechneterKontostand);
		this.waehrung = neu;	
	}
	
	/**
	 * Mit dieser Methode wird der geforderte Betrag vom Konto abgehoben, wenn es nicht gesperrt ist.
	 *
	 * @param betrag double
	 * @throws GesperrtException wenn das Konto gesperrt ist
	 * @throws IllegalArgumentException wenn der betrag negativ ist 
	 * @return true, wenn die Abhebung geklappt hat, 
	 * 		   false, wenn sie abgelehnt wurde
	 */
	public abstract boolean abheben(double betrag) throws GesperrtException;
	
}


