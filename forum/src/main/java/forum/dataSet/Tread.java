package forum.dataSet;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
    @Type(type = "text")
    private String mainComment;

    @Column
    private String tag;

    @Column(updatable = false)
    private String dateOfCreate;

    @Column
    private String dateOfChange;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "tread")
    private List<Comment> comments;

    public Tread() {}

    public Tread(String subject, String mainComment, User user, String tag) {
        this.subject = subject;
        this.mainComment = mainComment;
        this.user = user;
        this.tag = tag;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateOfCreate = now.format(formatter);
        dateOfChange = dateOfCreate;
        comments = new ArrayList<>();
        user.addThread(this);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.addTread(this);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMainComment() {
        return mainComment;
    }

    public void setMainComment(String mainComment) {
        this.mainComment = mainComment;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDateOfCreate() {
        return dateOfCreate;
    }

    public String getDateOfChange() {
        return dateOfChange;
    }

    public void setDateOfChange(String dateOfChange) {
        this.dateOfChange = dateOfChange;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
