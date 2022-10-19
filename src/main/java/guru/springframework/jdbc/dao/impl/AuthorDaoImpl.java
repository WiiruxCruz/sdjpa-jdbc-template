package guru.springframework.jdbc.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import guru.springframework.jdbc.dao.AuthorDao;
import guru.springframework.jdbc.dao.extractor.AuthorExtractor;
import guru.springframework.jdbc.dao.mapper.AuthorMapper;
import guru.springframework.jdbc.domain.Author;

@Component
public class AuthorDaoImpl implements AuthorDao {
	
	private final JdbcTemplate jdbcTemplate;
	
	public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public Author getById(Long id) {
		// TODO Auto-generated method stub
		String sql = 
		"select\r\n"
		+ "	author.id as id, first_name, last_name,\r\n"
		+ "    book.id as book_id, book.isbn, book.publisher,\r\n"
		+ "    book.title\r\n"
		+ "from author\r\n"
		+ "left outer join book on author.id = book.author_id\r\n"
		+ "where\r\n"
		+ "author.id = ?\r\n"
		;
		
		//return jdbcTemplate.queryForObject("SELECT * FROM author where id = ?", getRowMapper(), id);
		
		return jdbcTemplate.query(sql, new AuthorExtractor(), id);
	}

	@Override
	public Author findAuthorByName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		
		return jdbcTemplate.queryForObject("SELECT * FROM author WHERE first_name = ? and last_name = ?",
				getRowMapper(),
				firstName,
				lastName
			);
	}

	@Override
	public Author saveNewAuthor(Author author) {
		// TODO Auto-generated method stub
		jdbcTemplate.update(
			"INSERT INTO author(first_name, last_name) values (?, ?)",
			author.getFirstName(),
			author.getLastName()
		);
		
		Long createdId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
		
		return this.getById(createdId);
	}

	@Override
	public Author updateAuthor(Author author) {
		// TODO Auto-generated method stub
		jdbcTemplate.update(
			"UPDATE author SET first_name = ?, last_name = ? WHERE id = ? ",
			author.getFirstName(),
			author.getLastName(),
			author.getId()
		);
		
		return this.getById(author.getId());
	}

	@Override
	public void deleteAuthorById(Long id) {
		// TODO Auto-generated method stub
		jdbcTemplate.update(
			"DELETE FROM author where id = ?",
			id
		);
	}
	
	private RowMapper<Author> getRowMapper(){
		return new AuthorMapper();
	}

}
