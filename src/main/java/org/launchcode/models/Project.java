/* 
Made by robin on 8/3/17, 10:56 PM 
*/

package org.launchcode.models;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;



    @OneToMany
    @JoinColumn(name = "project_id")
    private List<Task> tasks = new ArrayList<>();

    public Project() {}

    public Project(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
