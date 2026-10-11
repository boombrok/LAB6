package view;

import domain.Student;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StudentView {
    private Scanner sc = new Scanner(System.in);

    public int showMenu() {
        System.out.println("1. Переглянути всіх студентів");
        System.out.println("2. Додати нового студента");
        System.out.println("3. Вилучити студента за ID");
        System.out.println("4. Запит 1: Студенти заданого факультету");
        System.out.println("5. Запит 2: Студенти, які народились після заданого року");
        System.out.println("6. Запит 3: Список навчальної групи");
        System.out.println("7. Запит 4: Студенти, які навчаються за кошти юр.особи");
        System.out.println("8. Запит 5: Сортування (Дата спад. + ПІБ зрост.)");
        System.out.println("9. Перевірка за ID: Вивід місяця та дня народження");
        System.out.println("10. Сформувати Map 1: Список студентів за факультетами");
        System.out.println("11. Сформувати Map 2: Кількість студентів за роками");
        System.out.println("12. Зберегти у файли (TXT / BIN)");
        System.out.println("13. Завантажити з файлів (TXT / BIN)");
        System.out.println("0. Вихід");
        System.out.print("Вибір: ");
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void displayStudents(String title, List<Student> students) {
        System.out.println("\n=== " + title + " ===");
        if (students.isEmpty()) {
            System.out.println("Результатів немає.");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    public void displayFacultyGrouped(Map<String, List<Student>> map) {
        System.out.println("\n=== Розподіл студентів за факультетами (Map) ===");
        map.forEach((faculty, list) -> {
            System.out.println("Факультет: " + faculty);
            list.forEach(s -> System.out.println("  " + s));
        });
    }

    public void displayYearCounts(Map<Integer, Long> map) {
        System.out.println("\n=== Кількість студентів за роками народження (Map) ===");
        map.forEach((year, count) -> System.out.println("Рік: " + year + " -> Кількість: " + count));
    }

    public String inputString(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }

    public int inputInt(String msg) {
        System.out.print(msg);
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public double inputDouble(String msg) {
        System.out.print(msg);
        try {
            return Double.parseDouble(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
