package com.ademiju.demoThymeLeaf.datas.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Validated
@Entity
@Setter
@Getter
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id",nullable = false)
    @SequenceGenerator(name = "user_id_sequence", sequenceName = "user_id_sequence")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE, generator = "user_id_sequence")
    private Long id;

    @NotBlank(message = "name property cannot be blank")
    private String name;

    @Email
    @NotBlank(message = "name property cannot be blank")
    private  String email;

}
