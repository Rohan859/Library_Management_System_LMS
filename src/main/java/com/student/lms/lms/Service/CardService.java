package com.student.lms.lms.Service;

import com.student.lms.lms.Enums.CardStatus;
import com.student.lms.lms.Entities.LibraryCard;
import com.student.lms.lms.Entities.Student;
import com.student.lms.lms.Repository.CardRepository;
import com.student.lms.lms.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class CardService
{
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private StudentRepository studentRepository;

    public String generateCard()
    {
        LibraryCard card=new LibraryCard();

        //set
        card.setCardStatus(CardStatus.NEW);
        card.setNoOfBookIssued(0);

        LocalDate expiry=LocalDate.of(2027,11,13);

        card.setValidity(expiry);

        card=cardRepository.save(card);

        return "Generated new Library Card Successfully with card id " +card.getCardNo();
    }

    public String associateCardAndStudent(int cardNo,int rollNo)
    {
        LibraryCard card=cardRepository.findById(cardNo).get();
        Student student=studentRepository.findById(rollNo).get();

        card.setCardStatus(CardStatus.ISSUED);
        card.setNoOfBookIssued(card.getNoOfBookIssued()+1);
        card.setStudent(student); //adding the foreign Key

        card = cardRepository.save(card); //saving into the database of the existing card

        return "card no "+card.getCardNo()+" is successfully associated with student with roll No "+student.getRollNo();
    }
}
