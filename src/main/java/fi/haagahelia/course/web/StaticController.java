package fi.haagahelia.course.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaticController {

    @GetMapping("/")
    public String mostrarPaginaPrincipal() {
        return "index";
    }

    @RequestMapping("/index")
	public String index() {
		return "index";
	}

    @GetMapping("/perfil")
    public String mostrarPaginaPerfil() {
        return "perfil";
    }
}
