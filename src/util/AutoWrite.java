package util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import dao.Data;

public class AutoWrite {
	private  List<String[]> list = new ArrayList<>();

	private int j=0;
	private String name;


	public  void write(String filePath) {


		try {
			// 创建CSV写对象
			CsvWriter csvWriter = new CsvWriter(filePath, ',', Charset.forName("GBK"));
			// CsvWriter csvWriter = new CsvWriter(filePath);

			// 把数据逐行写入
			for(String[] content:list)
				csvWriter.writeRecord(content);
			csvWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getRealName(CsvReader csvReader) throws Throwable {
		String man = csvReader.get(1);
		return Data.Find(man);
		
	}

	public  void read(String filePath) throws Throwable {



		try {
			// 创建CSV读对象
			CsvReader csvReader = new CsvReader(filePath, ',', Charset.forName("GBK"));

			// 读表头
			csvReader.readHeaders();

		
			while (csvReader.readRecord()) {
				if(j==0){
					name = this.getRealName(csvReader)+",";
					j=1;
				}
				// 读一整行
				if (csvReader.getRawRecord().indexOf("问题") != -1){
				//	System.out.println(csvReader.getRawRecord());
					String temp = (name+csvReader.getRawRecord()).replaceAll("\"","");
					
					list.add(temp.split(","));
				// 读这行的某一列
				// System.out.println(csvReader.get("问题"));
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
