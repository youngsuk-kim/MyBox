package dev.bread.springboot.file.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Property {

	public static final String END_POINT;

	public static final String REGION_NAME;

	public static final String ACCESS_KEY;

	public static final String SECRET_KEY;

	static {
		Properties prop = Property.readProperties("application.properties");

		END_POINT = prop.getProperty("endPoint");
		REGION_NAME = prop.getProperty("regionName");
		ACCESS_KEY = prop.getProperty("accessKey");
		SECRET_KEY = prop.getProperty("secretKey");
	}

	public static Properties readProperties(String propFileName) {
		Properties prop = new Properties();
		InputStream inputStream = Property.class.getClassLoader().getResourceAsStream(propFileName);

		try {
			if (inputStream != null) {
				prop.load(inputStream);
				return prop;
			}
			else {
				throw new FileNotFoundException("프로퍼티 파일 '" + propFileName + "'을 resource에서 찾을 수 없습니다.");
			}
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
