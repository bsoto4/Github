package doapps.github.util;

import android.content.Context;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

import doapps.github.R;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.Viewport;

/**
 * Created by Luis alberto on 21/07/2016.
 */
public class LineChartUtil {

    private Context context;
    public LineChartData data;

    /**
     * method to build an instance of the service
     *
     * @param context ==> any class context
     * @param data    data which represent data to a linearchart
     */
    public LineChartUtil(Context context, LineChartData data) {
        this.context = context;
        this.data = data;
    }

    /**
     * method to add values to arraylines
     *
     * @param xValues
     * @param lines
     */
    public void addDataLine(ArrayList<String> xValues, List<Line> lines) {

        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        for (int i = 0; i < xValues.size(); i++) {
            axisValues.add(new AxisValue(i * 10).setLabel(xValues.get(i)));
        }

        addArrayLines(lines, axisValues);
    }

    /**
     * method to add lines and list of axisValues
     *
     * @param Lines
     * @param axisValues
     */
    public void addArrayLines(List<Line> Lines, List<AxisValue> axisValues) {
        try {
            if (Lines.size() > 0 && !Lines.isEmpty()) {
                for (int i = 0; i < Lines.size(); i++) {
                    Line line = Lines.get(i);
                    line.setHasPoints(false);
                    //line.setCubic(true);
                    line.setStrokeWidth(2);

                    if (i == 0) {
                        line.setColor(context.getResources().getColor(R.color.colorBlack));
                    } else if (i == 1) {
                        line.setColor(context.getResources().getColor(R.color.colorBrown));
                    } else if (i == 2) {
                        line.setColor(context.getResources().getColor(R.color.colorBlue));
                    } else if (i == 3) {
                        line.setColor(context.getResources().getColor(R.color.colorGreen));
                    } else if (i == 4) {
                        line.setColor(context.getResources().getColor(R.color.colorRed));
                    } else {
                        line.setColor(context.getResources().getColor(R.color.colorNeutral));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            data = initData(Lines, axisValues);
        }
    }

    /**
     * method to set the ViewPort
     *
     * @return
     */
    public Viewport initViewPort(int top, int right) {
        Viewport viewport = new Viewport();
        //viewport.top = 300;
        viewport.top = top;
        viewport.bottom = 0;
        viewport.left = 0;
        //viewport.right = 300;
        viewport.right = right;

        return viewport;
    }

    /**
     * method that initialice and set default rule X and Y titles and styles
     *
     * @param lines
     * @param axisValues
     * @return a LineChartData
     */
    public LineChartData initData(List<Line> lines, List<AxisValue> axisValues) {

        LineChartData data = new LineChartData(lines);
        data.setAxisXBottom(new Axis(axisValues).setHasLines(true).setLineColor(Color.BLACK).setTextColor(Color.BLACK).setName("Notas"));
        data.setAxisYLeft(new Axis().setHasLines(true).setLineColor(Color.BLACK).setTextColor(Color.BLACK).setMaxLabelChars(3).setName("Alturas Arbitrarias"));

        return data;
    }

}
