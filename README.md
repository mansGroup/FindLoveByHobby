# FindLoveByHobby
Final Project

아이티윌 자바 140기 과정

Final 팀 프로젝트

팀장 : 김다훈

팀원 : 김도현, 유다한, 이찬희, 위태욱, 지대한

주제 : 취미로 매칭되는 소개팅 사이트

개발 환경

언어 : JAVA, Pyton

통합 개발 환경 : Eclipse, Colab

DB : Oracle

server : Tomcat(Boot 내장형)

프론트 : HTML/CSS/JS, BootStrap

기타 기술 : WebSocket, WebRTC, ML, DA

=================================== DB 새로운 계정 생성하기 ===============================================

alter session set "_ORACLE_SCRIPT"=true;

create user skott identified by tiger;

alter user skott account unlock;

grant create session to skott;

grant connect,resource,dba to skott;

===========================================================================================================
