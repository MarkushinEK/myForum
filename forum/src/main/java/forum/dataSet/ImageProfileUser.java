package forum.dataSet;

import javax.persistence.*;

@Entity
@Table(name = "imageprofileuser")
public class ImageProfileUser {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String imageName;

    @Column
    private String extension;

    public ImageProfileUser(){}

    public ImageProfileUser(String imageName) {
        this.imageName = imageName;
        extension = imageName.substring(imageName.lastIndexOf(".")+1);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
