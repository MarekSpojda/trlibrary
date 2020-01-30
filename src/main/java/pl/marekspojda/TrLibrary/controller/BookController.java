package pl.marekspojda.TrLibrary.controller;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.marekspojda.TrLibrary.dto.BookToRentDTO;
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
		StringBuilder allBooksAsHtml = new StringBuilder(
				"List of all books:<br><table><tr><th>Title</th><th>Genre</th><th>Release</th><th>Student</th></tr>");

		List<Book> allBooks = bookRepository.findAllOrderByTitleAsc();
		for (Book book : allBooks) {
			allBooksAsHtml.append("<tr><td>" + book.getTitle() + "</td>").append("<td>" + book.getGenre() + "</td>")
					.append("<td>" + book.getReleaseDate().toString().substring(0, 10)).append("</td>");
			allBooksAsHtml.append(getBookOwnerInfo(book));
			allBooksAsHtml.append("</tr>");
		}

		return allBooksAsHtml.toString() + "</table>";
	}

	@Secured({ "ROLE_USER" })
	@PostMapping(path = "/listavailablebooks", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String listAvailableBooksPost() {
		StringBuilder availableBooksAsHtml = new StringBuilder(
				"List of available books:<br><table><tr><th>Title</th><th>Genre</th><th>Release</th><th>(rent book)</th></tr>");

		List<Book> books = bookRepository.findAllAvailableOrderByTitleAsc();
		for (Book book : books) {
			availableBooksAsHtml.append("<tr><td>" + book.getTitle() + "</td>")
					.append("<td>" + book.getGenre() + "</td>")
					.append("<td>" + book.getReleaseDate().toString().substring(0, 10)).append("</td>")
					.append(makeRentButtonCode(book));
			availableBooksAsHtml.append("</tr>");
		}

		return availableBooksAsHtml.toString() + "</table>";
	}

	@Secured({ "ROLE_USER" })
	@RequestMapping(path = "/rentbook/{bookId}", method = RequestMethod.POST)
	public String rentBookPost(@PathVariable String bookId, Model model) {
		model.addAttribute("rentBookId", bookId);
		model.addAttribute("rentBookName", bookRepository.findById(Long.parseLong(bookId)).get().getTitle());
		model.addAttribute("rentListOfStudents", userRepository.findAllOrderBySurname());
		return "rent";
	}

	// TODO cleanup library done to here

	@Secured({ "ROLE_USER" })
	@PostMapping(path = "/rentconfirm/{bookId}/{userId}", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String rentConfirmPost(@PathVariable String bookId, @PathVariable String userId) {
		User student = userRepository.findById(Long.parseLong(userId)).get();
		Book book = bookRepository.findById(Long.parseLong(bookId)).get();
		book.setAvailable(false);
		book.setStudentId(student.getUserId());
		bookRepository.save(book);
		student.getBooks().add(book);
		userRepository.save(student);
		return "Book rented to user";
	}

	@Secured({ "ROLE_USER" })
	@PostMapping(path = "/assignbooktostudent/{bookId}/{userId}", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String assignBookToStudentPost(@PathVariable String bookId, @PathVariable String userId) {
		User student = userRepository.findById(Long.parseLong(userId)).get();
		Book book = bookRepository.findById(Long.parseLong(bookId)).get();
		book.setAvailable(false);
		book.setStudentId(student.getUserId());
		bookRepository.save(book);
		student.getBooks().add(book);
		userRepository.save(student);
		return "Book rented to user";
	}

	@Secured({ "ROLE_USER" })
	@PostMapping(path = "/returnbook/{bookId}/{userId}", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String retunrBookPost(@PathVariable String bookId, @PathVariable String userId) {
		User student = userRepository.findById(Long.parseLong(userId)).get();
		Book book = bookRepository.findById(Long.parseLong(bookId)).get();
		student.getBooks().remove(book);
		userRepository.save(student);
		book.setAvailable(true);
		book.setStudentId(null);
		bookRepository.save(book);
		return "Book returned";
	}

	// Provides info of student who rented a book
	private String getBookOwnerInfo(Book book) {
		if (book.getStudentId() != null) {
			User user = userRepository.findById(book.getStudentId()).get();
			return "<td>" + user.getName() + " " + user.getSurname() + ", birthdate: "
					+ user.getBirthDate().toString().substring(0, 10) + "</td>";
		}

		return "<td>(book available)</td>";
	}

	// Provide code of button to rent a book
	private String makeRentButtonCode(Book book) {
		return "<td><button type=\"button\" class=\"rentbook\" booktorentid=\"" + book.getBookId()
				+ "\">Rent book</button></td>";
	}

	@GetMapping(path = "/rent2", produces = "text/html; charset=UTF-8")
	public String rent2Get() {
		return "rent3";
	}

	@PostMapping(path = "/returnrent3", produces = "text/html; charset=UTF-8")
	public String returnRent3Post() {
		return "rent2";
	}

	@PostMapping(path = "/rent2", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String rent2Post(@ModelAttribute("bookToRentDTO") BookToRentDTO bookToRentDTO) {
		return "userId: " + bookToRentDTO.getUserId() + ", bookId: " + bookToRentDTO.getBookId();
	}

	@ModelAttribute("bookToRentDTO")
	public BookToRentDTO getBookToRentDTO() {
		return new BookToRentDTO();
	}
}