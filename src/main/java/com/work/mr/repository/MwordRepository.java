package com.work.mr.repository;

import com.work.mr.domain.Mword;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Mword entity.
 */
@SuppressWarnings("unused")
public interface MwordRepository extends JpaRepository<Mword,Long> {

}
