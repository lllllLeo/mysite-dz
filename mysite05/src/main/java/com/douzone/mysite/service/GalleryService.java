package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GalleryRepository;
import com.douzone.mysite.vo.GalleryVO;

@Service
public class GalleryService {

	@Autowired
	private GalleryRepository galleryRepository;

	public void uploadImage(String url) {
		galleryRepository.uploadImage(url);
		
	}

	public List<GalleryVO> getAllImages() {
		return galleryRepository.getAllImages();
	}

	public void delete(Long no) {
		galleryRepository.delete(no);
	}
}
