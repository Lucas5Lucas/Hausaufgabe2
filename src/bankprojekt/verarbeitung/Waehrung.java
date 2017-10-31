package bankprojekt.verarbeitung;

public enum Waehrung {
	EUR(1.0),
	BGN(1.95583), 
	LTL(3.4528), 
	KM(1.95583);
	
	/*
	 * Euro-Umrechnungskurs
	 */
	private final double kurs;
	
	Waehrung(double umrechnungskurs){
		this.kurs = umrechnungskurs;
	}
	
	/* 
	 * Gibt den eingegebenen Euro Betrag in die jeweilige Währung umgerechnet als double zurück
	 * 
	 * @param betrag der in Euro umzurechnende Betrag
	 * @return betrag in jeweiliger waehrung
	 */
	public double umrechnen(double betrag){
		return betrag * this.kurs;
	}
	
	public final double getKurs(){
		return this.kurs;
	}
	

}
