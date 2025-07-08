package kr.co.wikibook.gallery.account.medel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class AccountLoginRes {
    private int id;
    
    //json생성 시 pw는 빠진다
    @JsonIgnore 
    private String loginPw;

}