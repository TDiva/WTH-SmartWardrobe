package by.SmartWardrobe;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TextView;
import by.idea.SmartWardrobe.R;
import main.constants.Category;
import main.wardrobe.entity.Apparel;
import main.wardrobe.service.WardrobeManager;

import java.io.*;

public class MyActivity extends TabActivity {
    /**
     * Called when the activity is first created.
     */
    final String DIR_SD = ".Apparel";
    final String FILENAMETAG = "deficon";
    final String FILENAME = "basa";
    TextView tvWeather;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        writeFileSD();
        setContentView(by.idea.SmartWardrobe.R.layout.main);
        tvWeather = (TextView) findViewById(by.idea.SmartWardrobe.R.id.tvWeather);
        FetchWeatherTask weatherTask = new FetchWeatherTask(this, tvWeather);
        weatherTask.execute("Minsk", "metric");
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        try {
            ObjectInputStream is = new ObjectInputStream(openFileInput(FILENAME));
            WardrobeManager.loadFromFile(is);
            is.close();
        } catch (IOException e) {

        }
        //TODO delete inicializer
        Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ex);

        for (int i = 0; i < 5; i++) {
            WardrobeManager.getInstance().addApparel(new Apparel("...", myBitmap, Category.SHIRT, 0, "..."));

        }

        TabHost.TabSpec tab1 = tabHost.newTabSpec(getString(by.idea.SmartWardrobe.R.string.main_menu_auto_find));
        TabHost.TabSpec tab2 = tabHost.newTabSpec(getString(by.idea.SmartWardrobe.R.string.main_menu_catalog));
        TabHost.TabSpec tab3 = tabHost.newTabSpec(getString(by.idea.SmartWardrobe.R.string.main_menu_find_by_teg));
        TabHost.TabSpec tab4 = tabHost.newTabSpec(getString(by.idea.SmartWardrobe.R.string.main_menu_find_to_wash));
        TabHost.TabSpec tab5 = tabHost.newTabSpec(getString(by.idea.SmartWardrobe.R.string.main_menu_pack_to_trip));

        // Set the Tab name and Activity
        // that will be opened when particular Tab will be selected
        tab1.setIndicator(getString(by.idea.SmartWardrobe.R.string.main_menu_auto_find));
        tab1.setContent(new Intent(this, Tab1Activity.class));

        tab2.setIndicator(getString(by.idea.SmartWardrobe.R.string.main_menu_catalog));
        tab2.setContent(new Intent(this, Tab2Activity.class));

        tab3.setIndicator(getString(by.idea.SmartWardrobe.R.string.main_menu_find_by_teg));
        tab3.setContent(new Intent(this, Tab3Activity.class));

        tab4.setIndicator(getString(by.idea.SmartWardrobe.R.string.main_menu_find_to_wash));
        tab4.setContent(new Intent(this, Tab4Activity.class));

        tab5.setIndicator(getString(by.idea.SmartWardrobe.R.string.main_menu_pack_to_trip));
        tab5.setContent(new Intent(this, Tab5Activity.class));

        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);
        tabHost.addTab(tab4);
        tabHost.addTab(tab5);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                onAddClick();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onAddClick() {
        Intent intent = new Intent(getApplicationContext(), AddingActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        try {
            ObjectOutputStream os = new ObjectOutputStream(openFileOutput(FILENAME, MODE_PRIVATE));
            WardrobeManager.saveToFile(os);
            os.close();
        } catch (IOException e) {

        }
    }
    void writeFileSD() {
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return;
        }
        // получаем путь к SD
        File sdPath = Environment.getExternalStorageDirectory();
        // добавляем свой каталог к пути
        sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);
        // создаем каталог
        sdPath.mkdirs();
        // формируем объект File, который содержит путь к файлу
        File sdFile = new File(sdPath, FILENAMETAG);
        try {
            // открываем поток для записи
            ObjectOutputStream bw = new ObjectOutputStream(new FileOutputStream(sdFile));
            // пишем данные
            bw.writeObject(BitmapFactory.decodeResource(getResources(), R.drawable.ex));
            // закрываем поток
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
