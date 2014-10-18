package by.SmartWardrobe;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import by.idea.SmartWardrobe.R;
import main.constants.Category;
import main.wardrobe.entity.Apparel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tab1Activity  extends Activity
{
    ListView list;
    ListViewAdapter listviewadapter;
    List<Apparel> apparelList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.today_suit);

        //TODO apprelList = getTodaySuit(s, k, g, b, n);
        //TODO delete this code below
        apparelList = new ArrayList<Apparel>(Arrays.asList(
                new Apparel("1", BitmapFactory.decodeResource(getResources(), R.drawable.ex), Category.SWEATER, 3, "desc"),
                new Apparel("2", BitmapFactory.decodeResource(getResources(), R.drawable.ex), Category.SWEATER, 3, "desc"),
                new Apparel("3", BitmapFactory.decodeResource(getResources(), R.drawable.ex), Category.SWEATER, 3, "desc"),
                new Apparel("4", BitmapFactory.decodeResource(getResources(), R.drawable.ex), Category.SWEATER, 3, "desc")
        ));

        list = (ListView) findViewById(R.id.lvTodaySuit);

        // Pass results to ListViewAdapter Class
        listviewadapter = new ListViewAdapter(this, R.layout.listview_item,
                apparelList);

        // Binds the Adapter to the ListView
        list.setAdapter(listviewadapter);
        list.setChoiceMode(ListView.CHOICE_MODE_NONE);
       //TODO вставить всевозможные варианты
        final ArrayList<String> arr1 = new ArrayList<String>() {{
            add("Вариант 1");
            add("Вариант 2");
            add("Вариант 3");
        }};
        final Spinner sp1 = (Spinner) findViewById(R.id.spinner);
        Spinner sp2 = (Spinner) findViewById(R.id.spinner2);
        Spinner sp3 = (Spinner) findViewById(R.id.spinner3);
        final SpinnerAdapter spAdapter1 = new ArrayAdapter<String>(this, R.layout.list_item_category, arr1);
        SpinnerAdapter spAdapter2 = new ArrayAdapter<String>(this, R.layout.list_item_category, arr1);
        SpinnerAdapter spAdapter3 = new ArrayAdapter<String>(this, R.layout.list_item_category, arr1);
        sp1.setAdapter(spAdapter1);
        sp2.setAdapter(spAdapter2);
        sp3.setAdapter(spAdapter3);
        Button btnChange = (Button) findViewById(R.id.btnChange);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = arr1.get(sp1.getSelectedItemPosition());
//                String b = arr2.get(sp2.getBaseline());
//                String c = arr3.get(sp3.getBaseline());
                //TODO apprel = getTodaySuit(s, k, g, d, t, k);
            }
        });
    }
}