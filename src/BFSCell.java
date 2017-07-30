/**
 * Created by or on 7/30/17.
 */
public class BFSCell {
    Coordinates place;
    BFSCell parent;

    public BFSCell(Coordinates place, BFSCell parent){
        this.place=place;
        this.parent=parent;
    }

    public void setPlace(Coordinates newPlace){this.place=newPlace;}
    public void setParent(BFSCell newParent){this.parent=newParent;}

    public Coordinates getPlace(){return place;}
    public BFSCell getParent(){return parent;}
}
