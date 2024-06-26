package com.study.domain.file;

import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class FileService {
	
	private final FileMapper fileMapper;
	
	// 파일 정보 저장
	@Transactional
	public void saveFiles(final Long postId, final List<FileRequest> files) {
		
		if (CollectionUtils.isEmpty(files)) {
			return;
		}
		
		for (FileRequest file : files) {
			file.setPostId(postId);
		}
		
		fileMapper.saveAll(files);
		
	}
	
	// 파일 리스트 조회 -> 전체적
	public List<FileResponse> findAllFileByPostId(final Long postId) {
		return fileMapper.findAllByPostId(postId);
	}
	
	// 파일 리스트 조회 -> 게시글에서의 파일 리스트
	public List<FileResponse> findAllFileByIds(final List<Long> ids) {
		
		if (CollectionUtils.isEmpty(ids)) {
			return Collections.emptyList();
		}
		
		return fileMapper.findAllByIds(ids);
		
	}
	
	// 파일 삭제
	@Transactional
	public void deleteAllFileByIds(final List<Long> ids) {
		
		if (CollectionUtils.isEmpty(ids)) {
			return ;
		}
		fileMapper.deleteAllByIds(ids);
		
	}
	
	// 파일 상세정보 조회
	public FileResponse findFileById(final Long id) {
		return fileMapper.findById(id);
	}

}
