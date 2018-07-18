package com.jianma.zeus.model;
// Generated 2018-7-16 16:41:26 by Hibernate Tools 4.3.1.Final

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
 * School generated by hbm2java
 */
@Entity
@Table(name = "school", catalog = "Zeus")
public class School implements java.io.Serializable {

	private Integer id;
	private String name;
	private String province;
	private String academy;
	private Date createAt;
	private Date updateAt;

	public School() {
	}

	public School(String name, String province, String academy) {
		this.name = name;
		this.province = province;
		this.academy = academy;
	}

	public School(String name, String province, String academy, Date createAt, Date updateAt) {
		this.name = name;
		this.province = province;
		this.academy = academy;
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

	@Column(name = "name", nullable = false, length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "province", nullable = false, length = 10)
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "academy", nullable = false, length = 20)
	public String getAcademy() {
		return this.academy;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at", length = 19)
	public Date getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_at", length = 19)
	public Date getUpdateAt() {
		return this.updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

}