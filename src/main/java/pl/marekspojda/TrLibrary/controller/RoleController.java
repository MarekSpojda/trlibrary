package pl.marekspojda.TrLibrary.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import pl.marekspojda.TrLibrary.entity.User;
import pl.marekspojda.TrLibrary.repository.RoleRepository;
import pl.marekspojda.TrLibrary.repository.UserRepository;

@Controller
public class RoleController {
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	public RoleController(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@PostMapping(path = "/studenttouser", produces = "text/html; charset=UTF-8")
	public void studentToUserPost(Principal principal, HttpServletRequest request) {
		User loadedUser = userRepository.findUserByEmailCustom(principal.getName());
		if (!request.isUserInRole("ROLE_USER")) {
			loadedUser.getRoles().add(roleRepository.findById(2L).get());
		}
		userRepository.save(loadedUser);
	}

	@PostMapping(path = "/studenttoadmin", produces = "text/html; charset=UTF-8")
	public void studentToAdminPost(Principal principal, HttpServletRequest request) {
		User loadedUser = userRepository.findUserByEmailCustom(principal.getName());
		if (!request.isUserInRole("ROLE_ADMIN")) {
			loadedUser.getRoles().add(roleRepository.findById(1L).get());
		}
		userRepository.save(loadedUser);
	}
}