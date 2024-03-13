package com.student.lms.lms.Controllers;

import com.student.lms.lms.Entities.Author;
import com.student.lms.lms.Entities.Book;
import com.student.lms.lms.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController
{
    @Autowired
    private AuthorService authorService;
    @PostMapping("/add")
    public String addAuthor(@RequestBody Author author)
    {
        String res=authorService.addAuthor(author);
        return res;
    }


    @GetMapping("/maxBookAuthor")
    public Author getMaxBookAuthor()
    {
        return authorService.getAuthorWithMaxBooks();
    }


}
