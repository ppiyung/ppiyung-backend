package org.ppiyung.ppiyung.member.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

public class SecurityUserDetails extends User implements UserDetails {

	@Getter
	@Setter
	private Member member;
	
	public SecurityUserDetails(Member member) {
		super(member.getMember_id(),
				member.getMember_pw(),
				new ArrayList<GrantedAuthority>(
						Arrays.asList(
								new SimpleGrantedAuthority(member.getRole())
								)
						)
				);
		this.member = member;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		List<String> roles = new ArrayList<String>();
		roles.add(this.member.getRole());
		
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return this.member.getMember_pw();
	}

	@Override
	public String getUsername() {
		return this.member.getMember_id();
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.member.isMember_active();
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.member.isMember_active();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.member.isMember_active();
	}

	@Override
	public boolean isEnabled() {
		return this.member.isMember_active();
	}
}
