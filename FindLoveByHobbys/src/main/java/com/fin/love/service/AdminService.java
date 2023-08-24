package com.fin.love.service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.fin.love.dto.facechat.FileMemoryDto;
import com.fin.love.dto.facechat.ReportListDto;
import com.fin.love.dto.facechat.ReportReadDto;
import com.fin.love.repository.facechat.FaceChatRepository;
import com.fin.love.repository.facechat.FaceReportRepository;
import com.fin.love.repository.facechat.Speakchat;
import com.fin.love.respository.member.Member;
import com.fin.love.respository.member.MemberRepository;
import com.fin.love.respository.member.Role;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminService {

	@Autowired
	private FaceReportRepository facerepository;

	@Autowired
	private MemberRepository memberrepository;
	
	public List<ReportReadDto> readlist() {
		log.info("read all reportlist");

		List<Speakchat> list3 = facerepository.findAllByOrderByCreatedTimeDesc();
		List<ReportReadDto> list = new ArrayList<>();

		for (Speakchat x : list3) {

			try {
				ReportReadDto dto = ReportReadDto.fromEntity(x);
				FileMemoryDto dtos = FileMemoryDto.getInstance();
				Map<Long, String> map = dtos.getFilemap();
				if (map.get(x.getId()) != null) {
					log.info("기존 파일 있음");
					dto.resourceaddon(map.get(x.getId()));
					list.add(dto);
					
				} else {
					String audio = "";
					if(x.getChatfile()==null) {
					
						audio = encodeAudioToBase64("C:\\EPI\\record-sample.wav");
						
					} else {
					
					audio = encodeAudioToBase64(x.getChatfile());
					}
					map.put(x.getId(), audio);
					dtos.setFilemap(map);
					dto.resourceaddon(audio);
					list.add(dto);
				}
			} catch (Exception e) {

				e.printStackTrace();
			}

		}

		// TODO Auto-generated method stub
		return list;
	}

	public List<ReportReadDto> readchecklist() {
		log.info("read need reportlist");

		List<Speakchat> list3 = facerepository.findByReportOrderByCreatedTimeDesc(1);
		List<ReportReadDto> list = new ArrayList<>();

		for (Speakchat x : list3) {

			try {
				ReportReadDto dto = ReportReadDto.fromEntity(x);
				FileMemoryDto dtos = FileMemoryDto.getInstance();
				Map<Long, String> map = dtos.getFilemap();
				if (map.get(x.getId()) != null) {
					log.info("기존 파일 있음");
					dto.resourceaddon(map.get(x.getId()));
					list.add(dto);
					
				} else {
					String audio = "";
					if(x.getChatfile()==null) {
					
						audio = encodeAudioToBase64("C:\\EPI\\record-sample.wav");
						
					} else {
					
					audio = encodeAudioToBase64(x.getChatfile());
					}
					map.put(x.getId(), audio);
					dtos.setFilemap(map);
					dto.resourceaddon(audio);
					list.add(dto);
				}
			} catch (Exception e) {

				e.printStackTrace();
			}

		}

		return list;

	}

	public List<ReportReadDto> readendlist() {
		log.info("read end reportlist");

		List<Speakchat> list3 = facerepository.findByReportOrderByCreatedTimeDesc(2);
		List<ReportReadDto> list = new ArrayList<>();

		for (Speakchat x : list3) {

			try {
				ReportReadDto dto = ReportReadDto.fromEntity(x);
				FileMemoryDto dtos = FileMemoryDto.getInstance();
				Map<Long, String> map = dtos.getFilemap();
				if (map.get(x.getId()) != null) {
					log.info("기존 파일 있음");
					dto.resourceaddon(map.get(x.getId()));
					list.add(dto);
					
				} else {
					String audio = "";
					if(x.getChatfile()==null) {
					
						audio = encodeAudioToBase64("C:\\EPI\\record-sample.wav");
						
					} else {
					
					audio = encodeAudioToBase64(x.getChatfile());
					}
					map.put(x.getId(), audio);
					dtos.setFilemap(map);
					dto.resourceaddon(audio);
					list.add(dto);
				}
			} catch (Exception e) {

				e.printStackTrace();
			}

		}

		// TODO Auto-generated method stub
		return list;

	}

	public void update(ReportListDto dto) {
		
		//TODO speakChat에서 신고당한 사람 불러와서 userinfo의 role 값 바꿔줘야됨. 지금은 몇번이 Ban 번호인지 몰라서 놔둠.
		
		long id = dto.getUpdatenum();

		Speakchat entity = facerepository.findById(id).orElseThrow();
		
		Member member = memberrepository.findById(entity.getRespondent()).orElseThrow();
		
		
		if(dto.getStatus()==1) {
			member.updateRole(Role.RIP_USER);
			memberrepository.save(member);
		
		} else {
			
			member.updateRole(Role.USER);
			memberrepository.save(member);
			
		}
		entity.updateStatus(dto.getStatus());

		facerepository.save(entity);
		
		log.info("update={}", dto);
	}

	public String encodeAudioToBase64(String filePath) throws Exception {
		Path audioPath = Path.of(filePath);
		String encodedAudio = "";
		try (InputStream inputStream = new FileInputStream(audioPath.toString())) {
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int bytesRead;
			byte[] data = new byte[16 * 1024];

			// 데이터를 읽어서 버퍼에 담기
			while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, bytesRead);
			}

			// 인코딩된 데이터를 문자열로 반환
			byte[] audioData = buffer.toByteArray();
			encodedAudio = Base64.getEncoder().encodeToString(audioData);
			return encodedAudio;
		} catch (Exception e) {

			e.printStackTrace();

		}
		return encodedAudio;
//		byte[] audioData = Files.readAllBytes(audioPath);
//		String encodedAudio = Base64.getEncoder().encodeToString(audioData);
//		return encodedAudio;
	}

}
