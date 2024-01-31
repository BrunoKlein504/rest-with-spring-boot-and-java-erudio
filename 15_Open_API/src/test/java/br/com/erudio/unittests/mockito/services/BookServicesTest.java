package br.com.erudio.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.erudio.data.vo.v1.BooksVO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.model.Books;
import br.com.erudio.repositories.BooksRepository;
import br.com.erudio.services.BookServices;
import br.com.erudio.unittests.mapper.mocks.MockBooks;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest {

	MockBooks input;

	@InjectMocks
	private BookServices service;

	@Mock
	private BooksRepository repository;

	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockBooks();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAll() {
		List<Books> list = input.mockEntityList();

		when(repository.findAll()).thenReturn(list);

		var people = service.findAll();

		assertNotNull(people);
		assertEquals(14, people.size());

		var booksOne = people.get(1);

		assertNotNull(booksOne);
		assertNotNull(booksOne.getKey());
		assertNotNull(booksOne.getLinks());
//		System.out.println(result.toString());
		assertTrue(booksOne.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
		assertEquals("Some Author1", booksOne.getAuthor());
		assertEquals("Some Title1", booksOne.getTitle());
		assertEquals(25D, booksOne.getPrice());
		assertNotNull(booksOne.getLaunchDate());

		var booksFour = people.get(4);

		assertNotNull(booksFour);
		assertNotNull(booksFour.getKey());
		assertNotNull(booksFour.getLinks());
//		System.out.println(result.toString());
		assertTrue(booksFour.toString().contains("links: [</api/books/v1/4>;rel=\"self\"]"));
		assertEquals("Some Author4", booksFour.getAuthor());
		assertEquals("Some Title4", booksFour.getTitle());
		assertEquals(25D, booksFour.getPrice());
		assertNotNull(booksFour.getLaunchDate());

		var booksSeven = people.get(7);

		assertNotNull(booksSeven);
		assertNotNull(booksSeven.getKey());
		assertNotNull(booksSeven.getLinks());
//		System.out.println(result.toString());
		assertTrue(booksSeven.toString().contains("links: [</api/books/v1/7>;rel=\"self\"]"));
		assertEquals("Some Author7", booksSeven.getAuthor());
		assertEquals("Some Title7", booksSeven.getTitle());
		assertEquals(25D, booksSeven.getPrice());
		assertNotNull(booksSeven.getLaunchDate());
	}

	@Test
	void testFindById() {
		Books entity = input.mockEntity(1);
		entity.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));

		var result = service.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
//		System.out.println(result.toString());
		assertTrue(result.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
		assertEquals("Some Author1", result.getAuthor());
		assertEquals("Some Title1", result.getTitle());
		assertEquals(25D, result.getPrice());
		assertNotNull(result.getLaunchDate());
	}

	@Test
	void testCreate() {
		Books entity = input.mockEntity(1);

		Books persisted = entity;
		persisted.setId(1L);

		BooksVO vo = input.mockVO(1);
		vo.setKey(1L);

		when(repository.save(entity)).thenReturn(persisted);

		var result = service.create(vo);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
//		System.out.println(result.toString());
		assertTrue(result.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
		assertEquals("Some Author1", result.getAuthor());
		assertEquals("Some Title1", result.getTitle());
		assertEquals(25D, result.getPrice());
		assertNotNull(result.getLaunchDate());
	}

	@Test
	void testCreateWithNullBooks() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.create(null);
		});

		String expectedMessage = "It's not allowed to persist a null object!";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	void testUpdate() {
		Books entity = input.mockEntity(1);
		entity.setId(1L);

		Books persisted = entity;
		persisted.setId(1L);

		BooksVO vo = input.mockVO(1);
		vo.setKey(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);

		var result = service.update(vo);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
//		System.out.println(result.toString());
		assertTrue(result.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
		assertEquals("Some Author1", result.getAuthor());
		assertEquals("Some Title1", result.getTitle());
		assertEquals(25D, result.getPrice());
		assertNotNull(result.getLaunchDate());
	}

	@Test
	void testUpdateWithNullBooks() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.update(null);
		});

		String expectedMessage = "It's not allowed to persist a null object!";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	void testDelete() {
		Books entity = input.mockEntity(1);
		entity.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));

		service.delete(1L);
	}

}
