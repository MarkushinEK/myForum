package forum.dataSet;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "threads")
public class Tread {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(updatable = false)
    private String subject;

    @Column(updatable = false)
    private String comment;

    @Column
    private String tag;

    @Column(updatable = false)
    private String dateOfCreate;

    @Column
    private String dateOfChange;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public Tread() {}

    public Tread(String subject, String comment, User user, String tag) {
        this.subject = subject;
        this.comment = comment;
        this.user = user;
        this.tag = tag;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateOfCreate = now.format(formatter);
        dateOfChange = dateOfCreate;
    }

    public String getSubject() {
        return subject;
    }

    public String getComment() {
        return comment;
    }

    public long getThreadId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getDateOfCreate() {
        return dateOfCreate;
    }

    public String getDateOfChange() {
        return dateOfChange;
    }

    public String getTag() {
        return tag;
    }

}
