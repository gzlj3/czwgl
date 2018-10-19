package com.lj.czwgl.repository;

import org.springframework.data.repository.CrudRepository;

import com.lj.czwgl.domain.Housefy;

public interface HousefyRepository extends CrudRepository<Housefy, String> {
	Housefy findFirstByHouseidOrderByRq1Desc(String hosueid);
	// Iterable<Housefy> findByYzhidAndZhxmNotNullOrderByFwmc(String yzhid);
	//
	// Iterable<Housefy> findByYzhidOrderByFwmc(String yzhid);

	// @Modifying
	// @Query("select u from Housefy u where u.yzhid=?1 and u.szrq<=curdate() order by u.fwmc")
	// Iterable<Housefy> queryZdList(String yzhid);
	//
	// @Modifying
	// @Query("update Housefy u set u.dbcds = ?1, u.sbcds=?2 where u.houseid = ?3")
	// void updateHousefySdb(int dbcds, int sbcds, String houseid);
}
