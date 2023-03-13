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
		super(member.getMemberId(),
				member.getMemberPw(),
				new ArrayList<GrantedAuthority>(
						Arrays.asList(
								new SimpleGrantedAuthority(member.getRole())
								)
						)
				);
		this.member = member;
	}

	public SecurityUserDetails(String memberId, Collection<? extends GrantedAuthority> authorities) {
		super(memberId, "", authorities);
		this.member = new Member();
		member.setMemberId(memberId);
		
		String role = null;
		for (GrantedAuthority authority : authorities) {
			role = authority.getAuthority(); // 이 서비스에서는 1계정당 1권한이므로 한 번만 루프를 돌게 됨. 덮어써도 무방.
		}
			
		char roleCode = 0;
		if (role.contentEquals("ROLE_NORMAL")) { // enum 도입 고려
			roleCode = 'N';
		} else if (role.equals("ROLE_COMPANY")) {
			roleCode = 'C';
		} else if (role.equals("ROLE_ADMIN")) {
			roleCode = 'A';
		}
		
		member.setMemberType(roleCode);
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
		return this.member.getMemberPw();
	}

	@Override
	public String getUsername() {
		return this.member.getMemberId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.member.isMemberActive();
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.member.isMemberActive();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.member.isMemberActive();
	}

	@Override
	public boolean isEnabled() {
		return this.member.isMemberActive();
	}
}
