package g.takeru.renshu.customview.mountainchart;

public class ChartData {

    public static final int TYPE_DAY = 4;
    public static final int TYPE_WEEK = 7;
    public static final int TYPE_MONTH = 5;
    public static final int TYPE_YEAR = 12;

    public int value;
    public boolean isNow;
    public int mountainHeight;

    public ChartData(int value, boolean isNow, int mountainHeight) {
        this.value = value;
        this.isNow = isNow;
        this.mountainHeight = mountainHeight;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isNow() {
        return isNow;
    }

    public void setNow(boolean isNow) {
        this.isNow = isNow;
    }

    public int getMountainHeight() {
        return mountainHeight;
    }

    public void setMountainHeight(int mountainHeight) {
        this.mountainHeight = mountainHeight;
    }
}
