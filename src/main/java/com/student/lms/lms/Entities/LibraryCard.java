package com.student.lms.lms.Entities;

import com.student.lms.lms.Enums.CardStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Card-Info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class LibraryCard
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardNo;

//    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;

    private int noOfBookIssued;

    private LocalDate validity;

    @JoinColumn //it tells I am adding new column in LibraryCard table as a foreign key
    @OneToOne
    private Student student;

}