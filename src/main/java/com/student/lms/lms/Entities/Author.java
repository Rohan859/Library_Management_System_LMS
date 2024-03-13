package com.student.lms.lms.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Author
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId;
    private Integer age;
    private String name;
    private double rating;
    private Integer noOfBooks;
    private String email;

//    @OneToMany
//    private List<Book>bookList;
}
