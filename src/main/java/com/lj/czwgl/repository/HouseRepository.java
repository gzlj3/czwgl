package com.lj.czwgl.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.lj.czwgl.domain.House;

public interface HouseRepository extends CrudRepository<House, String> {
	Iterable<House> findByYzhidAndZhxmNotNullOrderByFwmc(String yzhid);

	Iterable<House> findByYzhidOrderByFwmc(String yzhid);
	
	@Modifying
	@Query("select u from House u where u.yzhid=?1 and sfsz='1' and u.szrq<=curdate() order by u.fwmc")
	Iterable<House> queryZdList(String yzhid);

	@Modifying
	@Query("update House u set u.dbcds = ?1, u.sbcds=?2 where u.houseid = ?3")
	void updateHouseSdb(int dbcds, int sbcds, String houseid);
}
