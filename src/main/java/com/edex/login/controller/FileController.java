package com.edex.login.controller;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.edex.login.model.Song;
import com.edex.login.repo.SongRepo;

// Annotation 
@RestController
@RequestMapping("/file")
public class FileController {
	@Autowired
	private Environment env;
	

@Autowired
private SongRepo songRepo;


	@PostMapping("/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile uploadedFile) {
		if (uploadedFile.isEmpty()) {
			return new ResponseEntity<>("Please select a file!", HttpStatus.OK);
		}
		try {

			byte[] bytes = uploadedFile.getBytes();

			UUID uuid = UUID.randomUUID();
//			String uploadsLocation = env.getProperty("resource.uploads");
			String uploadsLocation = "C:\\Users\\Lenovo\\OneDrive\\Documents\\code\\springboot\\workspace\\myspotify\\src\\main\\resources\\Uploads\\";
			String fileLocation = uploadsLocation + uuid + uploadedFile.getOriginalFilename();
			Path path = Paths.get(fileLocation);
			Files.write(path, bytes);

			File file = new File(fileLocation);
			return ResponseEntity.status(HttpStatus.OK).body(file.getName());

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.CREATED).body(e.getMessage());

		}
	}
	
	
	
	@PostMapping("/addfile")
	public ResponseEntity<?> addFile(@RequestBody Song song){
		System.out.println("===Song Details=="+song.getArtistName());
		Song savedEntity = songRepo.saveAndFlush(song);

		return ResponseEntity.status(HttpStatus.OK)
				.body(savedEntity);
		
	}
	
	@GetMapping("/getfile")
	public ResponseEntity<?> getFile(){
		List<Song> songs = songRepo.findAll();
		return ResponseEntity.status(HttpStatus.OK)
				.body(songs);
	}
	
	


}
