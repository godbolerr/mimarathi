package com.work.mr.service;

import com.work.mr.service.dto.MwordDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing Mword.
 */
public interface MwordService {

    /**
     * Save a mword.
     *
     * @param mwordDTO the entity to save
     * @return the persisted entity
     */
    MwordDTO save(MwordDTO mwordDTO);

    /**
     *  Get all the mwords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<MwordDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" mword.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    MwordDTO findOne(Long id);

    /**
     *  Delete the "id" mword.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
