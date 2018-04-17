package testBots.dao.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "department")
public class DepartmentEntity {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "salary")
    private int salary;

    @Column(name = "department")
    private String department;

    @Column(name = "head_of_department")
    private String head_of_department;

    @ManyToOne
    @JoinColumn(name = "lector_id", referencedColumnName = "id", insertable= false, updatable=false)
    private LectorsEntity lector;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getHead_of_department() {
        return head_of_department;
    }

    public void setHead_of_department(String head_of_department) {
        this.head_of_department = head_of_department;
    }

    public LectorsEntity getLector() {
        return lector;
    }

    public void setLector(LectorsEntity lector) {
        this.lector = lector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentEntity that = (DepartmentEntity) o;
        return id == that.id &&
                salary == that.salary &&
                Objects.equals(name, that.name) &&
                Objects.equals(secondName, that.secondName) &&
                Objects.equals(department, that.department) &&
                Objects.equals(lector, that.lector);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, secondName, salary, department, lector);
    }

    @Override
    public String toString() {
        return "Department: " +
                "id = " + id +
                ", name = " + name +
                ", secondName = " + secondName +
                ", salary = " + salary +
                ", department = " + department +
                ", lector = " + lector;
    }
}
