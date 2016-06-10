package app.num.linechart.model;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dkkbg_000 on 10/06/2016.
 */
public class ReturnData {
    public static List<Entry> RetrunListEntry () {
        List<Entry> ent = new ArrayList<>();
        ent.add(new Entry(1f, 0));
        ent.add(new Entry(6f, 1));
        ent.add(new Entry(4f, 2));
        ent.add(new Entry(3f, 3));
        ent.add(new Entry(12f, 4));
        ent.add(new Entry(3f, 5));
        ent.add(new Entry(13f, 6));
        ent.add(new Entry(13f, 7));
        ent.add(new Entry(15f, 8));
        ent.add(new Entry(12f, 9));
        ent.add(new Entry(2f, 0));
        ent.add(new Entry(3f, 1));
        ent.add(new Entry(4f, 2));
        ent.add(new Entry(5f, 3));
        ent.add(new Entry(6f, 4));
        ent.add(new Entry(7f, 5));
        ent.add(new Entry(12f, 6));
        ent.add(new Entry(13f, 7));
        ent.add(new Entry(9f, 8));
        ent.add(new Entry(11f, 9));
        ent.add(new Entry(8f, 0));
        ent.add(new Entry(7f, 1));
        ent.add(new Entry(6f, 2));
        ent.add(new Entry(7f, 3));
        ent.add(new Entry(6f, 4));
        ent.add(new Entry(7f, 5));
        ent.add(new Entry(6f, 6));
        ent.add(new Entry(6f, 7));
        ent.add(new Entry(9f, 8));
        ent.add(new Entry(9f, 9));
        ent.add(new Entry(10f, 10));
//        ent.add(new Entry(9f, 10));
        return ent;
    }
}
