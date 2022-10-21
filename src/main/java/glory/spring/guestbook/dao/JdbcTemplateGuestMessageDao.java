package glory.spring.guestbook.dao;

import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import glory.spring.guestbook.vo.GuestMessage;

// GuestMessageDAO를 상속 받아 실제적인 데이터베이스 처리를 담당할 구현 클래스 
public class JdbcTemplateGuestMessageDao implements GuestMessageDAO {

	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplateGuestMessageDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public int count() {
		return jdbcTemplate.queryForObject("select count(*) from guestbook", Integer.class);
	}

	@Override
	public List<GuestMessage> select(int begin, int end) {
		return jdbcTemplate.query(
				"select * from (select ROWNUM rnum, MESSAGE_ID, GUEST_NAME,"
						+ "MESSAGE, REGISTRY_DATE " 
						+ "from (select * from GUESTBOOK order by MESSAGE_ID desc)) " 
						+ "where rnum>=? and rnum<=?",
				new GuestMessageRowMapper(),
				new Object[] { begin, end});
	}

	@Override
	public int insert(final GuestMessage message) {
		int insertedCount = jdbcTemplate.update(
				"insert into guestbook(message_id, guest_name, " + 
					"message, registry_date) values (guest_seq.nextval, ?,?,?)"
						, message.getGuestName(), message.getMessage(), message.getRegistryDate());
		
		return insertedCount;
	}

	@Override
	public int delete(int id) {
		return jdbcTemplate.update("delete from guestbook where message_id = ?", id);
	}

	@Override
	public int update(GuestMessage message) {
		
		return jdbcTemplate.update("update guestbook"
									+ "set message = ? where message_id=?",
									new int[] {Types.VARCHAR, Types.INTEGER},
									new Object[] {message.getMessage(), message.getId()});
	}

}
