package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloCon {
	@Autowired
	String world;
	@RequestMapping(value="/hello")
	@ResponseBody
	String test(){
		return world;
	}
}
