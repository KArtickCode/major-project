package com.example.major_project.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@TypeDef(
//        typeClass = JsonType.class,
//        defaultForType = Phone.class
//)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String  name;

    private Integer age;

    @Column(unique = true)  // email should be unique
    private String email;

    @Column(unique = true, nullable = false)  //phone number should be unique and not null
    private String phone;   // phone: +9193743389 , another way to store the mobile no{countryCode: +91  number: 1234567890 }

//    @Column(columnDefinition = "jsonb")
//    private Phone contact;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;


    public User toUser(){
        User user = new User();
        user.setName(this.getName());
        user.setAge(this.getAge());
        user.setEmail(this.getEmail());
        user.setPhone(this.getPhone());

        return user;
    }

}
