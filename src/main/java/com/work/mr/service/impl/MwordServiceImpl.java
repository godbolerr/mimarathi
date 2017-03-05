package com.work.mr.service.impl;

import com.work.mr.service.MwordService;
import com.work.mr.domain.Mword;
import com.work.mr.repository.MwordRepository;
import com.work.mr.service.dto.MwordDTO;
import com.work.mr.service.mapper.MwordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Mword.
 */
@Service
@Transactional
public class MwordServiceImpl implements MwordService{

    private final Logger log = LoggerFactory.getLogger(MwordServiceImpl.class);
    
    private final MwordRepository mwordRepository;

    private final MwordMapper mwordMapper;

    public MwordServiceImpl(MwordRepository mwordRepository, MwordMapper mwordMapper) {
        this.mwordRepository = mwordRepository;
        this.mwordMapper = mwordMapper;
    }

    /**
     * Save a mword.
     *
     * @param mwordDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MwordDTO save(MwordDTO mwordDTO) {
        log.debug("Request to save Mword : {}", mwordDTO);
        Mword mword = mwordMapper.mwordDTOToMword(mwordDTO);
        mword = mwordRepository.save(mword);
        MwordDTO result = mwordMapper.mwordToMwordDTO(mword);
        return result;
    }

    /**
     *  Get all the mwords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MwordDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Mwords");
        Page<Mword> result = mwordRepository.findAll(pageable);
        return result.map(mword -> mwordMapper.mwordToMwordDTO(mword));
    }

    /**
     *  Get one mword by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MwordDTO findOne(Long id) {
        log.debug("Request to get Mword : {}", id);
        Mword mword = mwordRepository.findOne(id);
        MwordDTO mwordDTO = mwordMapper.mwordToMwordDTO(mword);
        return mwordDTO;
    }

    /**
     *  Delete the  mword by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Mword : {}", id);
        mwordRepository.delete(id);
    }
}
