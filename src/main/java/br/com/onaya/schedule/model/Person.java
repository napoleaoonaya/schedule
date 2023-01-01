package br.com.onaya.schedule.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 255)
    private String name;
    @Column(name = "middleName", length = 255)
    private String middleName;
    @Column(name = "lastName", length = 255)
    private String lastName;
    @Column(name = "address", length = 255)
    private String address;
    @Column(name = "email", length = 255)
    private String email;
    @Column(name = "cell", length = 255)
    private String cell;
    @Column(name = "telephone", length = 255)
    private String telephone;
}
