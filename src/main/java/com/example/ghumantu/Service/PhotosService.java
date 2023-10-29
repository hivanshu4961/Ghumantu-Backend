package com.example.ghumantu.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.example.ghumantu.Dto.PhotosResponse;
import com.example.ghumantu.Exception.SpringException;
import com.example.ghumantu.Model.Photos;
import com.example.ghumantu.Model.Place;
import com.example.ghumantu.Model.User;
import com.example.ghumantu.Repository.PhotosRepository;
import com.example.ghumantu.Repository.PlaceRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PhotosService {
	
	private final PhotosRepository photosRepository;
	private final PlaceRepository placeRepository;
	private final AuthService authService;
	
	
	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
//		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();
	}
	
	private PhotosResponse Map(Photos photo) {
		return PhotosResponse.builder()
				.createdDate(photo.getCreatedDate())
				.image(decompressBytes(photo.getImage()))
				.photoId(photo.getPhotoId())
				.username(photo.getUser().getUsername())
				.build();
	}
	
	 public List<PhotosResponse> getPhotos(String placeName) {
		Place place = placeRepository.findByPlaceName(placeName).orElseThrow(()-> new SpringException("place not found..."));
		return photosRepository.getByPlace(place)
				.stream()
				.map(this :: Map)
				.collect(Collectors.toList());
	}
	 
	 public static byte[] decompressBytes(byte[] data) {
			Inflater inflater = new Inflater();
			inflater.setInput(data);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
			byte[] buffer = new byte[1024];
			try {
				while (!inflater.finished()) {
					int count = inflater.inflate(buffer);
					outputStream.write(buffer, 0, count);
				}
				outputStream.close();
			} catch (IOException ioe) {
			} catch (DataFormatException e) {
			}
			return outputStream.toByteArray();
	 }

	public PhotosResponse save(MultipartFile file,String placeName) throws IOException{
		Place place = placeRepository.findByPlaceName(placeName).orElseThrow(()-> new SpringException("place not found"));
		User user = authService.getCurrentUser();
		if(user == null) {
			throw new SpringException("User not found");
		}
		Photos img = new Photos();
		img.setCreatedDate(Instant.now());
		img.setUser(user);
		img.setPlace(place);
		img.setImage(compressBytes(file.getBytes()));
		photosRepository.save(img);
		return Map(img);
		
	}

//	public PhotosDto getPhoto(Integer photoId) {
//		Photos photo = photosRepository.findById(photoId).orElseThrow(()-> new SpringException("photo not found"));
//		return Map(photo);
//	}

	

}
