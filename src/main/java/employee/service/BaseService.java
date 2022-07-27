package employee.service;

import employee.pojo.Person;

public interface BaseService<T extends Person> {

    void add(T object);

    void delete(T object);

}
