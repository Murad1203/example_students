package com.springboot.student.spring_example_students.entity;


import javax.persistence.*;
import java.util.List;


//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
@Entity
@Table(name = "buckets")
public class Bucket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToMany
    @JoinTable(name = "backets_students",
    joinColumns = @JoinColumn(name = "bucket_id"),
    inverseJoinColumns = @JoinColumn(name = "stud_id"))
    private List<Students> students;



    public Bucket() {
    }

    public Bucket(User user, List<Students> students) {

        this.user = user;
        this.students = students;
    }

    public Students getStudId(long id) {
        return students.get((int) id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Students> getStudents() {
        return students;
    }

    public void setStudents(List<Students> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Bucket{" +
                "id=" + id +
                ", user=" + user +
                ", students=" + students +
                '}';
    }
}
