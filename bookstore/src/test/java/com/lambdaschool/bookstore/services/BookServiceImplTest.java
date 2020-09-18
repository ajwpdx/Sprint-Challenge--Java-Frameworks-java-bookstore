package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.BookstoreApplication;
import com.lambdaschool.bookstore.exceptions.ResourceNotFoundException;
import com.lambdaschool.bookstore.models.*;
import com.lambdaschool.bookstore.repository.BookRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookstoreApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//**********
// Note security is handled at the controller, hence we do not need to worry about security here!
//**********
public class BookServiceImplTest
{

    @Autowired
    private BookService bookService;

    @Autowired SectionService sectionService;

    @Autowired AuthorService authorService;

    @Before
    public void setUp() throws
            Exception
    {
        MockitoAnnotations.initMocks(this);

//        List<Book> myList = bookService.findAll();
//        for (Book b : myList)
//        {
//            System.out.println(b.getBookid() + " " + b.getTitle());
//        }

//        List<Section> myList = sectionService.findAll();
//        for (Section s : myList)
//        {
//            System.out.println(s.getSectionid() + " " + s.getName());
//        }
//
//        List<Author> aList = authorService.findAll();
//        for (Author author : aList)
//        {
//            System.out.println(author.getAuthorid() + " " + author.getLname());
//        }

//        26 Flatterland
//        27 Digital Fortess
//        28 The Da Vinci Code
//        29 Essentials of Finance
//        30 Calling Texas Home

//        21 Fiction
//        22 Technology
//        23 Travel
//        24 Business
//        25 Religion
    }

    @After
    public void tearDown() throws
            Exception
    {
    }

    @Test
    public void a_findAll()
    {
        assertEquals(5, bookService.findAll().size());

    }

    @Test
    public void b_findBookById()
    {
       assertEquals("Calling Texas Home", bookService.findBookById(30).getTitle());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void bb_notFindBookById()
    {
        assertEquals("", bookService.findBookById(37).getTitle());
    }

    @Test
    public void c_delete()
    {
        bookService.delete(28);
        assertEquals(4, bookService.findAll().size());
    }

    @Test
    public void d_save()
    {
        String b7title = "Crushing a Java Sprint at Lambda";
        Author a7 = new Author("John", "Mitchell");
        Section s2 = new Section("Technology");

        Book b7 = new Book("Crushing a Java Sprint at Lambda", "9780738206753", 2020, s2);
        s2.setSectionid(22);

        b7.getWrotes()
                .add(new Wrote(a7, new Book()));
        a7.setAuthorid(15);
        Book savedBook = bookService.save(b7);

        assertNotNull(savedBook);
        assertEquals(b7title, savedBook.getTitle());

    }

    @Test
    public void e_update()
    {
    }

    @Test
    public void f_deleteAll()
    {
    }
}