package com.fin.love.web;

import com.fin.love.repository.assessment.Assessment;
import com.fin.love.repository.hobby.Hobby;
import com.fin.love.repository.image.HobbyPicture;
import com.fin.love.repository.image.Picture;
import com.fin.love.repository.profile.Profile;
import com.fin.love.respository.member.Member;
import com.fin.love.service.LikeService;
import com.fin.love.service.MatchingDetailService;
import com.fin.love.service.MatchingService;
import com.fin.love.service.PictureService;
import com.fin.love.service.chatting.ChattingRoomService;
import com.fin.love.service.note.NoteNumberService;
import com.fin.love.service.note.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/matching")
@Controller
public class MatchingConnectionController {

    private final LikeService likeService;
    private final NoteService noteService;
    private final NoteNumberService noteNumberService;
    private final ChattingRoomService chattingRoomService;
    private final PictureService pictureService;
    private final MatchingService matchingService;
    private final MatchingDetailService matchingDetailService;

    private String user = "user2";  // TODO 세션연결

    @GetMapping("/profile/{senderId}")
    public String conectionDetail(@PathVariable String senderId, Model model) {
        // 프로필 사진
        Picture member1Pic = pictureService.findById(senderId);

        String member1UsualPic1 = matchingService.imageChange(member1Pic.getPic1());
        String member1UsualPic2 = matchingService.imageChange(member1Pic.getPic2());
        String member1UsualPic3 = matchingService.imageChange(member1Pic.getPic3());

        model.addAttribute("usualPic1", member1UsualPic1);
        model.addAttribute("usualPic2", member1UsualPic2);
        model.addAttribute("usualPic3", member1UsualPic3);

        // 취미 사진
        HobbyPicture member1HobbyPic = pictureService.hobbyFindById(senderId);

        String member1HobbyPic1 = matchingService.imageChange(member1HobbyPic.getHobbyPic1());
        String member1HobbyPic2 = matchingService.imageChange(member1HobbyPic.getHobbyPic2());
        String member1HobbyPic3 = matchingService.imageChange(member1HobbyPic.getHobbyPic3());

        model.addAttribute("hobbyPic1", member1HobbyPic1);
        model.addAttribute("hobbyPic2", member1HobbyPic2);
        model.addAttribute("hobbyPic3", member1HobbyPic3);

//===============================이지미 관련 코드==========================================

        // 취미 목록 가져오기
        List<Hobby> hobbies = matchingDetailService.getAllHobbies();
        model.addAttribute("hobbies", hobbies);
        log.info("hobbies = {}", hobbies);

        // id에 해당하는 Assessment 가져오기
        Assessment assessment = matchingDetailService.getUserAssessment(senderId);
        model.addAttribute("assessment", assessment);
        log.info("assanswer = {}", assessment);

        // id에 해당하는 Profile 가져오기
        Profile profile = matchingDetailService.getUserProfile(senderId);
        model.addAttribute("profile", profile);
        log.info("proanswer = {}", profile);

        // 프로필 테이블의 직업의 번호를 조회하여 jobs테이블의 job이름을 가져옴
        String userJob = matchingDetailService.getUserJobName(profile.getUserJob());
        model.addAttribute("job", userJob);
        log.info("userJob = {}", userJob);

        // 프로필 테이블의 종교 번호를 조회하여 종교 테이블에서 종교 이름 가져옴
        String userReligion = matchingDetailService.getUserReligionName(profile.getUserReligion());
        model.addAttribute("religion", userReligion);
        log.info("userReligion = {}", userReligion);

        // 프로필 테이블에서 학력 번호를 조회하여 학력 데이블의 학력 이름을 가져옴.
        String userAcademic = matchingDetailService.getUserAcademicName(profile.getUserAcademic());
        model.addAttribute("academic", userAcademic);
        log.info("userAcademic = {}", userAcademic);

        //프로필 테이블에서 키 번호를 조회하여 키 데이블의 키 이름을 가져옴.
        String userHeight = matchingDetailService.getUserHeightName(profile.getUserHeight());
        model.addAttribute("height", userHeight);
        log.info("userHeight = {}", userHeight);

        // 프로필 테이블에서 나이 번호를 조회하여 나이 데이블의 나이 이름을 가져옴.
        String userAge = matchingDetailService.getUserAgeName(profile.getUserAge());
        model.addAttribute("age", userAge);
        log.info("userAge = {}", userAge);

        // 프로필 테이블에서 음주 번호를 조회하여 음주 테이블에사 음주 이름을 가져옴.
        String userDrinks = matchingDetailService.getUserDrinksName(profile.getUserDrinks());
        model.addAttribute("drinks", userDrinks);
        log.info("userDrinks = {}", userDrinks);

        // 프로필 테이블에서 흡연 번호를 조회하여 흡연 테이블에사 흡연 이름을 가져옴.
        String userSmoke = matchingDetailService.getUserSmokerName(profile.getUserSmoker());
        model.addAttribute("smoke", userSmoke);
        log.info("userSmoke = {}", userSmoke);

        // id에 해당하는 UserHobbies 가져오기
        List<String> userHobbies = matchingDetailService.getUserHobbies(senderId);
        model.addAttribute("userHobbies", userHobbies);
        log.info("userHobbies = {}", userHobbies);

        // id에 해당하는 Member 정보 가져오기
        Member member = matchingDetailService.getUserinfo(senderId);
        model.addAttribute("member", member);
        log.info("memanswer = {}", member);

        model.addAttribute("id", senderId);

        // 매칭 상세 정보 페이지로 이동
        return "/matching/matchingConnectionDetail";
    }

    @GetMapping("/connected/{senderId}")
    public String connected(@PathVariable String senderId) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userid = authentication.getName();
        // LIKES 테이블 whether값 0 -> 1
        likeService.chageWhether(senderId, userid, 1);

        // 매칭되어 알림이 불필요하여 삭제
        String type = "likes";
        noteService.deleteNote(senderId, userid, type);

        // 채팅방 오픈
        chattingRoomService.makeChattingRoomm(senderId, userid);

        // 상대방에게 연결됨을 쪽지로 알림
        noteService.noticeConnected(senderId, userid);

        // 상대방 noteCount 올림
        noteNumberService.upNoteCount(senderId);

        return "redirect:/chat/chat";
    }

    @GetMapping("/disconnected/{senderId}")
    public String disconnected(@PathVariable String senderId) {
    	 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         String userid = authentication.getName();
        // LIKES 테이블 whether값 0 -> 2로 바꾸기
        likeService.chageWhether(senderId, userid, 2);

        // sender알람 Notecount 올리기
        noteNumberService.upNoteCount(senderId);
        return "redirect:/matching/matchingList/"+userid;
    }
}
