package kr.co.wikibook.gallery.account.medel;

import lombok.Getter;

@Getter
public class AccountLoginReq {
//    실무에선 아이디만 적는다, 이렇게 적게되면 db쿼리문에서 뭐가 틀려서 튜플이 뜨지않는지 알 수 없기 때문

    private String loginId;
    private String loginPw;
}
