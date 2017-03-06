package com.work.mr.web.rest;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.work.mr.domain.Mword;
import com.work.mr.repository.MwordRepository;
import com.work.mr.service.util.MarathiUtil;

/**
 * REST controller for managing Mword.
 */
@RestController
@RequestMapping("/marathi")
public class MarathiResource {

	private final Logger log = LoggerFactory.getLogger(MarathiResource.class);

	@Autowired
	private MwordRepository mwordRepository;

	/**
	 * GET /mwords : get all the mwords.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of mwords in
	 *         body
	 * @throws URISyntaxException
	 *             if there is an error to generate the pagination HTTP headers
	 */
	@GetMapping("/init")
	@Timed
	public long getAllMwords() {
		log.debug("REST request to get a page of Mwords");
		List<Mword> twoLetter = MarathiUtil.generateTwoLetter();

		long count = 0;
		List<Mword> tempList = new ArrayList<Mword>();
		for (Iterator iterator = twoLetter.iterator(); iterator.hasNext();) {
			Mword mwordDTO = (Mword) iterator.next();
			count++;
			if (count % 500 != 0) {
				tempList.add(mwordDTO);
			} else {
				tempList.add(mwordDTO);
				System.out.println("Adding list to repo " + tempList.size());
				mwordRepository.save(tempList);
				tempList.clear();
			}

		}
		return count;

	}

}
