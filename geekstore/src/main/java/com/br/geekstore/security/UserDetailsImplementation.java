package com.br.geekstore.security;


import com.br.geekstore.model.Role;
import com.br.geekstore.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

public class UserDetailsImplementation implements UserDetails, Serializable {

    private static final long serialversionUIDLONG = 1L;

    private String email;
    private String password;
    private Collection<Role> roles;



    public UserDetailsImplementation(User usuario) {
        this.email = usuario.getEmail();
        this.password = usuario.getPassword();
        this.roles = usuario.getRoles();

    }

    public UserDetailsImplementation() {}


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
