package br.com.erudio.unittests.mapper.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.erudio.data.vo.v1.BooksVO;
import br.com.erudio.model.Books;

public class MockBooks {


    public Books mockEntity() {
        return mockEntity(0);
    }
    
    public BooksVO mockVO() {
        return mockVO(0);
    }
    
    public List<Books> mockEntityList() {
        List<Books> books = new ArrayList<Books>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BooksVO> mockVOList() {
        List<BooksVO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockVO(i));
        }
        return books;
    }
    
    public Books mockEntity(Integer number) {
        Books books = new Books();
        books.setId(number.longValue());
        books.setAuthor("Some Author" + number);
        books.setLaunchDate(new Date());
        books.setPrice(25D);
        books.setTitle("Some Title" + number);
        return books;
    }

    public BooksVO mockVO(Integer number) {
    	BooksVO book = new BooksVO();
        book.setKey(number.longValue());
        book.setAuthor("Some Author" + number);
        book.setLaunchDate(new Date());
        book.setPrice(25D);
        book.setTitle("Some Title" + number);
        return book;
    }

}
