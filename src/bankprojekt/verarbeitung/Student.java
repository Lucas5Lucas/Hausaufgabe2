package bankprojekt.verarbeitung;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * <p>
 * Kunde einer Bank der als Student registriert wird
 * </p>
 * 
 */
public class Student extends Kunde{

	/**
	 * Das Studienfach
	 */
	private String studienfach;
	/**
	 * Die Universit�t
	 */
	private String universitaet;
	/**
	 * Vorraussichtliches Studienende
	 */
	private LocalDate vorrStudienende;
	/**
	 * Letztes Semester dass durch Studienbescheinigung best�tigt wurde
	 */
	private int best�tigtesSemester;
	/**
	 * Liegt aktuelle Studienbescheinigung vor oder nicht
	 */
	private boolean bescheinigungLiegtVor;
	
	
	/**
	 * Erzeugt einen Student mit den �bergebenen Werten
	 * 
	 * @param vorname
	 * @param nachname
	 * @param adresse
	 * @param gebdat
	 * @param studienfach
	 * @param universitaet
	 * @param vorrStudienende
	 * @throws IllegalArgumentException wenn einer der Parameter null ist
	 */
	public Student(String vorname, String nachname, String adresse, LocalDate gebdat, String studienfach, String universitaet, LocalDate vorrStudienende, int best�tigtesSemester){
		super(vorname, nachname, adresse, gebdat);
		if(studienfach == null || universitaet == null || vorrStudienende == null || best�tigtesSemester == 0)
			throw new IllegalArgumentException("null als Parameter nich erlaubt");
		this.studienfach = studienfach;
		this.universitaet = universitaet;
		this.vorrStudienende = vorrStudienende;	
		this.best�tigtesSemester = best�tigtesSemester;
		this.bescheinigungLiegtVor = true;
	}

	/**
	 * Vorl�ufiges Studienende
	 * @return
	 */
	public LocalDate getVorrStudienende() {
		return vorrStudienende;
	}

	/**
	 * Setzt Studienende auf gegebenes Datum
	 * @param vorrStudienende
	 */
	public void setVorrStudienende(LocalDate vorrStudienende) {
		this.vorrStudienende = vorrStudienende;
	}

	/**
	 * Letztes best�tigtes Semester
	 */
	public int getBest�tigtesSemester() {
		return best�tigtesSemester;
	}

	/**
	 * Setzt letztesbes�tigtes Semester auf gegebenen Wert
	 * @param best�tigtesSemester
	 */
	public void setBest�tigtesSemester(int best�tigtesSemester) {
		this.best�tigtesSemester = best�tigtesSemester;
	}

	/**
	 * Besuchte Universit�t
	 * @return
	 */
	public String getUniversitaet() {
		return universitaet;
	}

	/**
	 * Setzt Universit�t auf gegebenen Wert
	 */
	public void setUniversitaet(String universitaet) {
		this.universitaet = universitaet;
	}
	
	/**
	 * Liegt Bescheinigung f�r aktuelles Semester vor
	 * @return true falls ja, false falls nein
	 */
	public boolean getBescheinigungLiegtVor() {
		return this.bescheinigungLiegtVor;
	}

	/**
	 * Setzt bescheinigungLiegt vor auf true oder false
	 * @param bescheinigungLiegtVor
	 */
	public void setBescheinigungLiegtVor(boolean bescheinigungLiegtVor) {
		this.bescheinigungLiegtVor = bescheinigungLiegtVor;
	}
	
	/**
	 * Gibt das Studienfach zur�ck
	 * @return Das Studienfach
	 */
	public String getStudienfach() {
		return this.studienfach;
	}

	/**
	 * Setzt das Studienfach
	 * @param studienfach
	 */
	public void setStudienfach(String studienfach) {
		this.studienfach = studienfach;
	}
	
	/**
	 * gibt alle Daten des Studenten aus
	 */
	@Override
	public String toString() {
		String ausgabe = super.toString();
		DateTimeFormatter df = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
		ausgabe += this.studienfach + ", " + this.universitaet + ", Studienende: " + df.format(this.vorrStudienende) + ", Best�tigtes Semester: " + this.best�tigtesSemester;
		if(this.bescheinigungLiegtVor == true){
			ausgabe += System.getProperty("line.separator") + "Aktuelle Bescheinigung liegt vor";
		} else {
			ausgabe += System.getProperty("line.separator") + "Aktuelle Bescheinigung liegt NICHT vor";
		}
		return ausgabe;
	}
}
