import java.io.Serializable;

public class Employe implements Serializable {
           private long id;
            private  String name;
            private String phone;
            private long salary;
            private long age;
            private String departement;

    public Employe() {

    }
    public Employe(long id, String name, String phone, long salary, int age, String departement) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
        this.departement = departement;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }
}
