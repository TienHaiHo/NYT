package app.num.linechart.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import app.num.linechart.R;
import app.num.linechart.presenter.FetchData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LineChart lineChart = (LineChart) findViewById(R.id.chart);

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(4f, 0));
        entries.add(new Entry(8f, 1));
        entries.add(new Entry(6f, 2));
        entries.add(new Entry(2f, 3));
        entries.add(new Entry(18f, 4));
        entries.add(new Entry(9f, 5));

        LineDataSet dataset = new LineDataSet(entries, "# of Calls");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");

        LineData data = new LineData(labels, dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
        dataset.setDrawCubic(true);

        dataset.setDrawFilled(true);

        lineChart.setData(data);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.animateY(5000);
////////////////////////////////////////////////////////////////////////////////////////////
        Button btnPrevious = (Button)findViewById(R.id.btn_previous);
        Button btnNext = (Button)findViewById(R.id.btn_next);
        final LineChart newChart = (LineChart)findViewById(R.id.chart1);
        final int[] count = {0};

        FetchData dataEntry= new FetchData();

        List<Entry> result = dataEntry.getListEntry(count[0]);
        final LineDataSet[] lineData = {new LineDataSet(result, "Day Revenue")};
        ArrayList<String> listS = new ArrayList<String>();
        for (int i = 1; i<=result.size(); i++){
            listS.add(String.valueOf(count[0]*10+i));
        }

        final LineData[] dataUse = new LineData[1];

        lineData[0].setColor(ColorTemplate.LIBERTY_COLORS[2]);
        lineData[0].setHighLightColor(ColorTemplate.LIBERTY_COLORS[2]);
        lineData[0].setCircleColorHole(ColorTemplate.LIBERTY_COLORS[2]);
        lineData[0].setCircleColor(ColorTemplate.LIBERTY_COLORS[2]);
//
        newChart.getAxisRight().setEnabled(false);
        newChart.getAxisRight().setDrawGridLines(false);
        newChart.getXAxis().setDrawGridLines(false);
        newChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        newChart.setGridBackgroundColor(ColorTemplate.COLOR_NONE);
        newChart.setBackgroundColor(ColorTemplate.COLOR_NONE);
        newChart.setDescription("");
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count[0] <2) count[0]++;
                FetchData dataEntry= new FetchData();
                List<Entry> result = dataEntry.getListEntry(count[0]);
                Log.d("test", String.valueOf(result.size()));
                LineDataSet[] lineData = {new LineDataSet(result, "Day Revenue")};
                ArrayList<String> listS = new ArrayList<String>();
                for (int i = 1; i<=result.size(); i++){
                    listS.add(String.valueOf(count[0]*10+i));
                }
                dataUse[0] = new LineData(listS, lineData[0]);

                Log.d("gsa", String.valueOf(dataEntry.getListEntry(count[0]).get(0).getVal()));

                lineData[0].setColor(ColorTemplate.LIBERTY_COLORS[2]);
                lineData[0].setHighLightColor(ColorTemplate.LIBERTY_COLORS[2]);
                lineData[0].setCircleColorHole(ColorTemplate.LIBERTY_COLORS[2]);
                lineData[0].setCircleColor(ColorTemplate.LIBERTY_COLORS[2]);

                newChart.clear();
                newChart.setData(dataUse[0]);
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count[0] >0) count[0]--;
                FetchData dataEntry= new FetchData();
                List<Entry> result = dataEntry.getListEntry(count[0]);
                Log.d("test", String.valueOf(result.size()));
                LineDataSet[] lineData = {new LineDataSet(result, "Day Revenue")};
                ArrayList<String> listS = new ArrayList<String>();
                for (int i = 1; i<=result.size(); i++){
                    listS.add(String.valueOf(count[0]*10+i));
                }
                dataUse[0] = new LineData(listS, lineData[0]);

                Log.d("gsa", String.valueOf(dataEntry.getListEntry(count[0]).get(0).getVal()));

                lineData[0].setColor(ColorTemplate.LIBERTY_COLORS[2]);
                lineData[0].setHighLightColor(ColorTemplate.LIBERTY_COLORS[2]);
                lineData[0].setCircleColorHole(ColorTemplate.LIBERTY_COLORS[2]);
                lineData[0].setCircleColor(ColorTemplate.LIBERTY_COLORS[2]);

                newChart.clear();
                newChart.setData(dataUse[0]);
            }
        });

        dataUse[0] = new LineData(listS, lineData[0]);
        lineData[0].setDrawFilled(false);

        newChart.setData(dataUse[0]);
    }
}
