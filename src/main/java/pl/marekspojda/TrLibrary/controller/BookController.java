package pl.marekspojda.TrLibrary.controller;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.marekspojda.TrLibrary.entity.Book;
import pl.marekspojda.TrLibrary.entity.User;
import pl.marekspojda.TrLibrary.repository.BookRepository;
import pl.marekspojda.TrLibrary.repository.UserRepository;

@Controller
public class BookController {
	private final BookRepository bookRepository;
	private final UserRepository userRepository;

	public BookController(BookRepository bookRepository, UserRepository userRepository) {
		this.bookRepository = bookRepository;
		this.userRepository = userRepository;
	}

	@Secured({ "ROLE_USER" })
	@PostMapping(path = "/listallbooks", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String listAllBooksPost() {
		StringBuilder stringBuilder = new StringBuilder(
				"List of all books:<br><table><tr><th>Title</th><th>Genre</th><th>Release</th><th>Student</th></tr>");

		List<Book> books = bookRepository.findAllOrderByTitleAsc();
		for (Book book : books) {
			stringBuilder.append("<tr><td>" + book.getTitle() + "</td>").append("<td>" + book.getGenre() + "</td>")
					.append("<td>" + book.getReleaseDate().toString().substring(0, 10)).append("</td>");
			stringBuilder.append(getBookOwnerInfo(book));
			stringBuilder.append("</tr>");
		}

		return stringBuilder.toString() + "</table>";
	}

	@Secured({ "ROLE_USER" })
	@PostMapping(path = "/listavailablebooks", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String listAvailableBooksPost() {
		StringBuilder stringBuilder = new StringBuilder(
				"List of available books:<br><table><tr><th>Title</th><th>Genre</th><th>Release</th></tr>");

		List<Book> books = bookRepository.findAllAvailableOrderByTitleAsc();
		for (Book book : books) {
			stringBuilder.append("<tr><td>" + book.getTitle() + "</td>").append("<td>" + book.getGenre() + "</td>")
					.append("<td>" + book.getReleaseDate().toString().substring(0, 10)).append("</td>");
			stringBuilder.append("</tr>");
		}

		return stringBuilder.toString() + "</table>";
	}

	// Provides info of student who rented a book
	private String getBookOwnerInfo(Book book) {
		if (book.getStudentId() != null) {
			User user = userRepository.findById(book.getStudentId()).get();
			return "td" + user.getName() + " " + user.getSurname() + ", birthdate: "
					+ user.getBirthDate().toString().substring(0, 10) + "</td>";
		}

		return "<td></td>";
	}
}