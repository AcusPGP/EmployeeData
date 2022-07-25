package employee;

public abstract class Person implements IGeneral {
    private String name;
    private int age;
    private String address;

    public Person(String name, int age, String address) {
        super();
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @Override
    public void setName(String Name) {
        this.name = Name;
    }

    @Override
    public String getName() {
        return this.name;
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
    public void setAddress(String Address) {
        this.address = Address;
    }

    @Override
    public String getAddress() {
        return this.address;
    }
}

