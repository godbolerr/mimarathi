package com.work.mr.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.work.mr.service.MwordService;
import com.work.mr.web.rest.util.HeaderUtil;
import com.work.mr.web.rest.util.PaginationUtil;
import com.work.mr.service.dto.MwordDTO;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Mword.
 */
@RestController
@RequestMapping("/api")
public class MwordResource {

    private final Logger log = LoggerFactory.getLogger(MwordResource.class);

    private static final String ENTITY_NAME = "mword";
        
    private final MwordService mwordService;

    public MwordResource(MwordService mwordService) {
        this.mwordService = mwordService;
    }

    /**
     * POST  /mwords : Create a new mword.
     *
     * @param mwordDTO the mwordDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mwordDTO, or with status 400 (Bad Request) if the mword has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/mwords")
    @Timed
    public ResponseEntity<MwordDTO> createMword(@RequestBody MwordDTO mwordDTO) throws URISyntaxException {
        log.debug("REST request to save Mword : {}", mwordDTO);
        if (mwordDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new mword cannot already have an ID")).body(null);
        }
        MwordDTO result = mwordService.save(mwordDTO);
        return ResponseEntity.created(new URI("/api/mwords/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /mwords : Updates an existing mword.
     *
     * @param mwordDTO the mwordDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mwordDTO,
     * or with status 400 (Bad Request) if the mwordDTO is not valid,
     * or with status 500 (Internal Server Error) if the mwordDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/mwords")
    @Timed
    public ResponseEntity<MwordDTO> updateMword(@RequestBody MwordDTO mwordDTO) throws URISyntaxException {
        log.debug("REST request to update Mword : {}", mwordDTO);
        if (mwordDTO.getId() == null) {
            return createMword(mwordDTO);
        }
        MwordDTO result = mwordService.save(mwordDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mwordDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /mwords : get all the mwords.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of mwords in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/mwords")
    @Timed
    public ResponseEntity<List<MwordDTO>> getAllMwords(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Mwords");
        Page<MwordDTO> page = mwordService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/mwords");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /mwords/:id : get the "id" mword.
     *
     * @param id the id of the mwordDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mwordDTO, or with status 404 (Not Found)
     */
    @GetMapping("/mwords/{id}")
    @Timed
    public ResponseEntity<MwordDTO> getMword(@PathVariable Long id) {
        log.debug("REST request to get Mword : {}", id);
        MwordDTO mwordDTO = mwordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(mwordDTO));
    }

    /**
     * DELETE  /mwords/:id : delete the "id" mword.
     *
     * @param id the id of the mwordDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/mwords/{id}")
    @Timed
    public ResponseEntity<Void> deleteMword(@PathVariable Long id) {
        log.debug("REST request to delete Mword : {}", id);
        mwordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
