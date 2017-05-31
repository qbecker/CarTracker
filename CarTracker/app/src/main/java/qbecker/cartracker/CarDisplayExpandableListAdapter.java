package qbecker.cartracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import qbecker.cartracker.CarStuff.Repair;
import qbecker.cartracker.CarStuff.Trip;


/**
 * Created by qbecker on 5/30/17.
 */

public class CarDisplayExpandableListAdapter extends BaseExpandableListAdapter implements View.OnTouchListener,
        ExpandableListView.OnGroupExpandListener,
        ExpandableListView.OnGroupCollapseListener{

    public Context context;
    public List<String> expandableListTitle;
    public HashMap<String, List<String>> expandableListDetail;
    public String selectedCar;
    public List<String> repairDescriptions;
    public List<String> tripDescriptions;

    public CarDisplayExpandableListAdapter(Context context, List<String> expandableListTitle,
                                           HashMap<String, List<String>> expandableListDetail, String selectedCar) {
        this.context = context;
        this.selectedCar = selectedCar;
        this.expandableListDetail = new HashMap<String, List<String>>();
        this.expandableListTitle = expandableListTitle;
        List<Trip> rawTrips = databaseDAO.GetAllTripNamesForCar(this.selectedCar, this.context);
        List<Repair> rawRepairs = databaseDAO.GetAllRepairsNamesForCar(this.selectedCar, this.context);
        repairDescriptions = new ArrayList<String>();
        tripDescriptions = new ArrayList<String>();
        for(int i = 0; i < rawRepairs.size(); i++){
            String des = rawRepairs.get(i).getDescription() + "@" + rawRepairs.get(i).getUid() + "@" + rawRepairs.get(i).getDate();
            repairDescriptions.add(des);
        }
        for(int i = 0; i < rawTrips.size(); i++){
            String des = rawTrips.get(i).getDescription() + "@" + rawTrips.get(i).getUid() + "@" + rawTrips.get(i).getDate();
            tripDescriptions.add(des);
        }
        this.expandableListDetail.put("Trips", tripDescriptions);
        this.expandableListDetail.put("Repairs", repairDescriptions);
        this.expandableListTitle = new ArrayList<String>(this.expandableListDetail.keySet());

    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        android.util.Log.w("getChild  called","");

        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        android.util.Log.w("getChildID called","");
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListTextItemsPreSplit = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item_id_date, null);
        }
        String[] expandedListTextItemsPostSplit = expandedListTextItemsPreSplit.split("@");
        TextView expandedListItemsDesc = (TextView) convertView
                .findViewById(R.id.lblListItem);
        TextView expandedListItemsId = (TextView) convertView
                .findViewById(R.id.lblListItemId);
        TextView expandedListItemsDate = (TextView) convertView
                .findViewById(R.id.lblListItemDate);

        expandedListItemsDesc.setText(expandedListTextItemsPostSplit[0]);
        expandedListItemsId.setText(expandedListTextItemsPostSplit[1]);
        expandedListItemsDate.setText(expandedListTextItemsPostSplit[2]);



        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        android.util.Log.w(this.getClass().getSimpleName(), listTitle);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listHeaderText = (TextView) convertView
                .findViewById(R.id.lblListHeader);

        listHeaderText.setText(listTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        android.util.Log.w("","Entered onTouch");
        if (event.getAction()==MotionEvent.ACTION_DOWN){
            // onTouch is passed the textview's parent view, a linearlayout - what we set the
            // event on. Look at its children to find the textview
            if(v instanceof android.widget.LinearLayout){
                android.widget.LinearLayout layView = (android.widget.LinearLayout)v;
                // the layout (from list_item.xml should only have one child, but, here's how
                // you find the children of a layout or other view group.
                for(int i=0; i<=layView.getChildCount(); i++){
                    if(layView.getChildAt(i) instanceof TextView){
                        TextView tmp = ((TextView)layView.getChildAt(i));
                        android.util.Log.d(this.getClass().getSimpleName(),"in onTouch called for: " +
                                tmp.getText());
                    }
                }
            }
            // code below should never executes. onTouch is called for textview's linearlayout parent
            if(v instanceof TextView){
                android.util.Log.d(this.getClass().getSimpleName(),"in onTouch called for: " +
                        ((TextView)v).getText());
            }
        }
        return true;
    }

    @Override
    public void onGroupCollapse(int groupPosition) {

    }

    @Override
    public void onGroupExpand(int groupPosition) {

    }
}
