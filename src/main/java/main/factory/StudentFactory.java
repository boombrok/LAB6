package factory;

import domain.Student;
import java.time.LocalDate;

public class StudentFactory {
    public static Student createStudent(int id, String name, LocalDate date, String faculty, String group, double rating, String funding) {
        return new Student(id, name, date, faculty, group, rating, funding);
    }
}
