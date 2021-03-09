package com.aws.s3.filestorage.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aws.s3.filestorage.Service.StorageService;

/**
 * StorageController is used for file storage service end point.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@RestController
@RequestMapping("/files")
public class StorageController {

	private final StorageService storageService;
	
	@Autowired
	public StorageController(StorageService storageService){
		this.storageService = storageService;
	}
	
	/**
	 * uploadFile method is used to upload file to s3 bucket.
	 * @param file
	 * @return ResponseEntity<String>
	 */
	@PostMapping("/")
	public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file){
		return new ResponseEntity<>(storageService.uploadFile(file), HttpStatus.OK);
	}
	
	/**
	 * downloadFile method is used to download file from s3 bucket.
	 * @param fileName
	 * @return ResponseEntity<ByteArrayResource>
	 */
	@GetMapping("/{fileName}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName){
		byte[] data = storageService.downloadFile(fileName);
		ByteArrayResource resource = new ByteArrayResource(data);
		return ResponseEntity
				.ok()
				.contentLength(data.length)
				.header("Content-type", "application/octet-stream")
				.header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
				.body(resource);
	}
	
	/**
	 * deleteFile method is used to delete file in s3 bucket.
	 * @param fileName
	 * @return ResponseEntity<String>
	 */
	@DeleteMapping("/{fileName}")
	public ResponseEntity<String> deleteFile(@PathVariable String fileName){
		return new ResponseEntity<>(storageService.deleteFile(fileName), HttpStatus.OK);
	}
}
