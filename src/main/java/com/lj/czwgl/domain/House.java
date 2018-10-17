package com.lj.czwgl.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * House entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "house", catalog = "czwgl", uniqueConstraints = @UniqueConstraint(columnNames = {
		"yyhid", "fwmc" }))
public class House implements java.io.Serializable {

	// Fields

	private String houseid;
	private String yyhid;
	private String fwmc;
	private String zhxm;
	private String sfzh;
	private String dhhm;
	private Integer czje;
	private Integer yj;
	private Date htrqq;
	private Date htrqz;
	private Date szrq;
	private Integer dqsds;
	private Integer sqsds;
	private Double dfdj;
	private Double sfdj;
	private Integer wlf;
	private Integer glf;
	private Integer ljf;
	private Integer qtf;
	private Integer dbcds;
	private Integer sbcds;
	private String bz;

	// Constructors

	/** default constructor */
	public House() {
	}

	/** minimal constructor */
	public House(String houseid, String yyhid, String fwmc) {
		this.houseid = houseid;
		this.yyhid = yyhid;
		this.fwmc = fwmc;
	}

	/** full constructor */
	public House(String houseid, String yyhid, String fwmc, String zhxm,
			String sfzh, String dhhm, Integer czje, Integer yj, Date htrqq,
			Date htrqz, Date szrq, Integer dqsds, Integer sqsds, Double dfdj,
			Double sfdj, Integer wlf, Integer glf, Integer ljf, Integer qtf,
			Integer dbcds, Integer sbcds, String bz) {
		this.houseid = houseid;
		this.yyhid = yyhid;
		this.fwmc = fwmc;
		this.zhxm = zhxm;
		this.sfzh = sfzh;
		this.dhhm = dhhm;
		this.czje = czje;
		this.yj = yj;
		this.htrqq = htrqq;
		this.htrqz = htrqz;
		this.szrq = szrq;
		this.dqsds = dqsds;
		this.sqsds = sqsds;
		this.dfdj = dfdj;
		this.sfdj = sfdj;
		this.wlf = wlf;
		this.glf = glf;
		this.ljf = ljf;
		this.qtf = qtf;
		this.dbcds = dbcds;
		this.sbcds = sbcds;
		this.bz = bz;
	}

	// Property accessors
	@Id
	@Column(name = "houseid", unique = true, nullable = false, length = 32)
	public String getHouseid() {
		return this.houseid;
	}

	public void setHouseid(String houseid) {
		this.houseid = houseid;
	}

	@Column(name = "yyhid", nullable = false, length = 32)
	public String getYyhid() {
		return this.yyhid;
	}

	public void setYyhid(String yyhid) {
		this.yyhid = yyhid;
	}

	@Column(name = "fwmc", nullable = false, length = 20)
	public String getFwmc() {
		return this.fwmc;
	}

	public void setFwmc(String fwmc) {
		this.fwmc = fwmc;
	}

	@Column(name = "zhxm", length = 50)
	public String getZhxm() {
		return this.zhxm;
	}

	public void setZhxm(String zhxm) {
		this.zhxm = zhxm;
	}

	@Column(name = "sfzh", length = 18)
	public String getSfzh() {
		return this.sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	@Column(name = "dhhm", length = 50)
	public String getDhhm() {
		return this.dhhm;
	}

	public void setDhhm(String dhhm) {
		this.dhhm = dhhm;
	}

	@Column(name = "czje")
	public Integer getCzje() {
		return this.czje;
	}

	public void setCzje(Integer czje) {
		this.czje = czje;
	}

	@Column(name = "yj")
	public Integer getYj() {
		return this.yj;
	}

	public void setYj(Integer yj) {
		this.yj = yj;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "htrqq", length = 10)
	public Date getHtrqq() {
		return this.htrqq;
	}

	public void setHtrqq(Date htrqq) {
		this.htrqq = htrqq;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "htrqz", length = 10)
	public Date getHtrqz() {
		return this.htrqz;
	}

	public void setHtrqz(Date htrqz) {
		this.htrqz = htrqz;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "szrq", length = 10)
	public Date getSzrq() {
		return this.szrq;
	}

	public void setSzrq(Date szrq) {
		this.szrq = szrq;
	}

	@Column(name = "dqsds")
	public Integer getDqsds() {
		return this.dqsds;
	}

	public void setDqsds(Integer dqsds) {
		this.dqsds = dqsds;
	}

	@Column(name = "sqsds")
	public Integer getSqsds() {
		return this.sqsds;
	}

	public void setSqsds(Integer sqsds) {
		this.sqsds = sqsds;
	}

	@Column(name = "dfdj", precision = 5)
	public Double getDfdj() {
		return this.dfdj;
	}

	public void setDfdj(Double dfdj) {
		this.dfdj = dfdj;
	}

	@Column(name = "sfdj", precision = 5)
	public Double getSfdj() {
		return this.sfdj;
	}

	public void setSfdj(Double sfdj) {
		this.sfdj = sfdj;
	}

	@Column(name = "wlf")
	public Integer getWlf() {
		return this.wlf;
	}

	public void setWlf(Integer wlf) {
		this.wlf = wlf;
	}

	@Column(name = "glf")
	public Integer getGlf() {
		return this.glf;
	}

	public void setGlf(Integer glf) {
		this.glf = glf;
	}

	@Column(name = "ljf")
	public Integer getLjf() {
		return this.ljf;
	}

	public void setLjf(Integer ljf) {
		this.ljf = ljf;
	}

	@Column(name = "qtf")
	public Integer getQtf() {
		return this.qtf;
	}

	public void setQtf(Integer qtf) {
		this.qtf = qtf;
	}

	@Column(name = "dbcds")
	public Integer getDbcds() {
		return this.dbcds;
	}

	public void setDbcds(Integer dbcds) {
		this.dbcds = dbcds;
	}

	@Column(name = "sbcds")
	public Integer getSbcds() {
		return this.sbcds;
	}

	public void setSbcds(Integer sbcds) {
		this.sbcds = sbcds;
	}

	@Column(name = "bz", length = 500)
	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

}