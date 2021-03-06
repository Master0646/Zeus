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
 * Comment generated by hbm2java
 */
@Entity
@Table(name = "comment", catalog = "Zeus")
public class Comment implements java.io.Serializable {

	private Integer id;
	private int assignmentId;
	private int userId;
	private String content;
	private boolean check;
	private Date createAt;

	private String nickName;
	private String headPortrait;
	
	public Comment() {
	}

	public Comment(int assignmentId, int userId, String content, boolean check, Date createAt) {
		this.assignmentId = assignmentId;
		this.userId = userId;
		this.content = content;
		this.check = check;
		this.createAt = createAt;
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

	@Column(name = "assignmentId", nullable = false)
	public int getAssignmentId() {
		return this.assignmentId;
	}

	public void setAssignmentId(int assignmentId) {
		this.assignmentId = assignmentId;
	}

	@Column(name = "userId", nullable = false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "content", nullable = false)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "check", nullable = false)
	public boolean isCheck() {
		return this.check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at", nullable = true, length = 19)
	public Date getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	@Transient
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Transient
	public String getHeadPortrait() {
		return headPortrait;
	}

	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}

	
}
