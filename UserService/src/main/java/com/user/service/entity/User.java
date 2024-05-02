package com.user.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="micro_users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long userId;

    @Column(name="NAME", nullable=false)
    private String name;

    @Column(name = "EMAIL", nullable=false, unique=true)
    private String email;

    @Column(name = "ABOUT", nullable=false)
    private String about;

    @Transient
    private List<Rating> ratings=new ArrayList<>();
}
