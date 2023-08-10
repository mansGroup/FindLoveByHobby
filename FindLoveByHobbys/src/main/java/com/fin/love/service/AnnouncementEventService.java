package com.fin.love.service;

import org.springframework.stereotype.Service;

import com.fin.love.dto.announcementEvent.AnnouncementEventDto;
import com.fin.love.repository.announcementEvent.AnnouncementEvent;
import com.fin.love.repository.announcementEvent.AnnouncementEventRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AnnouncementEventService {
	
	private final AnnouncementEventRepository announcementEventRepository;
	
		public AnnouncementEvent create(AnnouncementEventDto dto) {
			log.info("create(dto={})", dto);
			
			// DTO를 Entity객체로 변환:
			AnnouncementEvent entity = dto.toEntity();
			log.info("entity={}", entity);
			
			// DB 테이블에 저장(insert)
			announcementEventRepository.save(entity);
			log.info("entity={}", entity);
			
			return entity;
			
		}
}
