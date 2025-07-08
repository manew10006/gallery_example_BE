package kr.co.wikibook.gallery.account;

import kr.co.wikibook.gallery.account.medel.AccountJoinReq;
import kr.co.wikibook.gallery.account.medel.AccountLoginReq;
import kr.co.wikibook.gallery.account.medel.AccountLoginRes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
    AccountLoginRes findByLoginId(AccountLoginReq r);
    int save(AccountJoinReq r);


}
