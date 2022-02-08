package com.poscoict.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.poscoict.jblog.exception.FileUploadException;
import com.poscoict.jblog.repository.BlogRepository;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.UserVo;

@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ServletContext servletContext;
	
	public boolean join(UserVo userVo) {
		return blogRepository.insert(userVo);
	}
	
	public BlogVo getBlog(String id) {
		return blogRepository.findById(id);		
	}
	
	public boolean update(BlogVo blogVo, MultipartFile newLogo) {
		String SAVE_PATH = "/upload-images";
		String URL_BASE = "/assets/logo";
		String url = null;
		
		try {
			if (newLogo.isEmpty()) {
				url = blogRepository.findById(blogVo.getUserId()).getLogo();
			} else {
				String originFileName = newLogo.getOriginalFilename();
				String extName = originFileName.substring(originFileName.lastIndexOf("."));
				String saveFileName = generateSaveFileName(extName);
				
				byte[] data = newLogo.getBytes();
				OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
				os.write(data);
				os.close();
				
				url = URL_BASE + "/" + saveFileName;
			} 		
		} catch (IOException ex ) {
			throw new FileUploadException("file upload error : " + ex);
		}
		
		blogVo.setLogo(url);
		servletContext.setAttribute("blogVo", blogVo);
		
		return blogRepository.update(blogVo);
	}
	
	private String generateSaveFileName(String extName) {
		String filename = "";
		
		Calendar calendar = Calendar.getInstance();
		
		filename += calendar.get(calendar.YEAR);
		filename += calendar.get(calendar.MONTH);
		filename += calendar.get(calendar.DATE);
		filename += calendar.get(calendar.HOUR);
		filename += calendar.get(calendar.MINUTE);
		filename += calendar.get(calendar.SECOND);
		filename += calendar.get(calendar.MILLISECOND);
		filename += extName;
		
		return filename;
	}

}
