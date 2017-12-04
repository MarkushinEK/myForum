package forum.dataSet;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, updatable = false)
    private String login;

    @Column
    private String password;

    @Column(updatable = false)
    private String dateOfCreate;

    @Column(unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "imageId")
    private ImageProfileUser imageProfileUser;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Tread> threads;

    public User() {}

    public User(String login, String password, String email, ImageProfileUser imageProfileUser) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.imageProfileUser = imageProfileUser;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateOfCreate = now.format(formatter);
        comments = new ArrayList<>();
        threads = new ArrayList<>();
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void addThread(Tread tread) {
        threads.add(tread);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(String dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public ImageProfileUser getImageProfileUser() {
        return imageProfileUser;
    }

    public void setImageProfileUser(ImageProfileUser imageProfileUser) {
        this.imageProfileUser = imageProfileUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<Tread> getThreads() {
        return threads;
    }
}
