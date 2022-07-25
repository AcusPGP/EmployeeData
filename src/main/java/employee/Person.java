package employee;

public abstract class Person implements IGeneral {
    private String name;
    private int id;
    private int age;
    private String address;

    public Person () {}
    public Person(String name, int age, String address) {
        super();
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getAddress() {
        return this.address;
    }
}

