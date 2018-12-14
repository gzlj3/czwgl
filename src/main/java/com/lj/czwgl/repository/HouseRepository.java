package com.lj.czwgl.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.lj.czwgl.domain.House;

public interface HouseRepository extends CrudRepository<House, String> {
	@Modifying
	@Query("select u from House u where u.yzhid=?1 and sfsz<>'0' and u.szrq<=?2 order by u.fwmc")
	Iterable<House> findSdbList(String yzhid,Date date);

	Iterable<House> findByYzhidOrderByFwmc(String yzhid);
	
	@Modifying
	@Query("select u from House u where u.yzhid=?1 and sfsz<>'0' and u.szrq<=?2 order by u.fwmc")
	Iterable<House> queryZdList(String yzhid,Date date);

	@Modifying
	@Query("update House u set u.dbcds = ?1, u.sbcds=?2 where u.houseid = ?3")
	void updateHouseSdb(Integer dbcds, Integer sbcds, String houseid);
}
