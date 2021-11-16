package tw.com.fcb.mimosa.examples.gettingstarted.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.fcb.mimosa.ext.security.auth.ValidateRequest;
import tw.com.fcb.mimosa.ext.security.auth.ValidateResponse;

@RestController
@RequestMapping("/backend-auth")
public class BackendAuthController {
	
	@PostMapping("/validateToken")
	public ValidateResponse validate(@RequestBody ValidateRequest request) {
		if(request.getToken().length() < 3) {
			return ValidateResponse.builder()
					.custId("")
					.loginIp("")
					.loginway("NB")
					.fnct("F0101")
					.rtnCode("9999")
					.rtnMsg("error")
					.jsonObj("")
					.build();
		}
		
		return ValidateResponse.builder()
				.custId("A123456789")
				.loginIp("127.0.0.1")
				.loginway("NB")
				.fnct("F0101")
				.rtnCode("0000")
				.rtnMsg("OK")
				.jsonObj("")
				.build();
	}
}
