package com.student.lms.lms.Repository;

import com.student.lms.lms.Entities.Book;
import com.student.lms.lms.Entities.LibraryCard;
import com.student.lms.lms.Entities.Transaction;
import com.student.lms.lms.Enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<Transaction,String>
{
 //   Transaction findTransactionByBookAndCardAndTransactionStatus(Book book, LibraryCard card, TransactionStatus issued);

    @Query(value = "SELECT * FROM transaction WHERE book_id = :bookId AND card_id = :cardId AND transaction_status = :transactionStatus", nativeQuery = true)
    Transaction findTransactionByBookAndCardAndTransactionStatus(
            @Param("bookId") Book bookId,
            @Param("cardId") LibraryCard cardId,
            @Param("transactionStatus") TransactionStatus transactionStatus);
}
