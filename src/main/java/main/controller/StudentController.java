package controller;

import domain.Student;
import factory.StudentFactory;
import service.StudentService;
import view.StudentView;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class StudentController {
    private StudentService service = new StudentService();
    private StudentView view = new StudentView();
    private List<Student> students = new ArrayList<>();

    public StudentController() {
        students.add(StudentFactory.createStudent(1, "Іванов І.І.", LocalDate.of(2005, 5, 12), "КН", "КН-31", 95.2, "бюджет"));
        students.add(StudentFactory.createStudent(2, "Петров П.П.", LocalDate.of(2006, 2, 28), "ФЕУ", "Е-22", 84.0, "фіз.особа"));
        students.add(StudentFactory.createStudent(3, "Сидоров С.С.", LocalDate.of(2005, 5, 12), "КН", "КН-31", 72.5, "юр.особа"));
        students.add(StudentFactory.createStudent(4, "Коваленко О.М.", LocalDate.of(2004, 11, 20), "КН", "КН-32", 88.0, "юр.особа"));
    }

    public void execute() {
        while (true) {
            int c = view.showMenu();
            if (c == 0) break;
            try {
                handleCommand(c);
            } catch (IOException e) {
                System.out.println("Помилка файлу: " + e.getMessage());
            }
        }
    }

    private void handleCommand(int c) throws IOException {
        switch (c) {
            case 1 -> view.displayStudents("Всі студенти", students);
            case 2 -> addNewStudent();
            case 3 -> removeStudentById();
            case 4 -> view.displayStudents("Результат", service.filterByFaculty(students, view.inputString("Введіть факультет: ")));
            case 5 -> view.displayStudents("Результат", service.filterByBirthYear(students, view.inputInt("Введіть рік: ")));
            case 6 -> view.displayStudents("Результат", service.filterByGroup(students, view.inputString("Введіть групу: ")));
            case 7 -> view.displayStudents("Студенти за кошти юр.осіб", service.filterByLegalEntityFunding(students));
            case 8 -> view.displayStudents("Відсортовано", service.sortBirthDateDescNameAsc(students));
            case 9 -> checkStudentBirthday();
            case 10 -> view.displayFacultyGrouped(service.groupStudentsByFacultySorted(students));
            case 11 -> view.displayYearCounts(service.countStudentsByBirthYear(students));
            case 12 -> {
                service.saveToText(students, "data.txt");
                service.saveToBinary(students, "data.bin");
                System.out.println("Дані збережено у data.txt та data.bin");
            }
            case 13 -> {
                students = service.loadFromBinary("data.bin");
                System.out.println("Дані успішно завантажено з data.bin");
            }
            default -> System.out.println("Некоректний вибір.");
        }
    }

    private void addNewStudent() {
        int id = view.inputInt("Введіть ID: ");
        String name = view.inputString("Введіть ПІБ: ");
        LocalDate date;
        try {
            date = LocalDate.parse(view.inputString("Введіть дату народження (РРРР-ММ-ДД): "));
        } catch (DateTimeParseException e) {
            System.out.println("Некоректний формат дати. Встановлено поточну дату.");
            date = LocalDate.now();
        }
        String faculty = view.inputString("Введіть факультет: ");
        String group = view.inputString("Введіть групу: ");
        double rating = view.inputDouble("Введіть рейтинг: ");
        String funding = view.inputString("Введіть кошт (бюджет/фіз.особа/юр.особа): ");
        students.add(StudentFactory.createStudent(id, name, date, faculty, group, rating, funding));
        System.out.println("Студента успішно додано до колекції.");
    }

    private void removeStudentById() {
        int id = view.inputInt("Введіть ID студента для вилучення: ");
        boolean removed = students.removeIf(s -> s.getId() == id);
        if (removed) {
            System.out.println("Студента з ID " + id + " успішно видалено.");
        } else {
            System.out.println("Студента з таким ID не знайдено.");
        }
    }

    private void checkStudentBirthday() {
        int id = view.inputInt("Введіть ID для перевірки: ");
        Student s = service.findById(students, id);
        if (s != null) {
            System.out.println("Чи є студент із указаним id у списку: так.");
            System.out.println("Місяць народження: " + s.getBirthDate().getMonthValue() + ", день: " + s.getBirthDate().getDayOfMonth());
        } else {
            System.out.println("Студента з таким ID не знайдено.");
        }
    }
}
