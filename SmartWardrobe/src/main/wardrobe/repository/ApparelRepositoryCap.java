package main.wardrobe.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.constants.Category;
import main.wardrobe.entity.Apparel;

/**
 * Created by TDiva on 18.10.2014.
 */
public class ApparelRepositoryCap implements ApparelRepository {

    private Map<Category, List<Apparel>> catalog = new HashMap<Category, List<Apparel>>();
    private List<Apparel> wash = new ArrayList<Apparel>();
    private List<Apparel> clothes = new ArrayList<Apparel>();


    @Override
    public List<Apparel> getAll() {
        return clothes;
    }

    @Override
    public List<Apparel> getByCategory(String category) {
        Category c = Category.getByType(category);
        return catalog.get(c);
    }

    @Override
    public List<Apparel> getDirty() {
        return wash;
    }

    @Override
    public List<Apparel> getNotDirty() {
        List ret = (new ArrayList<Apparel>(clothes));
        ret.removeAll(wash);
        return ret;
    }

    @Override
    public Apparel getById(Long id) {
        int index = Collections.binarySearch(clothes, new Apparel(id), new Comparator<Apparel>() {
            @Override
            public int compare(Apparel apparel, Apparel apparel2) {
                return (int) (apparel2.getId() - apparel.getId());
            }
        });
        return clothes.get(index);
    }

    @Override
    public void addApparel(Apparel app) {
        clothes.add(app);
        if (app.getInWash()) {
            wash.add(app);
        }
        if (catalog.containsKey(app.getCategory()))
            catalog.get(app.getCategory()).add(app);
        else
            catalog.put(app.getCategory(), Arrays.asList(app));

    }

    @Override
    public void deleteApparel(Apparel app) {
        if (app.getInWash()) {
            wash.remove(app);
        }
        catalog.get(app.getCategory()).remove(app);
        clothes.remove(app);
    }

    @Override
    public void setWash(Apparel app, boolean flag) {
        app.setInWash(flag);
        if (flag) {
            wash.add(app);
        } else {
            wash.remove(app);
        }
    }
}
