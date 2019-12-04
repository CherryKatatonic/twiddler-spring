package techtonic.academy.twiddler.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

// Indicates that instances of this class will be stored in a relational database.
// The Table name in the database is the same as the class name by default (all lowercase),
// but it can be specified like so:
// @Entity(name = "table_name")
@Entity
public class Twiddle implements Serializable {

    // Indicates that this Column is the Primary Key for this Table
    @Id
    // Indicates that the value for this Column is generated automatically by the database
    @GeneratedValue
    private Long id;

    // Indicates that there can be MANY Twiddles associated with ONE User.
    // Fetch Type is set to EAGER because setting it to LAZY can cause the fetched Twiddles to be returned
    // before their respective Users are loaded, causing any logic that tries to access the User object to throw
    // a NullPointerException in Java or a TypeError in JavaScript.
    //
    // Essentially, it determines the asynchronous behavior for it:
    // FetchType.LAZY = continue doing other work while we wait for the results
    // FetchType.EAGER = wait for the results before continuing on
    @ManyToOne(fetch = FetchType.EAGER)
    // Indicates the name of the column on THIS Table that references the ID of the associated User in the OTHER Table
    // If the "name" parameter is not provided, it should default to the name of the associated Table + _ + the name of
    // the Primary Key from that table, which in this case would be "user_id".
    @JoinColumn(name = "user_id")
    //
    @JsonIgnoreProperties
    private User user;

    private String content;

    // Indicates that this is a Timestamp to be set automatically when the Entity is first created
    @CreationTimestamp
    private Date createdAt;

    // Indicates that this is a Timestamp to be set automatically every time the Entity is updated
    @UpdateTimestamp
    private Date updatedAt;

    // No-args Constructor is necessary for ORM
    public Twiddle() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
