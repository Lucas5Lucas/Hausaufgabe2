import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import bankprojekt.verarbeitung.Student;

public class StudentTest {

	@Test
	public void testStudent() {
		Student student = new Student("Lucas", "Gertsch", "Zuhause", LocalDate.parse("1988-09-20"), "Angewandte Informatik", 
				"HTW Berlin", LocalDate.parse("2018-01-01"), 5);
		
		assertEquals(student.getName(), "Gertsch, Lucas");
		assertEquals(student.getAdresse(), "Zuhause");
		assertEquals(student.getBescheinigungLiegtVor(), true);
		assertEquals(student.getBestätigtesSemester(), 5);
		assertEquals(student.getGeburtstag(), LocalDate.parse("1988-09-20"));
		assertEquals(student.getUniversitaet(), "HTW Berlin");
		assertEquals(student.getVorrStudienende(), LocalDate.parse("2018-01-01"));
		assertEquals(student.getStudienfach(), "Angewandte Informatik");
	}

}
