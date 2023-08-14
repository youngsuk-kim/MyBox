package dev.bread.springboot.file.http;

import dev.bread.springboot.file.config.HttpClientConfig;

import java.io.File;

import static dev.bread.springboot.file.config.HttpClientConfig.BUCKET_NAME;
import static dev.bread.springboot.file.config.HttpClientConfig.S3;

public class HttpClient implements CreateFolder, UploadFile {

	@Override
	public void createFolder(String folderName) {
		S3.putObject(HttpClientConfig.fileUploadConfig(folderName));
		System.out.format("Folder %s has been created.\n", folderName);
	}

	@Override
	public void upload(String fileName) {
		String objectName = "sample-object";
		String filePath = "/tmp/sample.txt";

		S3.putObject(BUCKET_NAME, objectName, new File(filePath));
		System.out.format("Object %s has been created.\n", objectName);
	}

}
