package com.student.lms.lms;


import com.student.lms.lms.Entities.Book;
import com.student.lms.lms.Service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class LmsApplicationTests {

	@Test
	void contextLoads() {
	}

//	@Test
//	public void test_valid_page_count() throws Exception {
//		BookService bookService = new BookService();
//		Book book = new Book();
//		book.setNoOfPages(100);
//		String result = bookService.addBook(book);
//		assertEquals("book is added into the db with bookId 1", result);
//	}
}
