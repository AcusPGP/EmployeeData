package employee.service;

import employee.pojo.Engineer;
import employee.pojo.Person;

public interface BaseService<T extends Person> {

    void add();
    void delete(T object);

}
