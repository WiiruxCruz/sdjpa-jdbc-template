package guru.springframework.jdbc.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import guru.springframework.jdbc.dao.AuthorDao;
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
		return jdbcTemplate.queryForObject("SELECT * FROM author where id = ?", getRowMapper(), id);
	}

	@Override
	public Author findAuthorByName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Author saveNewAuthor(Author author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Author updateAuthor(Author author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Author deleteAuthorById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private RowMapper<Author> getRowMapper(){
		return new AuthorMapper();
	}

}
