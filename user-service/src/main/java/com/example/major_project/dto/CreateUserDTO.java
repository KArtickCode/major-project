package com.example.major_project.dto;


import com.example.major_project.models.Phone;
import com.example.major_project.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDTO {

    private String name;

    private Integer age;

    private String email;

    private Phone phone;



    public User toUser(){
        User user = new User();
        user.setAge(this.getAge());
//        user.setContact(this.getPhone());
        user.setEmail(this.getEmail());
        user.setName(this.getName());

        // TODO: Handle null pointer exceptions elegantly
        user.setPhone(this.getPhone().getCountryCode() + this.getPhone().getNumber());

        return user;
    }

}
