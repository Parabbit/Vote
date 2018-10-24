package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class testDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	public int update(){
		String sql="update demo set name=? where id=?";
		Object[] params=new Object[]{"YWB",2};
		return jdbcTemplate.update(sql, params);
	}
}
