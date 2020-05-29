package com.utn.TPFinal.model;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

public class Call {
    @Id
    @GeneratedValue()
    @Column(name = "id_call")
   private Integer idCall;

   /* private Date callDate;

    @Column(name = "id_line_origin")
   private PhoneLine phoneLineOrigin;

    @Column(name = "id_line_destination")
   private PhoneLine phoneLineDestination;

    private Long duration;

    private Integer total_price;

    @Column(name = "id_call")
   private City cityOrigin;

    @Column(name = "id_call")
   private City cityDestination;


    private Integer total_cost;



    private Boolean billed;

    private Bill bill;*/




}
