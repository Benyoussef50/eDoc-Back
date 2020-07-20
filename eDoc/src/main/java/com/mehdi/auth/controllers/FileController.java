package com.mehdi.auth.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mehdi.auth.models.Content;
import com.mehdi.auth.payload.response.UploadFileResponse;
import com.mehdi.auth.repository.ContentRepository;
import com.mehdi.auth.repository.PlanRepository;
import com.mehdi.auth.service.FileStorageService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(com.mehdi.auth.constants.WebConstants.DOCS)
public class FileController {
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired
	ContentRepository contentRepo;

	@Autowired
	PlanRepository planRepository;
	@Autowired
	private FileStorageService fileStorageService;

	@PostMapping("/uploadFile")
	@PreAuthorize("hasRole('ROLE_UT') or hasRole('ROLE_ADM')")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
		String fileName = fileStorageService.storeFile(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(fileName).toUriString();

		return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}

	@PostMapping("/uploadFile/{idPlan}")
	@PreAuthorize("hasRole('ROLE_UT') or hasRole('ROLE_ADM')")
	public Optional<Content> uploadFileContent(@RequestParam("file") MultipartFile file,
			@PathVariable("idPlan") Long id) {
		String fileName = fileStorageService.storeFile(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(fileName).toUriString();
		Content c = new Content();
		c.setSetFile(fileName);
		c.setSetDownload(fileDownloadUri);
		c.setFullFormat(file.getContentType());
		/*
		 * return new Content(fileName, fileDownloadUri, file.getContentType(),
		 * file.getSize();
		 */
		return planRepository.findById(id).map(p -> {
			c.setPlan(p);
			return contentRepo.save(c);
		});
	}
	
	@PostMapping("/uploadMultipleFiles/{idPlan}")
	@PreAuthorize("hasRole('ROLE_UT') or hasRole('ROLE_ADM')")
	public List<Optional<Content>> uploadMultipleFilesContent(@RequestParam("files") MultipartFile[] files,@PathVariable("idPlan") Long id) {
		return Arrays.asList(files).stream().map(file -> uploadFileContent(file,id)).collect(Collectors.toList());
	}

	@PostMapping("/uploadMultipleFiles")
	@PreAuthorize("hasRole('ROLE_UT') or hasRole('ROLE_ADM')")
	public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		return Arrays.asList(files).stream().map(file -> uploadFile(file)).collect(Collectors.toList());
	}

	@GetMapping(value = "/downloadFile/{fileName:.+}")
//	@PreAuthorize("hasRole('ROLE_UT') or hasRole('ROLE_ADM')")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		// Load file as Resource
		Resource resource = fileStorageService.loadFileAsResource(fileName);

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			logger.info("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	@RequestMapping(value = "/show/{fileName:.+}")
	public ResponseEntity<byte[]> showPdf(@PathVariable String fileName) throws IOException {
	    
		Resource resource = fileStorageService.loadFileAsResource(fileName);
	    Path path = Paths.get(resource.getFile().getAbsolutePath());
	    byte[] pdfContents = null;
	    try {
	        pdfContents = Files.readAllBytes(path);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_PDF);
	//    String filename = NAME_PDF;
	  //  headers.setContentDispositionFormData(fileName, fileName);
	    headers.add("content-disposition", "inline; filename=" + fileName);
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	    
	    ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(
	            pdfContents, headers, HttpStatus.OK);
	    return response;
	}
}
