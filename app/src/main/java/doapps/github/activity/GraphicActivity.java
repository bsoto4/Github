package doapps.github.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import doapps.github.R;
import doapps.github.beandto.Level;
import doapps.github.beandto.RecordsDetail;
import doapps.github.service.LevelService;
import doapps.github.util.LineChartUtil;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class GraphicActivity extends AppCompatActivity {
    private static final String TAG = "GraphicActivity";
    private LineChartView line_chart_view;
    private LineChartUtil lineChartUtil;
    private LineChartData data;

    private LevelService levelService;
    private List<Line> lines = new ArrayList<Line>();

    private RecordsDetail recordsDetail;

    private TextView tvCY;
    private TextView tvBY;
    private TextView riverDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic);
        init();

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        Log.e(TAG,"Screen size height : "+height+"/ width : "+width);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initUIEvents();
    }

    private void init() {
        line_chart_view = (LineChartView) findViewById(R.id.chart);
        tvCY = (TextView) findViewById(R.id.Currentyear);
        tvBY = (TextView) findViewById(R.id.Backyear);
        riverDetail = (TextView) findViewById(R.id.riverDetail);

        lineChartUtil = new LineChartUtil(GraphicActivity.this, data);
        recordsDetail = new RecordsDetail(new ArrayList<Level>(), new ArrayList<Level>());
        levelService = new LevelService(GraphicActivity.this);
        levelService.getAllLevelData();
    }

    private void initUIEvents() {
        levelService.initGetAllLevelDataInterface(new LevelService.LevelInterface() {
            @Override
            public void interfaceGetData(JSONObject jsonObject) {
                try {
                    Log.e(TAG, "Array data : " + jsonObject);

                    JSONArray jsonArrayBackYear = jsonObject.getJSONArray("recordsLastYear");
                    JSONArray jsonArrayCurrentYear = jsonObject.getJSONArray("recordsCurrentYear");
                    double maxlevel = jsonObject.getDouble("maxPromLevel");
                    int maxdata = jsonObject.getInt("dataCount");

                  //  ArrayList<String> dias = new ArrayList<String>();
                    List<PointValue> pointValueList_CY = new ArrayList<PointValue>();
                    List<PointValue> pointValueList_BY = new ArrayList<PointValue>();

                    List<PointValue> pointValueList_MAX = new ArrayList<PointValue>();
                    List<PointValue> pointValueList_PROM = new ArrayList<PointValue>();
                    List<PointValue> pointValueList_MIN = new ArrayList<PointValue>();

                    SimpleDateFormat spf = new SimpleDateFormat("yyyy/MM/dd");

                    for (int i = 0; i < jsonArrayCurrentYear.length(); i++) {
                        JSONObject objCY = jsonArrayCurrentYear.getJSONObject(i);

                        Level nivel = new Level();
                        nivel.setId(objCY.getString("id"));
                        nivel.setRecord(objCY.getString("record"));
                        Date date = new Date(objCY.getString("dateRecord"));
                        nivel.setDateRecord(date);
                        nivel.setDateRecordString(spf.format(date));
                        nivel.setSectorId(objCY.getString("sectorId"));
                        nivel.setMax(objCY.getDouble("max"));
                        nivel.setProm(objCY.getDouble("prom"));
                        nivel.setMin(objCY.getDouble("min"));

                        pointValueList_CY.add(new PointValue().set((int) (i * 10), (int) Double.parseDouble(nivel.getRecord())));

                        pointValueList_MAX.add(new PointValue().set((int) (i * 10), (int) Double.parseDouble(nivel.getMax() + "")));
                        pointValueList_PROM.add(new PointValue().set((int) (i * 10), (int) Double.parseDouble(nivel.getProm() + "")));
                        pointValueList_MIN.add(new PointValue().set((int) (i * 10), (int) Double.parseDouble(nivel.getMin() + "")));
                        recordsDetail.recordsCurrentYear.add(nivel);
                    //    dias.add(recordsDetail.recordsCurrentYear.get(i).getDateRecordString());
                    }

                     for (int i = 0; i < jsonArrayBackYear.length(); i++) {
                        JSONObject objCY = jsonArrayBackYear.getJSONObject(i);
                        Level nivel = new Level();

                        nivel.setId(objCY.getString("id"));
                        nivel.setRecord(objCY.getString("record"));
                        Date date = new Date(objCY.getString("dateRecord"));
                        nivel.setDateRecordString(spf.format(date));
                        nivel.setSectorId(objCY.getString("sectorId"));
                        nivel.setMax(objCY.getDouble("max"));
                        nivel.setProm(objCY.getDouble("prom"));
                        nivel.setMin(objCY.getDouble("min"));

                        pointValueList_BY.add(new PointValue().set((int) (i * 10), (int) Double.parseDouble(nivel.getRecord())));
                        recordsDetail.recordsLastYear.add(nivel);
                    }

                    Line line_CY = new Line(pointValueList_CY);
                    Line line_BY = new Line(pointValueList_BY);

                    Line line_MAX = new Line(pointValueList_MAX);
                    Line line_PROM = new Line(pointValueList_PROM);
                    Line line_MIN = new Line(pointValueList_MIN);

                    lines.add(line_CY);
                    lines.add(line_BY);

                    lines.add(line_MAX);
                    lines.add(line_PROM);
                    lines.add(line_MIN);

                    ArrayList<String> xValues = new ArrayList<String>();

                    for (int i = 0; i < maxdata; i++) {
                        xValues.add("" + i);
                    }
                    // xValues.add("");
                    // xValues.add("1");
                    if (!recordsDetail.recordsCurrentYear.isEmpty() && !recordsDetail.recordsLastYear.isEmpty()) {
                        tvCY.setText(recordsDetail.recordsCurrentYear.get(0).getDateRecordString().substring(0, 4));
                        tvBY.setText(recordsDetail.recordsLastYear.get(0).getDateRecordString().substring(0, 4));
                    }
                    riverDetail.setText(jsonObject.getString("rio")+" - "+jsonObject.getString("sector"));

                    lineChartUtil.addDataLine(xValues, lines);
                    line_chart_view.setLineChartData(lineChartUtil.data);

                    Viewport viewport = lineChartUtil.initViewPort((int) maxlevel + 5, (int) maxdata * 10);
                    line_chart_view.startDataAnimation(300);
                    line_chart_view.setMaximumViewport(viewport);
                    line_chart_view.setCurrentViewport(viewport);
                    line_chart_view.setViewportCalculationEnabled(false);

                    // */
                } catch (JSONException e) {
                    e.getMessage();
                    e.printStackTrace();
                }
            }
        });
    }
}
