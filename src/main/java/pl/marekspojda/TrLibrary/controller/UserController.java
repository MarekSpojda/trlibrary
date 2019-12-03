package pl.marekspojda.TrLibrary.controller;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.marekspojda.TrLibrary.entity.User;
import pl.marekspojda.TrLibrary.repository.UserRepository;

@Controller
public class UserController {
	private final UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Secured({ "ROLE_ADMIN" })
	@PostMapping(path = "/listallstudents", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String registerPost() {
		StringBuilder stringBuilder = new StringBuilder(
				"List of all students:<br><table><tr><th>Name</th><th>Surname</th><th>Birthdate</th><th>E-mail</th><th></th></tr>");

		List<User> users = userRepository.findAll();
		for (User user : users) {
			stringBuilder.append("<tr><td>" + user.getName() + "</td>").append("<td>" + user.getSurname() + "</td>")
					.append("<td>" + user.getBirthDate().toString().substring(0, 10) + "</td>")
					.append("<td>" + user.getEmail() + "</td>").append("<td><button type=\"button\" studentid=\""
							+ user.getUserId() + "\">Books of student</button></td>")
					.append("</tr>");
		}

		return stringBuilder.toString() + "</table>";
	}
}