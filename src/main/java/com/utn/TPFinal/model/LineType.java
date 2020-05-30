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
@Table(name = "Line_types")
public class LineType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonBackReference
    @Column(name = "id_line_type")
    private Integer idTypeLine;
    @Column(name = "line_type")
    private String LineTypeName;

    @OneToMany(mappedBy = "lineType")
    List<PhoneLine> lineType = new ArrayList<PhoneLine>();

}
