package testBots.dao.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "lectors")
public class LectorsEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "degree")
    private String degree;

    @OneToMany(mappedBy = "lector", cascade = CascadeType.ALL)
    private Set<DepartmentEntity> departments = new HashSet<>();

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

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Set<DepartmentEntity> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<DepartmentEntity> departments) {
        this.departments = departments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LectorsEntity that = (LectorsEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(secondName, that.secondName) &&
                Objects.equals(degree, that.degree);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, secondName, degree);
    }

    @Override
    public String toString() {
        return "Lectors: " +
                "id = " + id +
                ", name = " + name +
                ", secondName = " + secondName +
                ", degree = " + degree;
    }
}
