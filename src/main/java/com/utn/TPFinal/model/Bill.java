package com.utn.TPFinal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "bills")
public class Bill {
    @Id
    @GeneratedValue()
    @Column(name = "id_bill")
    private Integer idBill;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_line")
    @JsonBackReference
    private PhoneLine line;

    @Column(name = "calls_quantity")
    private Integer callsQuantity;

    @Column(name = "total_cost")
    private Long totalCost;

    @Column(name = "total_price")
    private Long totalPrice;

    @Column(name = "bill_date")
    private Date billDate;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "paid")
    private boolean paid;

    @OneToMany(mappedBy = "bill")
    List<Call> calls = new ArrayList<Call>();
}
