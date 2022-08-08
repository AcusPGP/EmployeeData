package employee.service.worker;

import employee.pojo.Worker;


public interface WorkerService<T extends Worker> {

    void showLevel(T worker);
}
