package com.utn.TPFinal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonBackReference
    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "dni")
    private String dni;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "available")
    private Integer available;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city")
    @JsonBackReference
    private City city;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user_type")
    @JsonBackReference
    private UserType userType;

    @OneToMany(mappedBy = "user")
    List<PhoneLine> phoneLines = new ArrayList<PhoneLine>();

}
