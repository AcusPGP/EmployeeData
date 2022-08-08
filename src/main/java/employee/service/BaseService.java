package employee.service;

import employee.pojo.Person;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface BaseService<T extends Person> {

    void add();

    void delete() throws IOException;

    void edit() throws IOException;

}
