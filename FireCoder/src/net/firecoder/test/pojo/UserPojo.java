/* UserPojo.java
 * project: FireCoder
 */
package net.firecoder.test.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ≈À≥¨
 * create: 2011-7-16
 */
@Entity
@Table(name="User")
public class UserPojo {
	private int id;
	private String loginName;
	private String realName;
	private String password;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@GenericGenerator(name="increment", strategy="increment")
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="loginName")
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	@Column(name="realName")
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	@Column(name="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
