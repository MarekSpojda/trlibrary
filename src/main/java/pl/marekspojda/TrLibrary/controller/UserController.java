package pl.marekspojda.TrLibrary.controller;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.marekspojda.TrLibrary.entity.Book;
import pl.marekspojda.TrLibrary.entity.User;
import pl.marekspojda.TrLibrary.repository.UserRepository;

@Controller
public class UserController {
	private final UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Secured({ "ROLE_USER" })
	@PostMapping(path = "/listallstudents", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String listAllStudentsPost() {
		StringBuilder stringBuilder = new StringBuilder(
				"List of all students:<br><table><tr><th>Name</th><th>Surname</th><th>Birthdate</th><th>E-mail</th><th></th></tr>");

		List<User> users = userRepository.findAll();
		for (User user : users) {
			stringBuilder.append("<tr><td>" + user.getName() + "</td>").append("<td>" + user.getSurname() + "</td>")
					.append("<td>" + user.getBirthDate().toString().substring(0, 10) + "</td>")
					.append("<td>" + user.getEmail() + "</td>").append(makeStudensBookButton(user)).append("</tr>");
		}

		return stringBuilder.toString() + "</table>";
	}

	@Secured({ "ROLE_USER" })
	@RequestMapping(path = "/student/{id}", produces = "text/html; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String listBooksRentedByStudentPost(@PathVariable String id) {
		User user = userRepository.findById(Long.parseLong(id)).get();
		StringBuilder stringBuilder = new StringBuilder("Books of " + user.getName() + " " + user.getSurname()
				+ ":<br><table><tr><th>Title</th><th>Genre</th><th>Release</th></tr>");
		for (Book book : user.getBooks()) {
			stringBuilder.append("<tr><td>" + book.getTitle() + "</td>").append("<td>" + book.getGenre() + "</td>")
					.append("<td>" + book.getReleaseDate().toString().substring(0, 10)).append("</td>")
					.append("<td><button type=\"button\" class=\"returnbook\" bookId=\"" + book.getBookId())
					.append("\" userId=\"" + user.getUserId() + "\">Return book</button></td>");
			stringBuilder.append("</tr>");
		}

		return stringBuilder.toString() + "</table>";
	}

	// Provides code of button to list student's books
	private String makeStudensBookButton(User user) {
		return "<td><button type=\"button\" class=\"liststudentbooksbutton\" studentid=\"" + user.getUserId()
				+ "\">Books of student</button></td>";
	}
}