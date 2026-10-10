package service;

import domain.Student;
import java.io.IOException;
import java.util.List;

public interface FileIOInterface {
    void saveToText(List<Student> list, String filename) throws IOException;
    List<Student> loadFromText(String filename) throws IOException;
    void saveToBinary(List<Student> list, String filename) throws IOException;
    List<Student> loadFromBinary(String filename) throws IOException;
}
