package com.fin.love.service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fin.love.repository.image.HobbyPicture;
import com.fin.love.repository.image.HobbyPictureRepository;
import com.fin.love.repository.image.Picture;
import com.fin.love.repository.image.PictureRepository;
import com.fin.love.respository.member.Member;
import com.fin.love.respository.member.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PictureService {

	@Value("${com.example.upload.path}") // application.properties의 변수
	private String uploadPath;

	private final PictureRepository pictureRepository;
	private final HobbyPictureRepository hobbyPictureRepository;
	private final MemberRepository memberRepository;

	// 날짜별로 폴더 생성하는 메서드.
	public String makeFolder() {
		log.info("makeForder()");

		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

		String folderPath = str.replace("/", File.separator);

		// make folder ----
		File uploadPatheFolder = new File(uploadPath, folderPath);

		if (uploadPatheFolder.exists() == false) {
			uploadPatheFolder.mkdirs();
		}

		return folderPath;
	}

	// 업로드시 수정하는 메서드
	@Transactional
	public void pic1SaveImage(String id, String saveImagePathName) {
		log.info("saveImage(UserId = {})", id);
		log.info("path >>>>> " + saveImagePathName);

		Picture pic = pictureRepository.findById(id).orElseThrow();
		log.info("pic >>>>> " + pic);

		pic.pic1Update(saveImagePathName);
	}

	@Transactional
	public void pic2SaveImage(String id, String saveImagePathName) {
		log.info("saveImage(UserId = {})", id);
		log.info("path >>>>> " + saveImagePathName);

		Picture pic = pictureRepository.findById(id).orElseThrow();
		log.info("pic >>>>> " + pic);

		pic.pic2Update(saveImagePathName);
	}

	@Transactional
	public void pic3SaveImage(String id, String saveImagePathName) {
		log.info("saveImage(UserId = {})", id);
		log.info("path >>>>> " + saveImagePathName);

		Picture pic = pictureRepository.findById(id).orElseThrow();
		log.info("pic >>>>> " + pic);

		pic.pic3Update(saveImagePathName);
	}

	@Transactional
	public void hobbyPic1SaveImage(String id, String saveImagePathName) {
		log.info("saveImage(UserId = {})", id);
		log.info("path >>>>> " + saveImagePathName);

		HobbyPicture pic = hobbyPictureRepository.findById(id).orElseThrow();
		log.info("pic >>>>> " + pic);

		pic.hobbyPic1Update(saveImagePathName);
	}

	@Transactional
	public void hobbyPic2SaveImage(String id, String saveImagePathName) {
		log.info("saveImage(UserId = {})", id);
		log.info("path >>>>> " + saveImagePathName);

		HobbyPicture pic = hobbyPictureRepository.findById(id).orElseThrow();
		log.info("pic >>>>> " + pic);

		pic.hobbyPic2Update(saveImagePathName);
	}

	@Transactional
	public void hobbyPic3SaveImage(String id, String saveImagePathName) {
		log.info("saveImage(UserId = {})", id);
		log.info("path >>>>> " + saveImagePathName);

		HobbyPicture pic = hobbyPictureRepository.findById(id).orElseThrow();
		log.info("pic >>>>> " + pic);

		pic.hobbyPic3Update(saveImagePathName);
	}

	// pic1 이미지 리턴
	@Transactional(readOnly = true)
	public String pic1Image(String id) {
		log.info("pic1Image()");

		return pictureRepository.findById(id).orElseThrow().getPic1();
	}

	@Transactional(readOnly = true)
	public Picture baseUploadImage(String userId) {
		log.info("baseUloadImage(userId = {})", userId);

		return pictureRepository.findById(userId).orElseThrow();
	}

	@Transactional(readOnly = true)
	public HobbyPicture hobbyBaseUploadImage(String userId) {
		log.info("baseUloadImage(userId = {})", userId);

		return hobbyPictureRepository.findById(userId).orElseThrow();
	}

	// 새로운 아이디 데이터를 생성.
	public void newMemberPicture(String userId) {
		log.info("newMemberPicture(userId = {})", userId);

		pictureRepository.save(Picture.builder().id(userId).pic1(null).pic2(null).pic3(null).build());
	}

	public void newMemberHobbyPicture(String userId) {
		log.info("newMemberPicture(userId = {})", userId);

		hobbyPictureRepository
				.save(HobbyPicture.builder().id(userId).hobbyPic1(null).hobbyPic2(null).hobbyPic3(null).build());
	}

	// 아이디가 있는지 확인.
	public int checkUserId(String userId) {
		log.info("checkUserId(userId = {})", userId);
		int result = 0;

		Picture pic = pictureRepository.findById(userId).orElse(null);
		log.info("pic >>>> " + pic);

		if (pic == null) {
			result = 1;
		}

		log.info("result =>>>>> " + result);

		return result;
	}

	public int hobbyCheckUserId(String userId) {
		log.info("hobbyCheckUserId(userId = {})", userId);
		int result = 0;

		HobbyPicture pic = hobbyPictureRepository.findById(userId).orElse(null);
		log.info("pic >>>> " + pic);

		if (pic == null) {
			result = 1;
		}

		log.info("result =>>>>> " + result);

		return result;
	}

	// 유저아이디로 데이터 찾기
	public Picture findById(String userId) {
		log.info("findById(userId = {})", userId);

		return pictureRepository.findById(userId).orElseThrow();
	}

	public HobbyPicture hobbyFindById(String userId) {
		log.info("findById(userId = {})", userId);

		return hobbyPictureRepository.findById(userId).orElseThrow();
	}

	// 문자열로 받은 경로 이미지 값을 인코딩
	public String stringToIncoding(String getImage) throws Exception {
		log.info("stringToIncoding={}", getImage);

		byte[] bytes = getImage.getBytes();
		String base64Encoded = Base64.getEncoder().encodeToString(bytes);
		log.info("bytes={}", bytes);
		log.info("base64Encoded={}", base64Encoded);

		return base64Encoded;
	}

	// 출력하는 사진으로 변경시키기
	public String imageChange(String picture) {
		log.info("imageChange(picture = {})", picture);

		if (picture.equals("/images/Adding_a_Person_Image.png")) {
			return picture;
		}

		String result = "/images/uploadImages/";
		result += picture;

		return result;
	}
	
	// 이미지 삭제
	@Transactional
	public void usualPic1Delete(String userId) {
		log.info("usualPic1Delete(userId = {})", userId);
		// 삭제시 기본 이미지 전환을 위한 기본 이미지
		String baseImage = "/images/Adding_a_Person_Image.png";
		
		Picture pic = pictureRepository.findById(userId).orElseThrow();
		
		pic.pic1Update(baseImage);
	}
	
	@Transactional
	public void usualPic2Delete(String userId) {
		log.info("usualPic2Delete(userId = {})", userId);
		// 삭제시 기본 이미지 전환을 위한 기본 이미지
		String baseImage = "/images/Adding_a_Person_Image.png";
		
		Picture pic = pictureRepository.findById(userId).orElseThrow();
		
		pic.pic2Update(baseImage);
	}
	
	@Transactional
	public void usualPic3Delete(String userId) {
		log.info("usualPic3Delete(userId = {})", userId);
		// 삭제시 기본 이미지 전환을 위한 기본 이미지
		String baseImage = "/images/Adding_a_Person_Image.png";
		
		Picture pic = pictureRepository.findById(userId).orElseThrow();
		
		pic.pic3Update(baseImage);
	}
	
	@Transactional
	public void hobbyPic1Delete(String userId) {
		log.info("hobbyPic1Delete(userId = {})", userId);
		// 삭제시 기본 이미지 전환을 위한 기본 이미지
		String baseImage = "/images/Adding_a_Person_Image.png";
		
		HobbyPicture hobbyPic = hobbyPictureRepository.findById(userId).orElseThrow();
		
		hobbyPic.hobbyPic1Update(baseImage);
	}
	
	@Transactional
	public void hobbyPic2Delete(String userId) {
		log.info("hobbyPic2Delete(userId = {})", userId);
		// 삭제시 기본 이미지 전환을 위한 기본 이미지
		String baseImage = "/images/Adding_a_Person_Image.png";
		
		HobbyPicture hobbyPic = hobbyPictureRepository.findById(userId).orElseThrow();
		
		hobbyPic.hobbyPic2Update(baseImage);
	}
	
	@Transactional
	public void hobbyPic3Delete(String userId) {
		log.info("hobbyPic3Delete(userId = {})", userId);
		// 삭제시 기본 이미지 전환을 위한 기본 이미지
		String baseImage = "/images/Adding_a_Person_Image.png";
		
		HobbyPicture hobbyPic = hobbyPictureRepository.findById(userId).orElseThrow();
		
		hobbyPic.hobbyPic3Update(baseImage);
	}

}
