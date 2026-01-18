package com.userservice.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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



}
