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
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_city")
    private Integer idCity;

    @Column(name = "name")
    private String cityName;

    private String prefix;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_province")
    @JsonBackReference
    private Province province;

    @OneToMany(mappedBy = "city")
    List<User> users = new ArrayList<User>();

    @OneToMany(mappedBy = "cityOrigin")
    List<Call> cityOrigin = new ArrayList<Call>();

    @OneToMany(mappedBy = "cityDestination")
    List<Call> cityDestination = new ArrayList<Call>();

    @OneToMany(mappedBy = "cityTariffOrigin")
    List<Tariff> cityTariffOrigin = new ArrayList<Tariff>();

    @OneToMany(mappedBy = "cityTariffDestination")
    List<Tariff> cityTariffDestination = new ArrayList<Tariff>();

}
