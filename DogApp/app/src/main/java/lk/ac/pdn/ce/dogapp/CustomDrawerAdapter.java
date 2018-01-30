package lk.ac.pdn.ce.dogapp;

/**
 * Created by Hishan Indrajith on 11/24/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

public class CustomDrawerAdapter extends ArrayAdapter<DrawerItem> {

    Context context;
    List<DrawerItem> drawerItemList;
    int layoutResID;

    public CustomDrawerAdapter(Context context, int layoutResourceID, List<DrawerItem> listItems) {
        super(context, layoutResourceID, listItems);
        this.context = context;
        this.drawerItemList = listItems;
        this.layoutResID = layoutResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        DrawerItemHolder drawerHolder;
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            drawerHolder = new DrawerItemHolder();

            view = inflater.inflate(layoutResID, parent, false);
            drawerHolder.ItemName = (TextView)view.findViewById(R.id.drawer_itemName);
            drawerHolder.icon = (ImageView) view.findViewById(R.id.drawer_icon);
            drawerHolder.itemLayout =(LinearLayout)view.findViewById(R.id.item_layout);

            view.setTag(drawerHolder);

        } else {
            drawerHolder = (DrawerItemHolder) view.getTag();
        }

        DrawerItem dItem = (DrawerItem) this.drawerItemList.get(position);

        drawerHolder.icon.setImageDrawable(view.getResources().getDrawable(
                dItem.getImgResID()));
        drawerHolder.ItemName.setText(dItem.getItemName());
        if(dItem.getType().equals("user")){
            drawerHolder.itemLayout.setMinimumHeight(150);
            drawerHolder.itemLayout.setBackgroundColor(Color.rgb(16, 49, 64));
            drawerHolder.ItemName.setText(MainPage.userData[0]);
        }
        else if(dItem.getType().equals("points")){
            drawerHolder.itemLayout.setMinimumHeight(100);
            drawerHolder.itemLayout.setBackgroundColor(Color.rgb(16,49,64));
            drawerHolder.ItemName.setText(""+MainPage.getPoints()+" Points");

        }else if(dItem.getType().equals("img")){
            drawerHolder.itemLayout.setMinimumHeight(310);
            drawerHolder.icon.setLayoutParams(new LinearLayout.LayoutParams(300, 300));
            drawerHolder.icon.setPadding(50, 0, 0, 0);
        }
        else if(dItem.getType().equals("log_out")){
            drawerHolder.icon.setVisibility(View.INVISIBLE);
            drawerHolder.ItemName.setTextColor(Color.rgb(55, 159, 9));
            drawerHolder.ItemName.setTextSize(20);
        }
        else if(dItem.getType().equals("footer")){
            drawerHolder.itemLayout.setMinimumHeight(100);
            drawerHolder.itemLayout.setBackgroundColor(Color.rgb(67, 67, 67));
            drawerHolder.icon.setVisibility(View.INVISIBLE);
            drawerHolder.icon.setLayoutParams(new LinearLayout.LayoutParams(0,0));
            drawerHolder.ItemName.setPadding(20,0,0,0);
            drawerHolder.ItemName.setTextColor(Color.WHITE);
            drawerHolder.ItemName.setTextSize(12);
        }


        return view;
    }

    private static class DrawerItemHolder {
        TextView ItemName;
        ImageView icon;
        LinearLayout itemLayout;
    }
}