package kr.ac.hansung.spring.csemall;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component("offerDAO")
public class OfferDAO {

	private JdbcTemplate jdbcTemplateobject;

	@Autowired
	public void setDataSource(DataSource datasource) {
		this.jdbcTemplateobject = new JdbcTemplate(datasource);
	}

	public int getRowcount() {
		String sqlStatement = "select count(*) from offers";
		return jdbcTemplateobject.queryForObject(sqlStatement, Integer.class);

	}

	public Offer getOffer(String name) {
		String sqlStatement = "select * from offers where name=?";
		return jdbcTemplateobject.queryForObject(sqlStatement, new Object[] { name }, new RowMapper<Offer>() {

			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Offer offer = new Offer();
				offer.setId(rs.getInt("id"));
				offer.setName(rs.getString("name"));
				offer.setEmail(rs.getString("email"));
				offer.setText(rs.getString("text"));
				return offer;
			}

		});
	}

	// multiful object
	public List<Offer> getOffers() {
		String sqlStatement = "select * from offers ";
		return jdbcTemplateobject.query(sqlStatement, new RowMapper<Offer>() {

			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Offer offer = new Offer();
				offer.setId(rs.getInt("id"));
				offer.setName(rs.getString("name"));
				offer.setEmail(rs.getString("email"));
				offer.setText(rs.getString("text"));
				return offer;

			}

		});

	}

	public boolean insert(Offer offer) {

		String name = offer.getName();
		String email = offer.getEmail();
		String text = offer.getText();
		String sqlStatement = "insert into offers (name, email, text) values (?,?,?)";
		return (jdbcTemplateobject.update(sqlStatement, new Object[] { name, email, text }) == 1);

	}

	public boolean update(Offer offer) {
		int id = offer.getId();
		String name = offer.getName();
		String email = offer.getEmail();
		String text = offer.getText();
		String sqlStatement = "update offers set name=?, email=?, text=? where id=?";
		return (jdbcTemplateobject.update(sqlStatement, new Object[] { name, email, text, id }) == 1);

	}

	public boolean delete(int id) {
		String sqlstatement ="delete from offers where id=?";
		
		return (jdbcTemplateobject.update(sqlstatement,new Object[]{id})==1);

	}
}
