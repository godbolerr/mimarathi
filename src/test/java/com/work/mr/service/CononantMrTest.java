package com.work.mr.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
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

		List<String> allLetters = new ArrayList<String>();

		for (int i = 0; i < Constants.aConsonants.length; i++) {

			String consonant = Constants.aConsonants[i];

			String fileName = Constants.BARAKHADI + Constants.FILE_SEPERATOR + consonant + Constants.TXT_EXTN;

			List<String> names = null;

			try {
				// System.out.println("Checking " + fileName + " for " +
				// Constants.consonantMap.get(consonant));
				List<String> myLines = IOUtils.readLines(ClassLoader.getSystemResourceAsStream(fileName), "utf-8");
				for (Iterator<String> iterator = myLines.iterator(); iterator.hasNext();) {
					String string = (String) iterator.next();
					String newString = StringUtils.strip(string);
//					System.out.println(":" + newString + ":");
					allLetters.add(newString);

				}

				// System.out.println( myLines );

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		System.out.println("Total letters " + allLetters.size());

		List<String> thatLetters = new ArrayList<String>();

		thatLetters.addAll(allLetters);
		long count = 0;
		for (Iterator iterator = allLetters.iterator(); iterator.hasNext();) {
			String thisLetter = (String) iterator.next();

			for (Iterator iterator2 = thatLetters.iterator(); iterator2.hasNext();) {
				String thatLetter = (String) iterator2.next();
				if (!"".equals(thisLetter) || !"".equals(thatLetter)) {
					System.out.println(thisLetter + thatLetter );
					count++;
				}
			}
		}
		System.out.println("Total count " + count);

	}
}
