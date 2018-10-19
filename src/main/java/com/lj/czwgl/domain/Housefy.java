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
	private Double syjzf;
	private Double qtf;
	private Integer czje;
	private String sfsz;
	private Date szrq;
	private String bz;
	private Double fy1;
	private Double fy2;
	private Double fy3;
	private Double fy4;
	private Double fy5;
	private String by1;
	private String by2;
	private String by3;
	private String by4;
	private String by5;

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
			Double sdj, Integer wlf, Integer glf, Integer ljf, Double syjzf,
			Double qtf, Integer czje, String sfsz, Date szrq, String bz,
			Double fy1, Double fy2, Double fy3, Double fy4, Double fy5,
			String by1, String by2, String by3, String by4, String by5) {
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
		this.syjzf = syjzf;
		this.qtf = qtf;
		this.czje = czje;
		this.sfsz = sfsz;
		this.szrq = szrq;
		this.bz = bz;
		this.fy1 = fy1;
		this.fy2 = fy2;
		this.fy3 = fy3;
		this.fy4 = fy4;
		this.fy5 = fy5;
		this.by1 = by1;
		this.by2 = by2;
		this.by3 = by3;
		this.by4 = by4;
		this.by5 = by5;
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

	@Column(name = "syjzf", precision = 10)
	public Double getSyjzf() {
		return this.syjzf;
	}

	public void setSyjzf(Double syjzf) {
		this.syjzf = syjzf;
	}

	@Column(name = "qtf", precision = 10)
	public Double getQtf() {
		return this.qtf;
	}

	public void setQtf(Double qtf) {
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

	@Column(name = "bz", length = 500)
	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	@Column(name = "fy1", precision = 10)
	public Double getFy1() {
		return this.fy1;
	}

	public void setFy1(Double fy1) {
		this.fy1 = fy1;
	}

	@Column(name = "fy2", precision = 10)
	public Double getFy2() {
		return this.fy2;
	}

	public void setFy2(Double fy2) {
		this.fy2 = fy2;
	}

	@Column(name = "fy3", precision = 10)
	public Double getFy3() {
		return this.fy3;
	}

	public void setFy3(Double fy3) {
		this.fy3 = fy3;
	}

	@Column(name = "fy4", precision = 10)
	public Double getFy4() {
		return this.fy4;
	}

	public void setFy4(Double fy4) {
		this.fy4 = fy4;
	}

	@Column(name = "fy5", precision = 10)
	public Double getFy5() {
		return this.fy5;
	}

	public void setFy5(Double fy5) {
		this.fy5 = fy5;
	}

	@Column(name = "by1", length = 50)
	public String getBy1() {
		return this.by1;
	}

	public void setBy1(String by1) {
		this.by1 = by1;
	}

	@Column(name = "by2", length = 50)
	public String getBy2() {
		return this.by2;
	}

	public void setBy2(String by2) {
		this.by2 = by2;
	}

	@Column(name = "by3", length = 50)
	public String getBy3() {
		return this.by3;
	}

	public void setBy3(String by3) {
		this.by3 = by3;
	}

	@Column(name = "by4", length = 50)
	public String getBy4() {
		return this.by4;
	}

	public void setBy4(String by4) {
		this.by4 = by4;
	}

	@Column(name = "by5", length = 50)
	public String getBy5() {
		return this.by5;
	}

	public void setBy5(String by5) {
		this.by5 = by5;
	}

}