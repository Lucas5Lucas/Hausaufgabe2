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
	 * Die Universität
	 */
	private String universitaet;
	/**
	 * Vorraussichtliches Studienende
	 */
	private LocalDate vorrStudienende;
	/**
	 * Letztes Semester dass durch Studienbescheinigung bestätigt wurde
	 */
	private int bestätigtesSemester;
	/**
	 * Liegt aktuelle Studienbescheinigung vor oder nicht
	 */
	private boolean bescheinigungLiegtVor;
	
	
	/**
	 * Erzeugt einen Student mit den übergebenen Werten
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
	public Student(String vorname, String nachname, String adresse, LocalDate gebdat, String studienfach, String universitaet, LocalDate vorrStudienende, int bestätigtesSemester){
		super(vorname, nachname, adresse, gebdat);
		if(studienfach == null || universitaet == null || vorrStudienende == null || bestätigtesSemester == 0)
			throw new IllegalArgumentException("null als Parameter nich erlaubt");
		this.studienfach = studienfach;
		this.universitaet = universitaet;
		this.vorrStudienende = vorrStudienende;	
		this.bestätigtesSemester = bestätigtesSemester;
		this.bescheinigungLiegtVor = true;
	}

	/**
	 * Vorläufiges Studienende
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
	 * Letztes bestätigtes Semester
	 */
	public int getBestätigtesSemester() {
		return bestätigtesSemester;
	}

	/**
	 * Setzt letztesbesätigtes Semester auf gegebenen Wert
	 * @param bestätigtesSemester
	 */
	public void setBestätigtesSemester(int bestätigtesSemester) {
		this.bestätigtesSemester = bestätigtesSemester;
	}

	/**
	 * Besuchte Universität
	 * @return
	 */
	public String getUniversitaet() {
		return universitaet;
	}

	/**
	 * Setzt Universität auf gegebenen Wert
	 */
	public void setUniversitaet(String universitaet) {
		this.universitaet = universitaet;
	}
	
	/**
	 * Liegt Bescheinigung für aktuelles Semester vor
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
	 * Gibt das Studienfach zurück
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
		ausgabe += this.studienfach + ", " + this.universitaet + ", Studienende: " + df.format(this.vorrStudienende) + ", Bestätigtes Semester: " + this.bestätigtesSemester;
		if(this.bescheinigungLiegtVor == true){
			ausgabe += System.getProperty("line.separator") + "Aktuelle Bescheinigung liegt vor";
		} else {
			ausgabe += System.getProperty("line.separator") + "Aktuelle Bescheinigung liegt NICHT vor";
		}
		return ausgabe;
	}
}
