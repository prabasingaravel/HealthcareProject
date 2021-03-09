package com.aws.s3.filestorage.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * StorageService is used for file storage controller operation.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@Service
@Slf4j
public class StorageService {

	private static final String bucketName = "filestorages";
	
	private final AmazonS3 s3Client;
	
	@Autowired
	public StorageService(AmazonS3 s3Client) {
		this.s3Client = s3Client;
	}

	/**
	 * uploadFile method is used to upload file to s3 bucket.
	 * @param file
	 * @return String
	 */
	public String uploadFile(MultipartFile file) {
		File fileObject = convertMultiPartFileToFile(file);
		String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObject));
		fileObject.delete(); 
		return "File uploaded: " + fileName;
	}

	/**
	 * downloadFile method is used to download file from s3 bucket.
	 * @param fileName
	 * @return byte[]
	 */
	public byte[] downloadFile(String fileName) {
		S3Object s3Object = s3Client.getObject(bucketName, fileName);
		S3ObjectInputStream inputStream = s3Object.getObjectContent();
		try {
			byte[] content = IOUtils.toByteArray(inputStream);
			return content;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * deleteFile method is used to delete file in s3 bucket.
	 * @param fileName
	 * @return String
	 */
	public String deleteFile(String fileName) {
		s3Client.deleteObject(bucketName, fileName);
		return fileName + "removed";
	}

	/**
	 * convertMultiPartFileToFile method is used to convert multi part file to file.
	 * @param MultipartFile
	 * @return File
	 */
	private File convertMultiPartFileToFile(MultipartFile file) {
		File convertedFile = new File(file.getOriginalFilename());
		try(FileOutputStream fos = new FileOutputStream(convertedFile)){
			fos.write(file.getBytes());
		}catch(IOException e) {
			log.error("Error converting multipartFile to file", e);
		}
		return convertedFile;
	}
}
