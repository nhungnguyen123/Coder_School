package com.uiapp.coder_school.dto;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
/**
 * Created by hongnhung on 1/26/17.
 */

@Table(name = "ItemName")
public class ItemName extends Model {

    @Column(name = "position")
    public  int position ;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    @Column(name = "name")
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
