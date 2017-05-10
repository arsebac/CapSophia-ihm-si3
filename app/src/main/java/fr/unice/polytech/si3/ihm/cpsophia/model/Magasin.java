package fr.unice.polytech.si3.ihm.cpsophia.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import fr.unice.polytech.si3.ihm.cpsophia.model.event.Event;

/**
 * @author Francois Melkonian
 */

public class Magasin implements Serializable {
    private int id;
    private String name;
    private List<MagasinType> types;
    private List<Event> events;
    private String desc;
    private int image;
    private boolean haveImage;
    public Magasin(int id,String name, List<MagasinType> types, String desc,int image) {
        this(id, name, types, desc);
        this.image = image;
        haveImage = true;

    }
    public Magasin(int id,String name, List<MagasinType> types, String desc) {
        this.id = id;
        this.name = name;

        this.types = types;
        this.desc = desc;
        haveImage = false;
        events = new ArrayList<>();
    }

    public boolean isHaveImage() {
        return haveImage;
    }

    public boolean isType(MagasinType filtre) {
        return types.contains(filtre);
    }

    public String getName() {
        return name;
    }

    public List<MagasinType> getTypes() {
        return types;
    }

    public String getDesc() {
        return desc;
    }

    public int getId() {
        return id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Magasin magasin = (Magasin) o;

        if (!name.equals(magasin.name)) return false;
        return types.equals(magasin.types);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + types.hashCode();
        return result;
    }
    public int getImageId(){
        return image;
    }
    public Bitmap getImage(Context context) {
        return BitmapFactory.decodeResource(context.getResources(),image);
    }
}
