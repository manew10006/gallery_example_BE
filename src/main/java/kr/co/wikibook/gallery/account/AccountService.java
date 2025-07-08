package kr.co.wikibook.gallery.account;

import ch.qos.logback.core.joran.spi.HttpUtil;
import kr.co.wikibook.gallery.account.medel.AccountJoinReq;
import kr.co.wikibook.gallery.account.medel.AccountLoginReq;
import kr.co.wikibook.gallery.account.medel.AccountLoginRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountMapper accountMapper;

    public int join(AccountJoinReq r){
        //암호화가 된 비밀번호
        String hashedPw = BCrypt.hashpw(r.getLoginPw(), BCrypt.gensalt());

        //암호화가 된 비밀번호를 갖는 AccountJoinReq 객체를 만들기 (아이디, 이름 갖고있는)
        //박스갈이~
        AccountJoinReq changeReq = new AccountJoinReq(r.getName(), r.getLoginId() ,hashedPw);
        return accountMapper.save(changeReq);


    }

        public AccountLoginRes login(AccountLoginReq req){
            AccountLoginRes res = accountMapper.findByLoginId(req);
            //비밀번호 체크 (암호화가되지않은것, 암호화과된것) 불린값리턴
            if(res == null || !BCrypt.checkpw(req.getLoginPw(), res.getLoginPw())){
                return null; //비밀번호가 다르면 null 반환
            }
            return res;

        }

}
