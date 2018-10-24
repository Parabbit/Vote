package hello;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MybatiesDao {
	@Select("select * from User where id=#{id}")
	public List<User> findById(User param);
}
