package hello;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testCon {
	@GetMapping("/templates")
	public String test(HttpServletRequest request) {
		request.setAttribute("myname", "YWB");
		return "index";
	}
	@RequestMapping("/love")
	public String testTow(HttpServletRequest request){
		request.setAttribute("mylove", "HLY");
		return "index";
	}
}
