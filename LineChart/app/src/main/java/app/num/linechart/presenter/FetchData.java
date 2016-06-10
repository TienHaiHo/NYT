package app.num.linechart.presenter;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

import app.num.linechart.model.ReturnData;

/**
 * Created by dkkbg_000 on 10/06/2016.
 */
public class FetchData {
    public  List<Entry> getListEntry (int count) {
        List<Entry> ent = ReturnData.RetrunListEntry();
        List<Entry> returnList = new ArrayList<>();
        if (count < 2)
        {
            for (int i=0; i<10; i++){
                returnList.add(ent.get(count*10+i));
            }
            return returnList;
        }
        else{
            for (int i=0; i<ent.size()-20; i++){
                returnList.add(ent.get(count*10+i));
            }
            return returnList;
        }
    }
}
