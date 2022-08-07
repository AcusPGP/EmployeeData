package employee.service;

import employee.pojo.Person;

public interface BaseService<T extends Person> {

    void add();

    void delete(T object);

    void edit() ;

}
