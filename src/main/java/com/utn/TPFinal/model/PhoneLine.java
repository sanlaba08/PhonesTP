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
@Table(name = "phone_lines")
public class PhoneLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_line")
    private Integer idLine;

    @Column(name = "number_line")
    private String numberLine;

    @Column(name = "available")
    private String available;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_line_type")
    @JsonBackReference
    private LineType lineType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "phoneLineOrigin")
    List<Call> callsOrigin = new ArrayList<Call>();

    @OneToMany(mappedBy = "phoneLineDestination")
    List<Call> callsDestination = new ArrayList<Call>();

    @OneToMany(mappedBy = "line")
    List<Bill> billLine = new ArrayList<Bill>();




}
