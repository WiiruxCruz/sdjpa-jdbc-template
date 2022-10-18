package guru.springframework.jdbc.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import guru.springframework.jdbc.domain.Author;

public class AuthorMapper implements RowMapper<Author>{

	@Override
	public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Author author = new Author();
		author.setId(rs.getLong("id"));
		author.setFirstName(rs.getString("first_name"));
		author.setLastName(rs.getString("last_name"));
		
		return author;
	}

}
