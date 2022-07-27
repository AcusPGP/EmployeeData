package employee.pojo;

public class Person implements IGeneral {
    private String name;
    private int id;
    private int age;
    private String address;

    private String type;

    public Person() {
    }

    public Person(int id, String name, int age, String address, String type) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.id = id;
        this.type = type;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String toString() {
        return "Employee [Id: " + id + ", Name: " + name + ", Age: " + age + ", Address: " + address +", Type: " + type;
    }
}

