package com.student.lms.lms.Controllers;

import com.student.lms.lms.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController
{
    @Autowired
    private CardService cardService;

    @PostMapping("/generateCard")
    public ResponseEntity addCard()
    {
        String res = cardService.generateCard();
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PutMapping("/associateCardAndStudent")
    public ResponseEntity associateCardAndStudent(@RequestParam("CardNo") int cardNo,
                                                  @RequestParam("rollNo") int rollNo)
    {
        String res=cardService.associateCardAndStudent(cardNo,rollNo);
        return new ResponseEntity(res,HttpStatus.OK);
    }
}
