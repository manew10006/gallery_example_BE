package kr.co.wikibook.gallery.account.medel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//회원가입 시 받아야할 데이터
@AllArgsConstructor //이후 비밀번호 암호화된 것 따로 만들기위해서 생성자를 통해 ㅇㅇ..?
// @NoArgsConstructor //기본생성자 리플렉션뭐시기 되려면, 기본생성자에 넣어야해서 (근데위험)
@Getter
public class AccountJoinReq {
    private String name;
    private String loginId;
    private String loginPw;

}
