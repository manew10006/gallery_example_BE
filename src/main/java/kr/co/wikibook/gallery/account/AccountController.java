package kr.co.wikibook.gallery.account;

import ch.qos.logback.core.util.StringUtil;
import jakarta.servlet.http.HttpServletRequest;
import kr.co.wikibook.gallery.account.etc.AccountConstants;
import kr.co.wikibook.gallery.account.medel.AccountJoinReq;
import kr.co.wikibook.gallery.account.medel.AccountLoginReq;
import kr.co.wikibook.gallery.account.medel.AccountLoginRes;
import kr.co.wikibook.gallery.common.util.HttpUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody AccountJoinReq req){
        //java에서 제공하는 메소드 , 문자열이 있으면 true 없으면 false
        if(!StringUtils.hasLength(req.getName())
                || !StringUtils.hasLength(req.getLoginId())
                || !StringUtils.hasLength(req.getLoginPw())){
            //요청이 잘못됫다고하는 400번대 클라이언트에러메세지 리턴
            return ResponseEntity.badRequest().build(); //state:400

        }

        int result = accountService.join(req);
        return ResponseEntity.ok(result); //state : 200
    }

    //HttpServletRequest이 요청에 관한 모든것을 한다 (Spring이 제공)
    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest httpReq, @RequestBody AccountLoginReq req){
        AccountLoginRes result = accountService.login(req);
        if(result == null){
            return ResponseEntity.notFound().build();
        }

        //세션처리
        HttpUtils.setSession(httpReq , AccountConstants.MEMBER_ID_NAME , result.getId());

        return ResponseEntity.ok(result);
    }

    @GetMapping("/check")
    public ResponseEntity<?> check(HttpServletRequest httpReq){
        //타입이 obj타입이라 Integer로 강제형변환한다
        Integer id = (Integer)HttpUtils.getSessionValue(httpReq , AccountConstants.MEMBER_ID_NAME);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest httpReq){
        HttpUtils.removeSessionValue(httpReq , AccountConstants.MEMBER_ID_NAME);
        return ResponseEntity.ok("😥");
    }



}
