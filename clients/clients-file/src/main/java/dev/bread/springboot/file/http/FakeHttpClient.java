package dev.bread.springboot.file.http;

import java.util.logging.Logger;

public class FakeHttpClient implements CreateFolder, UploadFile {

	private static final Logger logger = Logger.getLogger(FakeHttpClient.class.getName());

	@Override
	public void createFolder(String folderName) {
		logger.info("folder create finish");
	}

	@Override
	public void upload(String fileName) {
		logger.info("file create finish");
	}

}
