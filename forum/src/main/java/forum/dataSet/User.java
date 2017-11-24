package forum.dataSet;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @ManyToOne
    @JoinColumn(name = "imageId")
    private ImageProfileUser imageProfileUser;

    public User() {}

    public User(String login, String password, ImageProfileUser imageProfileUser) {
        this.login = login;
        this.password = password;
        this.imageProfileUser = imageProfileUser;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateOfCreate = now.format(formatter);
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
}
