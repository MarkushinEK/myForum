package forum.dataSet;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "comments")
public class Comment {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(updatable = false)
    @Type(type = "text")
    private String message;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(updatable = false)
    private String dateOfCreateMessage;

    @ManyToOne
    @JoinColumn(name = "treadId")
    private Tread tread;

    public Comment() {}

    public Comment(String message, User user) {
        this.message = message;
        this.user = user;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateOfCreateMessage = now.format(formatter);
        user.addComment(this);
    }

    public void addTread(Tread tread) {
        this.tread = tread;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDateOfCreateMessage() {
        return dateOfCreateMessage;
    }

    public void setDateOfCreateMessage(String dateOfCreateMessage) {
        this.dateOfCreateMessage = dateOfCreateMessage;
    }

    public Tread getTread() {
        return tread;
    }

    public void setTread(Tread tread) {
        this.tread = tread;
    }
}
