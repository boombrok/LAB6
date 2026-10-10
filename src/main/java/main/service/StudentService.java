package service;

import domain.Student;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class StudentService implements service.FileIOInterface {

    public List<Student> filterByFaculty(List<Student> students, String faculty) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getFaculty().equalsIgnoreCase(faculty.trim())) {
                result.add(s);
            }
        }
        return result;
    }

    public List<Student> filterByBirthYear(List<Student> students, int year) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getBirthDate().getYear() > year) {
                result.add(s);
            }
        }
        return result;
    }

    public List<Student> filterByGroup(List<Student> students, String group) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getGroup().equalsIgnoreCase(group.trim())) {
                result.add(s);
            }
        }
        return result;
    }

    public List<Student> filterByLegalEntityFunding(List<Student> students) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getFundingSource().equalsIgnoreCase("юр.особа")) {
                result.add(s);
            }
        }
        return result;
    }

    public List<Student> sortBirthDateDescNameAsc(List<Student> students) {
        List<Student> sorted = new ArrayList<>(students);
        sorted.sort(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                int cmp = s2.getBirthDate().compareTo(s1.getBirthDate());
                if (cmp != 0) return cmp;
                return s1.getFullName().compareTo(s2.getFullName());
            }
        });
        return sorted;
    }

    public Student findById(List<Student> students, int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    public Map<String, List<Student>> groupStudentsByFacultySorted(List<Student> students) {
        Map<String, List<Student>> map = new TreeMap<>();
        for (Student s : students) {
            String faculty = s.getFaculty();
            if (!map.containsKey(faculty)) {
                map.put(faculty, new ArrayList<>());
            }
            map.get(faculty).add(s);
        }
        for (List<Student> list : map.values()) {
            list.sort(new Comparator<Student>() {
                @Override
                public int compare(Student s1, Student s2) {
                    return s1.getFullName().compareTo(s2.getFullName());
                }
            });
        }
        return map;
    }

    public Map<Integer, Long> countStudentsByBirthYear(List<Student> students) {
        Map<Integer, Long> map = new TreeMap<>();
        for (Student s : students) {
            int year = s.getBirthDate().getYear();
            map.put(year, map.getOrDefault(year, 0L) + 1);
        }
        return map;
    }

    @Override
    public void saveToText(List<Student> list, String filename) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            for (Student s : list) {
                out.println(s.getId() + ";" + s.getFullName() + ";" + s.getBirthDate() + ";" +
                        s.getFaculty() + ";" + s.getGroup() + ";" + s.getRating() + ";" + s.getFundingSource());
            }
        }
    }

    @Override
    public List<Student> loadFromText(String filename) throws IOException {
        List<Student> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(";");
                list.add(new Student(Integer.parseInt(p[0]), p[1], LocalDate.parse(p[2]), p[3], p[4], Double.parseDouble(p[5]), p[6]));
            }
        }
        return list;
    }

    @Override
    public void saveToBinary(List<Student> list, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(list);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> loadFromBinary(String filename) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Student>) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        }
    }
}
