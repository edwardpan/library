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
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@GenericGenerator(name="increment", strategy="increment")
	@Column(name="id")
	private int id;
	@Column(name="loginName")
	private String loginName;
	@Column(name="realName")
	private String realName;
	@Column(name="password")
	private String password;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
