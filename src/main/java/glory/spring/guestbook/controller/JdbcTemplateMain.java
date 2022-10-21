package glory.spring.guestbook.controller;

import java.util.Date;
import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import glory.spring.guestbook.dao.JdbcTemplateGuestMessageDao;
import glory.spring.guestbook.vo.GuestMessage;

public class JdbcTemplateMain {
	
	private GuestMessage makeGuestMessage(String guestName, String message) {
		GuestMessage msg = new GuestMessage();
		msg.setGuestName(guestName);
		msg.setMessage(message);
		msg.setRegistryDate(new Date());
		return msg;
	}
	
	public static void main(String[] args) {
		String[] configLocations = new String[] {"applicationContext.xml"};
		AbstractApplicationContext context =
				new ClassPathXmlApplicationContext(configLocations);
		
		JdbcTemplateGuestMessageDao dao = context.getBean("jdbcTemplateGuestMessageDao", JdbcTemplateGuestMessageDao.class);
		
		JdbcTemplateMain myTest = new JdbcTemplateMain();
		
		dao.insert(myTest.makeGuestMessage("이영광", "오늘도 스프링~"));
		dao.insert(myTest.makeGuestMessage("박보영", "안녕하세요~! 박보영입니다."));
		dao.insert(myTest.makeGuestMessage("박서준", "안녕하세요~! 박서준입니다."));
		
		int count = dao.count();
		System.out.println("전체글 수 : " + count);
		List<GuestMessage> list = dao.select(1, count);
		for(GuestMessage msg : list) {
			System.out.println(msg.getId() + " : " + msg.getGuestName() + " : " + msg.getMessage() + " : " + msg.getRegistryDate());
		}
		context.close();
	}

}
