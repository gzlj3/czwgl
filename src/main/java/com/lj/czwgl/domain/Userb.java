package com.lj.czwgl.domain;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Userb entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "userb", catalog = "czwgl", uniqueConstraints = @UniqueConstraint(columnNames = "userid"))
public class Userb implements java.io.Serializable {

	// Fields

	private String userbid;
	private String userid;
	private String username;
	private String dhhm;
	private String dwbh;
	private String yzhid;
	private String lrr;
	private Timestamp lrsj;
	private String zhxgr;
	private Timestamp zhxgsj;

	// Constructors

	/** default constructor */
	public Userb() {
	}

	/** minimal constructor */
	public Userb(String userbid, String userid, String username, String yzhid) {
		this.userbid = userbid;
		this.userid = userid;
		this.username = username;
		this.yzhid = yzhid;
	}

	/** full constructor */
	public Userb(String userbid, String userid, String username, String dhhm,
			String dwbh, String yzhid, String lrr, Timestamp lrsj,
			String zhxgr, Timestamp zhxgsj) {
		this.userbid = userbid;
		this.userid = userid;
		this.username = username;
		this.dhhm = dhhm;
		this.dwbh = dwbh;
		this.yzhid = yzhid;
		this.lrr = lrr;
		this.lrsj = lrsj;
		this.zhxgr = zhxgr;
		this.zhxgsj = zhxgsj;
	}

	// Property accessors
	@Id
	@Column(name = "userbid", unique = true, nullable = false, length = 32)
	public String getUserbid() {
		return this.userbid;
	}

	public void setUserbid(String userbid) {
		this.userbid = userbid;
	}

	@Column(name = "userid", unique = true, nullable = false, length = 50)
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "username", nullable = false, length = 50)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "dhhm", length = 50)
	public String getDhhm() {
		return this.dhhm;
	}

	public void setDhhm(String dhhm) {
		this.dhhm = dhhm;
	}

	@Column(name = "dwbh", length = 50)
	public String getDwbh() {
		return this.dwbh;
	}

	public void setDwbh(String dwbh) {
		this.dwbh = dwbh;
	}

	@Column(name = "yzhid", nullable = false, length = 32)
	public String getYzhid() {
		return this.yzhid;
	}

	public void setYzhid(String yzhid) {
		this.yzhid = yzhid;
	}

	@Column(name = "lrr", length = 20)
	public String getLrr() {
		return this.lrr;
	}

	public void setLrr(String lrr) {
		this.lrr = lrr;
	}

	@Column(name = "lrsj", length = 26)
	public Timestamp getLrsj() {
		return this.lrsj;
	}

	public void setLrsj(Timestamp lrsj) {
		this.lrsj = lrsj;
	}

	@Column(name = "zhxgr", length = 20)
	public String getZhxgr() {
		return this.zhxgr;
	}

	public void setZhxgr(String zhxgr) {
		this.zhxgr = zhxgr;
	}

	@Column(name = "zhxgsj", length = 26)
	public Timestamp getZhxgsj() {
		return this.zhxgsj;
	}

	public void setZhxgsj(Timestamp zhxgsj) {
		this.zhxgsj = zhxgsj;
	}

}