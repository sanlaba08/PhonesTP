package com.utn.TPFinal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Column(name = "id_line_type")
    private String idLineType;

    @Column(name = "id_user")
    private String idUser;



}
