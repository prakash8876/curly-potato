package io.matoshri.gson.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_tbl")
public class User {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Getter
    @Setter
    @Column(name = "name", length = 20)
    private String name;
    @Getter
    @Setter
    @Email(message = "Enter proper email address")
    @Column(name = "email", length = 100)
    private String email;
    @Getter
    @Setter
    @Min(20)
    @Max(60)
    @Column(name = "age")
    private int age;
    @Getter
    @Setter
    @Column(name = "username", length = 20)
    private String username;
    @Getter
    @Setter
    @Column(name = "password", length = 20)
    private String password;
    @CreationTimestamp
    @Column(name = "created_on")
    private LocalDateTime createdOn;
    @UpdateTimestamp
    @Column(name = "updated_on", updatable = false)
    private LocalDateTime updatedOn;
}
