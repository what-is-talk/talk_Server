package com.talk.entity;

import com.talk.enums.AuthProvider;
import com.talk.enums.Role;
import lombok.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Entity
public class Users extends BaseTime {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;


    @Column(name="USERNAME")
    private String name;

    @Column
    private String profile_image;

//    @Column
//    @ElementCollection(fetch = FetchType.EAGER)
//    @Builder.Default
//    private List<String> roles = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuthProvider authProvider;

    @Builder
    public Users(Long id, String email, String name, String profile_image ,Role role, AuthProvider authProvider) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.profile_image = profile_image;
        this.role = role;
        this.authProvider = authProvider;
    }

//    public String getRoleKey(){
//        return this.roles.getKey();
//    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//    }
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
////
//    @Override
//    public boolean isAccountNonExpired() { return true; }
////
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
////
//    @Override
//    public boolean isCredentialsNonExpired() { return true; }
//
//    @Override
//    public boolean isEnabled() { return true; }

}
