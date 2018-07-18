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
import javax.persistence.Transient;

/**
 * UserCurriculum generated by hbm2java
 */
@Entity
@Table(name = "user_curriculum", catalog = "Zeus")
public class UserCurriculum implements java.io.Serializable {

	private Integer id;
	private int userId;
	private int curriculumId;
	private boolean valid;
	private Date insertAt;

	private String curriculumName;
	private String teacherName;
	private String curriculumRemark;
	
	public UserCurriculum() {
	}

	public UserCurriculum(int userId, int curriculumId, boolean valid) {
		this.userId = userId;
		this.curriculumId = curriculumId;
		this.valid = valid;
	}

	public UserCurriculum(int userId, int curriculumId, boolean valid, Date insertAt) {
		this.userId = userId;
		this.curriculumId = curriculumId;
		this.valid = valid;
		this.insertAt = insertAt;
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

	@Column(name = "userId", nullable = false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "curriculumId", nullable = false)
	public int getCurriculumId() {
		return this.curriculumId;
	}

	public void setCurriculumId(int curriculumId) {
		this.curriculumId = curriculumId;
	}

	@Column(name = "valid", nullable = false)
	public boolean isValid() {
		return this.valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "insert_at", length = 19)
	public Date getInsertAt() {
		return this.insertAt;
	}

	public void setInsertAt(Date insertAt) {
		this.insertAt = insertAt;
	}

	@Transient
	public String getCurriculumName() {
		return curriculumName;
	}

	public void setCurriculumName(String curriculumName) {
		this.curriculumName = curriculumName;
	}

	@Transient
	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	@Transient
	public String getCurriculumRemark() {
		return curriculumRemark;
	}

	public void setCurriculumRemark(String curriculumRemark) {
		this.curriculumRemark = curriculumRemark;
	}

	
}