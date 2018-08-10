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
	private Integer parentId;
	private String name;
	private String province;
	private String code;
	private Date createAt;
	private Date updateAt;

	public School() {
	}

	public School(String name, String province, String academy) {
		this.name = name;
		this.province = province;
	}

	public School(String name, String province, Date createAt, Date updateAt) {
		this.name = name;
		this.province = province;
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

	@Column(name = "parentId", nullable = false)
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at", nullable = true,length = 19)
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
		
	@Column(name = "code", nullable = false, length = 32)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
}
