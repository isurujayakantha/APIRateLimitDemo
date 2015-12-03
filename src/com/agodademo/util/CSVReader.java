package com.agodademo.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.agodademo.model.Record;

/**
 * @author Isuru Jayakantha
 *
 */
public class CSVReader {
	private List<Record> records;
	private String csvFile;
	private String line = "";
	private String cvsSplitBy = ",";

	public CSVReader(String csvFile) {
		this.csvFile = csvFile;
	}

	public List<Record> loadCSV() {
		records = new ArrayList<>();
		BufferedReader br = null;
		int currentLine = 0;
		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				if (currentLine > 0) {
					// use comma as separator
					String[] rows = line.split(cvsSplitBy);
					final Record record = new Record();
					record.setCity(rows[0]);
					System.out.println("ROW ID IS : " + rows[1]);
					record.setHotelId(rows[1]);
					record.setRoom(rows[2]);
					record.setPrice(rows[3]);
					records.add(record);
				}
				++currentLine;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return records;
	}

}