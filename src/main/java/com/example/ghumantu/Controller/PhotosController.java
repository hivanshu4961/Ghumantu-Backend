package com.example.ghumantu.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.example.ghumantu.Dto.PhotosResponse;
import com.example.ghumantu.Service.PhotosService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/photos")
public class PhotosController {

	private final PhotosService photosService;
	
	@PostMapping("/{placeName}")
	public ResponseEntity<PhotosResponse> save(@RequestParam("image") MultipartFile file,@PathVariable("placeName") String placeName) throws IOException{
		photosService.save(file,placeName);
		return new ResponseEntity<PhotosResponse>(photosService.save(file,placeName),HttpStatus.OK);
	}
	@GetMapping("/{placeName}")
	public ResponseEntity<List<PhotosResponse>> getPhotos(@PathVariable String placeName){
		return new ResponseEntity<List<PhotosResponse>>(photosService.getPhotos(placeName),HttpStatus.OK);
	}
}
