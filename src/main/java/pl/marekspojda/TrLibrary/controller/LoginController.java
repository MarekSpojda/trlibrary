package pl.marekspojda.TrLibrary.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.marekspojda.TrLibrary.dto.BookToRentDTO;

@Controller
public class LoginController implements ErrorController {
	private static final String PATH = "/error";

	@RequestMapping(path = "/login", produces = "text/html; charset=UTF-8", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(path = "/admin", produces = "text/html; charset=UTF-8", method = RequestMethod.GET)
	public String allowedToAdminsOnly(HttpServletRequest request) {
			return "admin";
	}

	@Secured({ "ROLE_USER" })
	@RequestMapping(path = "/user", produces = "text/html; charset=UTF-8", method = RequestMethod.GET)
	public String allowedToUsersOnly(HttpServletRequest request, Model model) {
		model.addAttribute("bookToRentDTO", new BookToRentDTO());
			return "user";
	}

	@Secured({ "ROLE_STUDENT" })
	@RequestMapping(path = "/student", produces = "text/html; charset=UTF-8", method = RequestMethod.GET)
	public String allowedToStudentsOnly(HttpServletRequest request) {
			return "student";
	}

	@RequestMapping(value = PATH)
	public String error() {
		return "redirect:/";
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}
}