package com.student.lms.lms.Controllers;

import com.student.lms.lms.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController
{
    @Autowired
    private TransactionService transactionService;

    @PutMapping("/issueBook")
    public ResponseEntity issueBook(@RequestParam("cardId")Integer cardId,
                                    @RequestParam("bookId")Integer bookId)
    {
        try
        {
           String res=transactionService.issueBook(bookId,cardId);
           return new ResponseEntity(res, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);

        }
    }

    @PutMapping("/returnBook")
    public String returnBook(@RequestParam("cardId")Integer cardId,
                             @RequestParam("bookId")Integer bookId)
    {
        String res=transactionService.returnBook(cardId,bookId);
        return res;
    }
}
