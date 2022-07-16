package com.bvrsoftware.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bvrsoftware.payloads.UserDto;
import com.bvrsoftware.service.FileService;
import com.bvrsoftware.service.UserService;

@RestController
@RequestMapping("/api/upload")
public class FilesControlController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
//	post image upload
	@PostMapping(value = "/image/{uId}")
	public ResponseEntity<UserDto> uploadUserImage(
			@RequestParam("image") MultipartFile file,
			@PathVariable Integer uId ){
		UserDto userDto = this.userService.getUserById(uId);
		try {
			this.fileService.DeleteResource(path, userDto.getImage());
			long imgSize=2097152;
			if(file.getSize()<imgSize 
					&& file.getContentType().equalsIgnoreCase("image/png")
					||file.getContentType().equalsIgnoreCase("image/jpeg")) {			
			    String fileName = this.fileService.uploadImage(path, file);
			   userDto.setImage(fileName);
			}
			UserDto updatedImage = this.userService.updateUser(userDto, uId);
	    	return new ResponseEntity<UserDto> (updatedImage, HttpStatus.OK);
		}catch (Exception e) {
		      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(userDto);
		}
		

	}
	
//	Download image
	@GetMapping(value = "/files/{fileName}" )
	public void downloadFiles(
			@PathVariable ("fileName") String fileName,
			HttpServletResponse response) throws  IOException{
		
		InputStream res = this.fileService.getResource(path, fileName);
		//response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	    StreamUtils.copy(res, response.getOutputStream());
	}
	/* @GetMapping("/files/{filename:.+}")
	  @ResponseBody
	  public ResponseEntity<Resource> getFile(@PathVariable String filename) throws FileNotFoundException {
	    Resource file = fileService.getResource(filename);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	  }*/


}
