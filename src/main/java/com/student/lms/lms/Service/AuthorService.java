package com.student.lms.lms.Service;

import com.student.lms.lms.Entities.Author;
import com.student.lms.lms.Entities.Book;
import com.student.lms.lms.Repository.AuthorRepository;
import com.student.lms.lms.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService
{
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    public String addAuthor(Author author)
    {
        authorRepository.save(author);
        return "Author is successfully add into the database with id "+author.getAuthorId();
    }

//    public List<Book> getBookListByAuthorId(Integer authorId)
//    {
//        Author author=authorRepository.findById(authorId).get();
//        return author.getBookList();
//    }

    public Author getAuthorWithMaxBooks()
    {
        List<Author>authors=authorRepository.findAll();
        int maxNumberOfBooks=0;
        Author author=null;

        for(Author author1:authors)
        {
            if(author1.getNoOfBooks()>maxNumberOfBooks)
            {
                maxNumberOfBooks=author1.getNoOfBooks();
                author=author1;
            }
        }
        return author;
    }

    public void setAuthorRepository(AuthorRepository authorRepository)
    {
        this.authorRepository=authorRepository;
    }
}
