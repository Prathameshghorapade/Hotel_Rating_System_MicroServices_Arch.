package com.userservice.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "micro_users")
public class User {

    @Id
    @Column(name = "User_Id")
    private String id;

    @Column(name = "User_Name",length = 20)
    private String name;

    @Column(name = "Email")
    private String email;

    @Column(name = "About",length = 50)
    private String about;

    @Transient
    private List<Rating>ratings=new ArrayList<>();



}
