package guru.springframework.jdbc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import guru.springframework.jdbc.dao.AuthorDao;
import guru.springframework.jdbc.domain.Author;


@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"guru.springframework.jdbc.dao"})
//@Import(AuthorDaoImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorDaoIntegrationTest {
	
	@Autowired
	AuthorDao ad;
	
	@Test
	void testDeleteAuthor() {
		Author author = new Author();
		author.setFirstName("john");
		author.setLastName("t");
		
		Author saved = ad.saveNewAuthor(author);
		
		ad.deleteAuthorById(saved.getId());
		
		assertThrows(EmptyResultDataAccessException.class,
			() -> {
				ad.getById(saved.getId());
			}
		);
		
		//Author deleted = ad.getById(saved.getId());
		
		//assertThat(deleted).isNull();
	}
	
	@Test
	void testUpdateAuthor() {
		Author author = new Author();
		author.setFirstName("john");
		author.setLastName("t");
		
		Author saved = ad.saveNewAuthor(author);
		saved.setLastName("Thompson");
		Author updated = ad.updateAuthor(saved);
		
		assertThat(updated.getLastName()).isEqualTo("Thompson");
	}
	
	@Test
	void testSaveAuthor() {
		Author author = new Author();
		author.setFirstName("John");
		author.setLastName("Thompson222");
		Author saved = ad.saveNewAuthor(author);
		
		System.out.println("New id is:" + saved.getId());
		
		assertThat(saved).isNotNull();
	}
	
	@Test
	void testGetAuthor() {
		Author author = ad.getById(1L);
		assertThat(author).isNotNull();
	}
	
	@Test
	void testGetAuthorByName() {
		Author author = ad.findAuthorByName("Craig", "Walls");
		assertThat(author).isNotNull();
	}

}
