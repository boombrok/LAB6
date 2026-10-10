package domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Student implements Serializable {
    private int id;
    private String fullName;
    private LocalDate birthDate;
    private String faculty;
    private String group;
    private double rating;
    private String fundingSource;

    public Student(int id, String fullName, LocalDate birthDate, String faculty, String group, double rating, String fundingSource) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.faculty = faculty;
        this.group = group;
        this.rating = rating;
        this.fundingSource = fundingSource;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getFaculty() { return faculty; }
    public void setFaculty(String faculty) { this.faculty = faculty; }

    public String getGroup() { return group; }
    public void setGroup(String group) { this.group = group; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public String getFundingSource() { return fundingSource; }
    public void setFundingSource(String fundingSource) { this.fundingSource = fundingSource; }

    @Override
    public String toString() {
        return String.format("ID: %d | %-20s | Дата: %s | Фак.: %-3s | Гр.: %-6s | Рейт.: %.1f | Кошт: %s",
                id, fullName, birthDate, faculty, group, rating, fundingSource);
    }
}
