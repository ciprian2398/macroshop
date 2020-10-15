package com.technetwork.macroshop.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "creation_time", nullable = false)
    private LocalDateTime creationTime;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "stock", nullable = false)
    private Long stock;

    @ManyToOne
    @JoinColumn(name = "owner")
    private User owner;
}
