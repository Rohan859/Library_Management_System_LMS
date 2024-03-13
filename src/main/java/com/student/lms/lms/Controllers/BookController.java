package com.student.lms.lms.Controllers;

import com.student.lms.lms.Entities.Book;
import com.student.lms.lms.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController
{
    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody Book book)
    {
        try
        {
            String res=bookService.addBook(book);
            return new ResponseEntity(res, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }


    }

    @PutMapping("/associateBookAndAuthor")
    public ResponseEntity associateBookAndAuthor(@RequestParam("bookId")Integer bookId,
                                         @RequestParam("authorId")Integer authorId)
    {
       try
       {
           String res = bookService.associateBookAndAuthor(bookId,authorId);
           return new ResponseEntity(res,HttpStatus.OK);
       }
       catch (Exception e)
       {
           return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
       }

    }

    @GetMapping("/getListOfBooksByAuthor")
    public List<Book> getBookListByAuthorId(@RequestParam("authorId") Integer authorId)
    {
        List<Book>bookList= bookService.getBookListByAuthorId(authorId);
        return bookList;
    }
}
