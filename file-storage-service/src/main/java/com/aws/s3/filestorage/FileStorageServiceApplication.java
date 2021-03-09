package com.aws.s3.filestorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * FileStorageServiceApplication contains main method of file storage service.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@SpringBootApplication
public class FileStorageServiceApplication {

	/**
	 * main method is the entry point of file storage service.
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(FileStorageServiceApplication.class, args);
	}

}
