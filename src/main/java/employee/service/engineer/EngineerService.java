package employee.service.engineer;


import employee.pojo.Engineer;

public interface EngineerService<T extends Engineer>{

    void showDegree(T engineer);
}
