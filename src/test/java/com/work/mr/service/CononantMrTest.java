package com.work.mr.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import com.work.mr.config.Constants;

/**
 * Test class for the UserResource REST controller.
 *
 * @see UserService
 */
@RunWith(SpringRunner.class)

public class CononantMrTest {


    @Test
    public void testFileExists() {
    	
    	for (int i = 0; i < Constants.aConsonants.length; i++) {
    		
    		String consonant = Constants.aConsonants[i];
    		
    		String fileName = Constants.BARAKHADI + Constants.FILE_SEPERATOR + consonant + Constants.FILE_SEPERATOR + Constants.TXT_EXTN;
    		
    		System.out.println("Checking " + fileName + " for " + Constants.consonantMap.get(consonant));
			
		}
    	
    	
    }
}
