package com.douzone.mysite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVO;

@RestController("userControllerApi") // 이거안하면 이미있는 UserController랑 중복되기때문에 DispatcherServlet -> Servlet.init()이 에러띄움 그래서 이름 직접 정해줌
@RequestMapping("/user/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@GetMapping("/checkemail")
	public Object checkEmail(@RequestParam(value="email", required=true, defaultValue="") String email) {
		UserVO vo = userService.getUser(email);
		return JsonResult.success(vo != null);
	}
}







//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put("result", "success"); // 통신성공여부
//		result.put("exist", vo != null);