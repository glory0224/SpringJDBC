package glory.spring.guestbook.vo;

import java.util.Date;

/*
 * GUESTBOOK TABLE

CREATE TABLE GUESTBOOK (
MESSAGE_ID NUMBER(5,0), 
GUEST_NAME VARCHAR2(20) DEFAULT null, 
MESSAGE VARCHAR2(4000), 
REGISTRY_DATE DATE DEFAULT null, 
CONSTRAINT GUESTBOOK_PK PRIMARY KEY (MESSAGE_ID) 
);


SEQUENCE 생성 

CREATE SEQUENCE GUEST_SEQ MINVALUE 1 MAXVALUE 99999 
INCREMENT BY 1 START WITH 1 NOCACHE NOORDER NOCYCLE;

 */

public class GuestMessage {
	private Integer id;
	private String guestName;
	private String message;
	private Date registryDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getRegistryDate() {
		return registryDate;
	}
	public void setRegistryDate(Date registryDate) {
		this.registryDate = registryDate;
	}
	
	
}
