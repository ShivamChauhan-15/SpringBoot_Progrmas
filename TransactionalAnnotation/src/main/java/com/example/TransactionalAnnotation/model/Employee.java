package com.example.TransactionalAnnotation.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQuery(
        name = "Employee.findAll",
        query = "FROM Employee"
)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int age;
    private String name;
    @OneToOne(mappedBy = "employee", cascade = CascadeType.PERSIST)
    private Address address;

    @Override
    public String toString() {
        return "{"+"id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
