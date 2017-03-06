package com.work.mr.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.work.mr.MimarathiApp;
import com.work.mr.domain.Mword;
import com.work.mr.repository.MwordRepository;
import com.work.mr.service.util.MarathiUtil;

/**
 * Test class for the UserResource REST controller.
 *
 * @see UserService
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MimarathiApp.class)
public class CononantMrTest {
	
    @Autowired
    private MwordRepository repo;

	@Test
	public void testFileExists() {

		
		
		List<Mword> twoLetter = MarathiUtil.generateTwoLetter();
		
		int count = 0 ; 
		List<Mword> tempList = new ArrayList<Mword>();
		for (Iterator iterator = twoLetter.iterator(); iterator.hasNext();) {
			Mword mwordDTO = (Mword) iterator.next();
			count++;
			if ( count % 500 != 0){
				tempList.add(mwordDTO);
			} else {
				tempList.add(mwordDTO);
				System.out.println("Adding list to repo " +tempList.size() );
				repo.save(tempList);
				tempList.clear();
			}
			
			
			
			
			
			
			
			
		}
		
		
		System.out.println(twoLetter.size());


	
	}
}
