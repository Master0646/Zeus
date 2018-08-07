package com.jianma.zeus.model;
// Generated 2018-7-31 15:53:01 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Menu generated by hbm2java
 */
@Entity
@Table(name = "menu", catalog = "Zeus")
public class Menu implements java.io.Serializable {

	private Integer id;
	private String name;
	private String url;
	private Date createAt;
	private Date updateAt;

	public Menu() {
	}

	public Menu(String name, String url, Date createAt) {
		this.name = name;
		this.url = url;
		this.createAt = createAt;
	}

	public Menu(String name, String url, Date createAt, Date updateAt) {
		this.name = name;
		this.url = url;
		this.createAt = createAt;
		this.updateAt = updateAt;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 10)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "url", nullable = false, length = 30)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at", nullable = true, length = 19)
	public Date getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_at", nullable = true,length = 19)
	public Date getUpdateAt() {
		return this.updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} 
		else 
		{
			if (obj instanceof Menu) {
				Menu m = (Menu) obj;
				if (m.name == this.name && m.url == this.url) {
					return true;
				}
			}
		}
		return false;
	}
}
