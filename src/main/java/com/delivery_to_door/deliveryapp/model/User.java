package com.delivery_to_door.deliveryapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true)
    private String username;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(unique = true, nullable = true)
    private String mobile;

    @Column(nullable = true)
    private String password;

    @Column()
    private String otp;

    @Column(name = "is_active")
    private int isActive = 1;

    @Column(name = "is_verified")
    private int isVerified = 0;

    //virtual field for token
    @Transient
    private String token;

    private User(){};

    public boolean getIsVerified(){
        return this.isVerified == 1 ? true : false;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email,String firstName,String lastName){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return "";
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
