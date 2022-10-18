package guru.springframework.jdbc.dao.impl;

import org.springframework.jdbc.core.RowMapper;

import guru.springframework.jdbc.dao.AuthorDao;
import guru.springframework.jdbc.dao.mapper.AuthorMapper;
import guru.springframework.jdbc.domain.Author;

public class AuthorDaoImpl implements AuthorDao {

	@Override
	public Author getById(Long id) {
		// TODO Auto-generated method stub
		return null;
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
