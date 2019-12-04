package pl.marekspojda.TrLibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pl.marekspojda.TrLibrary.dto.UserDTO;
import pl.marekspojda.TrLibrary.entity.User;
import pl.marekspojda.TrLibrary.repository.RoleRepository;
import pl.marekspojda.TrLibrary.repository.UserRepository;

@Controller
public class RegisterController {
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	public RegisterController(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@GetMapping(path = "/register", produces = "text/html; charset=UTF-8")
	public String registerGet() {
		return "register";
	}

	@PostMapping(path = "/register", produces = "text/html; charset=UTF-8")
	public String registerPost(@ModelAttribute("userDTO") UserDTO userDTO) {
		boolean emailExist = userRepository.existsByEmail(userDTO.getEmail());
		if (emailExist || !userDTO.getPassword().equals(userDTO.getPassword2())
				|| userRepository.findUserByNameSurnameBirthdateCustom(userDTO.getName(), userDTO.getSurname(),
						userDTO.getBirthDate()) != null) {
			return "redirect:/register";
		}

		User userToDatabase = new User(userDTO, roleRepository);
		userRepository.save(userToDatabase);
		return "redirect:/";
	}

	@ModelAttribute("userDTO")
	public UserDTO getUserDTO() {
		return new UserDTO();
	}
}