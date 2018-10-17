package com.lj.czwgl.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Housefy entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "housefy", catalog = "czwgl")
public class Housefy implements java.io.Serializable {

	// Fields

	private String housefyid;
	private String houseid;
	private String fwmc;
	private String zhxm;
	private Date rq1;
	private Date rq2;
	private Integer dscds;
	private Integer dbcds;
	private Integer dgtds;
	private Double ddj;
	private Integer sscds;
	private Integer sbcds;
	private Integer sgtds;
	private Double sdj;
	private Integer wlf;
	private Integer glf;
	private Integer ljf;
	private Integer qtf;
	private Integer czje;
	private String sfsz;
	private Date szrq;
	private String bz;

	// Constructors

	/** default constructor */
	public Housefy() {
	}

	/** minimal constructor */
	public Housefy(String housefyid, String houseid, String fwmc) {
		this.housefyid = housefyid;
		this.houseid = houseid;
		this.fwmc = fwmc;
	}

	/** full constructor */
	public Housefy(String housefyid, String houseid, String fwmc, String zhxm,
			Date rq1, Date rq2, Integer dscds, Integer dbcds, Integer dgtds,
			Double ddj, Integer sscds, Integer sbcds, Integer sgtds,
			Double sdj, Integer wlf, Integer glf, Integer ljf, Integer qtf,
			Integer czje, String sfsz, Date szrq, String bz) {
		this.housefyid = housefyid;
		this.houseid = houseid;
		this.fwmc = fwmc;
		this.zhxm = zhxm;
		this.rq1 = rq1;
		this.rq2 = rq2;
		this.dscds = dscds;
		this.dbcds = dbcds;
		this.dgtds = dgtds;
		this.ddj = ddj;
		this.sscds = sscds;
		this.sbcds = sbcds;
		this.sgtds = sgtds;
		this.sdj = sdj;
		this.wlf = wlf;
		this.glf = glf;
		this.ljf = ljf;
		this.qtf = qtf;
		this.czje = czje;
		this.sfsz = sfsz;
		this.szrq = szrq;
		this.bz = bz;
	}

	// Property accessors
	@Id
	@Column(name = "housefyid", unique = true, nullable = false, length = 32)
	public String getHousefyid() {
		return this.housefyid;
	}

	public void setHousefyid(String housefyid) {
		this.housefyid = housefyid;
	}

	@Column(name = "houseid", nullable = false, length = 32)
	public String getHouseid() {
		return this.houseid;
	}

	public void setHouseid(String houseid) {
		this.houseid = houseid;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "rq1", length = 10)
	public Date getRq1() {
		return this.rq1;
	}

	public void setRq1(Date rq1) {
		this.rq1 = rq1;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "rq2", length = 10)
	public Date getRq2() {
		return this.rq2;
	}

	public void setRq2(Date rq2) {
		this.rq2 = rq2;
	}

	@Column(name = "dscds")
	public Integer getDscds() {
		return this.dscds;
	}

	public void setDscds(Integer dscds) {
		this.dscds = dscds;
	}

	@Column(name = "dbcds")
	public Integer getDbcds() {
		return this.dbcds;
	}

	public void setDbcds(Integer dbcds) {
		this.dbcds = dbcds;
	}

	@Column(name = "dgtds")
	public Integer getDgtds() {
		return this.dgtds;
	}

	public void setDgtds(Integer dgtds) {
		this.dgtds = dgtds;
	}

	@Column(name = "ddj", precision = 5)
	public Double getDdj() {
		return this.ddj;
	}

	public void setDdj(Double ddj) {
		this.ddj = ddj;
	}

	@Column(name = "sscds")
	public Integer getSscds() {
		return this.sscds;
	}

	public void setSscds(Integer sscds) {
		this.sscds = sscds;
	}

	@Column(name = "sbcds")
	public Integer getSbcds() {
		return this.sbcds;
	}

	public void setSbcds(Integer sbcds) {
		this.sbcds = sbcds;
	}

	@Column(name = "sgtds")
	public Integer getSgtds() {
		return this.sgtds;
	}

	public void setSgtds(Integer sgtds) {
		this.sgtds = sgtds;
	}

	@Column(name = "sdj", precision = 5)
	public Double getSdj() {
		return this.sdj;
	}

	public void setSdj(Double sdj) {
		this.sdj = sdj;
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

	@Column(name = "czje")
	public Integer getCzje() {
		return this.czje;
	}

	public void setCzje(Integer czje) {
		this.czje = czje;
	}

	@Column(name = "sfsz", length = 1)
	public String getSfsz() {
		return this.sfsz;
	}

	public void setSfsz(String sfsz) {
		this.sfsz = sfsz;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "szrq", length = 10)
	public Date getSzrq() {
		return this.szrq;
	}

	public void setSzrq(Date szrq) {
		this.szrq = szrq;
	}

	@Column(name = "bz", length = 100)
	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

}