package techtonic.academy.twiddler.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

// Indicates that instances of this class will be stored in a relational database.
// The Table name in the database is the same as the class name by default (all lowercase),
// but it can be specified like so:
// @Entity(name = "table_name")
@Entity
public class User implements UserDetails, Serializable {

    // Indicates that this Column is the Primary Key for this Table
    @Id
    // Indicates that the value for this Column is generated automatically by the database
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Indicates that this field cannot be blank
    @NotBlank(message = "Username is required")
    // Indicates that two Users cannot have the same username
    @Column(unique = true)
    private String username;

    // Indicates that this field cannot be blank
    @NotBlank(message = "Password is required")
    // Indicates that this property can be written to in JSON (such as during Signup), but it
    // cannot be read (it will not be included in JSON when User objects are returned to the client)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    // Indicates that this property should not be persisted in the database. We use it once,
    // when constructing a User from a Signup form submission, and then we throw it away.
    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String confirmPassword;

    // Image uploads have not yet been implemented, but the "spring-cloud-gcp-starter-storage Maven
    // dependency has been included in the POM.
    private String imageUrl;

    // Indicates that this is a Timestamp to be set automatically when the Entity is first created
    @CreationTimestamp
    private Date createdAt;

    // Indicates that this is a Timestamp to be set automatically every time the Entity is updated
    @UpdateTimestamp
    private Date updatedAt;

    // This would be how to implement a OneToMany relationship if you wanted to store the User's Twiddles in
    // a list on the User object instead of retrieving them independently.
    // Note: this method should only be used if the list will only consist of a small number of items.
//    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
//    private List<Twiddle> twiddles = new ArrayList<>();

    public User() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    // Only needed if Twiddles are being stored directly on the User object
//    public List<Twiddle> getTwiddles() {
//        return twiddles;
//    }
//
//    public void setTwiddles(List<Twiddle> twiddles) {
//        this.twiddles = twiddles;
//    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Methods implemented from UserDetails
    // UserDetails provides a wrapper for our User class so that Spring Security can work with it
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
