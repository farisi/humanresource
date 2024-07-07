package org.sf.app.helpers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class FileStorage {
	
	@Value("${upload.path}")
    private String uploadPath;
		
	public String createImage(MultipartFile file, String path,String name) {
		Path root = Paths.get("src/main/resources/" + uploadPath + "/" + path + "/");
		try {
			Files.deleteIfExists(root.resolve(name));
			Files.copy(file.getInputStream(), root.resolve(name));
		}
		catch(Exception e) {
			if (e instanceof FileAlreadyExistsException) {
		        throw new RuntimeException("A file of that name already exists.");
		    }
		      
		    if(e instanceof UnsupportedOperationException) {
		    	throw new RuntimeException(" Operation not supported");
		    }
		    throw new RuntimeException(e.getMessage());
		}
		return name;
	}
	
	public String createThumbnail(MultipartFile file, String path,String name) {
		Path thumbnail = Paths.get("src/main/resources/" + uploadPath + "/" + path + "/thumbnails/");
		try {
			Files.deleteIfExists(thumbnail.resolve(name));
			Files.copy(file.getInputStream(), thumbnail.resolve(name));
		}
		catch(Exception e) {
			if (e instanceof FileAlreadyExistsException) {
		        throw new RuntimeException("A file of that name already exists.");
		    }
		      
		    if(e instanceof UnsupportedOperationException) {
		    	throw new RuntimeException(" Operation not supported");
		    }
		    throw new RuntimeException(e.getMessage());
		}
		return name;
	}
}
