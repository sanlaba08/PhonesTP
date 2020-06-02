package com.utn.TPFinal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "calls")
public class Call {
    @Id
    @GeneratedValue()
    @Column(name = "id_call")
    private Integer idCall;

    @Column(name = "call_date")
    private Date callDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_line_origin")
    @JsonBackReference
   private PhoneLine phoneLineOrigin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_line_destination")
    @JsonBackReference
   private PhoneLine phoneLineDestination;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "total_cost")
    private Integer totalCost;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city_origin")
    @JsonBackReference
   private City cityOrigin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city_destination")
    @JsonBackReference
   private City cityDestination;

    @Column(name = "billed")
    private Boolean billed;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_bill")
    @JsonBackReference
    private Bill bill;




}