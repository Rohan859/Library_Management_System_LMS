package com.student.lms.lms.Service;

import com.student.lms.lms.Entities.Author;
import com.student.lms.lms.Entities.Book;
import com.student.lms.lms.Exceptions.InvalidPageCountException;
import com.student.lms.lms.Repository.AuthorRepository;
import com.student.lms.lms.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService
{
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public String addBook(Book book) throws Exception
    {
        if(book.getNoOfPages()<=0)
        {
            throw new InvalidPageCountException("Page count is less than or eaual to zero");
        }
        bookRepository.save(book);
        return "book is added into the db with bookId "+book.getBookId();
    }

    public String associateBookAndAuthor(Integer bookId,Integer authorId) throws Exception
    {
        Optional<Book>bookOptional=bookRepository.findById(bookId);

        if(bookOptional.isEmpty())
        {
            //throw an exception
            throw new Exception("Book id entered is incorrect");
        }

        Book book=bookOptional.get();

//        Author author=authorRepository.findById(authorId).get();
//        Book book=bookRepository.findById(bookId).get();

        Optional<Author>authorOptional=authorRepository.findById(authorId);

        if(authorOptional.isEmpty())
        {
            //throw an exception
            throw new Exception("Author id entered is incorrect");
        }

        Author author=authorOptional.get();

        author.setNoOfBooks(author.getNoOfBooks()+1);
        book.setAuthor(author);

//        //add in list of books
//        author.getBookList().add(book);

        //save book and author into db

        authorRepository.save(author);
        bookRepository.save(book);

        return "successfully added the book with id "+book.getBookId()+" with the author with id "+author.getAuthorId();
    }

    public List<Book> getBookListByAuthorId(Integer authorId)
    {
        List<Book>allBooks=bookRepository.findAll();

        List<Book>ans=new ArrayList<>();

        for(Book book:allBooks)
        {
            if(book.getAuthor().getAuthorId().equals(authorId))
            {
                ans.add(book);
            }
        }
        return ans;
    }
}
