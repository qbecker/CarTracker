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

    public CarDisplayExpandableListAdapter(Context context, List<String> expandableListTitle,
                                           HashMap<String, List<String>> expandableListDetail, String selectedCar) {
        this.context = context;
        this.selectedCar = selectedCar;
        this.expandableListDetail = new HashMap<String, List<String>>();
        this.expandableListTitle = expandableListTitle;
        List<String> trips = databaseDAO.GetAllTripNamesForCar(this.selectedCar, this.context);
        List<String> repairs = databaseDAO.GetAllRepairsNamesForCar(this.selectedCar, this.context);
        this.expandableListDetail.put("Trips", trips);
        this.expandableListDetail.put("Repairs", repairs);
        this.expandableListTitle = new ArrayList<String>(this.expandableListDetail.keySet());

    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }
        TextView expandedListItems = (TextView) convertView
                .findViewById(R.id.lblListItem);

        expandedListItems.setText(expandedListText);

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
        return false;
    }

    @Override
    public void onGroupCollapse(int groupPosition) {

    }

    @Override
    public void onGroupExpand(int groupPosition) {

    }
}
