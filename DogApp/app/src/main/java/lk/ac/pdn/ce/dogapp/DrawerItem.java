package lk.ac.pdn.ce.dogapp;

/**
 * Created by Hishan Indrajith on 11/24/2017.
 */
public class DrawerItem {

    String ItemName;
    int imgResID;
    String type;

    public DrawerItem(String itemName, int imgResID,String type) {
        super();
        ItemName = itemName;
        this.imgResID = imgResID;
        this.type=type;
    }

    public String getItemName() {
        return ItemName;
    }
    public void setItemName(String itemName) {
        ItemName = itemName;
    }
    public int getImgResID() {
        return imgResID;
    }
    public void setImgResID(int imgResID) {
        this.imgResID = imgResID;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
