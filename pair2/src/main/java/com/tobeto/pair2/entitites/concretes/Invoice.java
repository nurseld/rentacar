package com.tobeto.pair2.entitites.concretes;

import com.tobeto.pair2.entitites.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "invoices")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice extends BaseEntity {

    @Column(name="invoice_no")
    private String invoiceNo;

    @Column(name="total_price")
    private Float totalPrice;

//    @Column(name="tax_rate")
//    private Float taxRate;

    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;
}
