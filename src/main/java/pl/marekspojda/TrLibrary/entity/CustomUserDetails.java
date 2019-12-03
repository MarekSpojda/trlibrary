package pl.marekspojda.TrLibrary.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class CustomUserDetails extends User implements UserDetails {
	private static final long serialVersionUID = 4580143358086688090L;

	public CustomUserDetails(final User user) {
		super(user);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();

		List<Role> userRole = super.getRoles();

		if (userRole != null) {
			for (Role role : userRole) {
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRole());

				authorities.add(authority);
			}
		}

		return authorities;
	}

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public String getSurname() {
		return super.getSurname();
	}

	@Override
	public List<Role> getRoles() {
		return super.getRoles();
	}

	@Override
	public List<Book> getBooks() {
		return super.getBooks();
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		return super.getEmail();
	}

	@Override
	public Date getBirthDate() {
		return super.getBirthDate();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
