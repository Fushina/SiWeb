package tpnote.control;

public class PokemonList {
    Integer id;
    String Image;
    String Name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    PokemonList(Integer id, String Name, String img){
        this.id = id;
        this.Name = Name;
        this.Image = img;
    }
}
