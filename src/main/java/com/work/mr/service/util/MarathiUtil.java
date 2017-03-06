package com.work.mr.service.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.work.mr.config.Constants;
import com.work.mr.domain.Mword;

public class MarathiUtil {

	public static List<Mword> generateTwoLetter() {

		List<String> firstList = (List<String>) Arrays.asList(Constants.mrLetterArray);
		List<String> secondList = (List<String>) Arrays.asList(Constants.mrLetterArray);

		List<Mword> list = new ArrayList<Mword>();

		for (Iterator<String> iterator = firstList.iterator(); iterator.hasNext();) {
			String firstLetter = iterator.next();

			for (Iterator<String> iterator2 = secondList.iterator(); iterator2.hasNext();) {
				String secondLetter = iterator2.next();

				Mword dto = new Mword();
				dto.setFirst(firstLetter);
				dto.setSecond(secondLetter);
				dto.setMword(firstLetter + secondLetter);
				dto.setWordlength(2);
				dto.setStatus(Constants.NOT_READ);
				list.add(dto);

			}
		}

		return list;
	}

	public static List<Mword> generateThreeLetter() {

		List<String> firstList = (List<String>) Arrays.asList(Constants.mrLetterArray);
		List<String> secondList = (List<String>) Arrays.asList(Constants.mrLetterArray);
		List<String> thirdList = (List<String>) Arrays.asList(Constants.mrLetterArray);

		List<Mword> list = new ArrayList<Mword>();

		for (Iterator<String> iterator = firstList.iterator(); iterator.hasNext();) {
			String firstLetter = iterator.next();

			for (Iterator<String> iterator2 = secondList.iterator(); iterator2.hasNext();) {
				String secondLetter = iterator2.next();

				for (Iterator<String> iterator3 = thirdList.iterator(); iterator3.hasNext();) {
					String thirdLetter = iterator3.next();

					Mword dto = new Mword();
					dto.setFirst(firstLetter);
					dto.setSecond(secondLetter);
					dto.setThird(thirdLetter);
					dto.setMword(firstLetter + secondLetter + thirdLetter);
					dto.setWordlength(3);
					dto.setStatus(Constants.NOT_READ);
					list.add(dto);

				}
			}
		}

		return list;
	}

	public static List<String> generateFourLetter() {

		List<String> list = new ArrayList<String>();

		return list;
	}

}
