package org.ppiyung.ppiyung.common.entity;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadEntity {

	private String filename;
	private MultipartFile file;
}
