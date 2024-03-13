package com.student.lms.lms.Service;

import com.student.lms.lms.Entities.Book;
import com.student.lms.lms.Entities.LibraryCard;
import com.student.lms.lms.Entities.Transaction;
import com.student.lms.lms.Enums.TransactionStatus;
import com.student.lms.lms.Repository.BookRepository;
import com.student.lms.lms.Repository.CardRepository;
import com.student.lms.lms.Repository.StudentRepository;
import com.student.lms.lms.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService
{
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private BookRepository bookRepository;

    public static final Integer MAX_LIMIT_OF_ISSUED_BOOKS=3;
    public static final Integer FINE_PER_DAY=5;

    public String issueBook(Integer bookId,Integer cardId) throws Exception
    {

        //1. book and card should be valid
        Optional<Book>bookOptional=bookRepository.findById(bookId);
        if(bookOptional.isEmpty())
        {
            throw new Exception("Book id "+bookId+" is not exist");
        }

        Book book=bookOptional.get();

        //2. Library Card should be valid
        Optional<LibraryCard>libraryCardOptional=cardRepository.findById(cardId);

        if(libraryCardOptional.isEmpty())
        {
            throw new Exception("Library Crad "+cardId+" is not exist");
        }

        LibraryCard card=libraryCardOptional.get();



        //find book and card from db
        //Book book=bookRepository.findById(bookId).get();
        //LibraryCard card=cardRepository.findById(cardId).get();

        //create transaction object

        Transaction transaction=new Transaction();

        //set the relevant attributes of transaction object

        transaction.setBook(book);
        transaction.setLibraryCard(card);
        transaction.setTransactionStatus(TransactionStatus.PENDING);

        //3. book should not be issued

        if(book.isIssued())
        {
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transactionRepository.save(transaction);
            throw new Exception("Book is already issued");
        }

        //4. Limit of card should not be exceeded

        if(card.getNoOfBookIssued()>MAX_LIMIT_OF_ISSUED_BOOKS)
        {
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transactionRepository.save(transaction);
            throw new Exception("You can not take more books than the limit");
        }

        //5. check card validity if it is expired or not

        LocalDate currentDate=LocalDate.now();
        if(currentDate.isAfter(card.getValidity()))
        {
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transactionRepository.save(transaction);
            throw new Exception("Your Card is expired");
        }




        //change the card and book entity attribute
        transaction.setTransactionStatus(TransactionStatus.ISSUED);
        book.setIssued(true);
        card.setNoOfBookIssued(card.getNoOfBookIssued()+1);

        //save all these
        bookRepository.save(book);
        cardRepository.save(card);
        transaction=transactionRepository.save(transaction);

        return "transaction has been done with transaction id "+transaction.getTransactionId();
    }


    public String returnBook(Integer cardId,Integer bookId)
    {
        //find the transaction
        //with bookId,cardId and issued

        Book book=bookRepository.findById(bookId).get();
        LibraryCard card=cardRepository.findById(cardId).get();

        Transaction transaction=transactionRepository.findTransactionByBookAndCardAndTransactionStatus(book,card,TransactionStatus.ISSUED);

        //fine amount calculate

        //time difference
        long timeDifferenceMiliSeconds=System.currentTimeMillis()-transaction.getIssueDate().getTime();
        //convert this MS to days
        Long days= TimeUnit.DAYS.convert(timeDifferenceMiliSeconds,TimeUnit.MILLISECONDS);
        Integer fineAmount=0;

        if(days>15)
        {
            fineAmount=Math.toIntExact(days-15)*FINE_PER_DAY;
        }

        //save the transaction
        transaction.setFineAmount(fineAmount);
        transaction.setTransactionStatus(TransactionStatus.COMPLETED);
        transaction.setReturnDate(new Date());
        book.setIssued(false);
        card.setNoOfBookIssued(card.getNoOfBookIssued()-1);


        transactionRepository.save(transaction);
        bookRepository.save(book);
        cardRepository.save(card);

        return "Book is successfully returned";
    }
}
