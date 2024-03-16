package com.fit.se.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee implements Serializable {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;

}
