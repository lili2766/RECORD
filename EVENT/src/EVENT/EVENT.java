package EVENT;

public class EVENT {
    /*
    无参数构造
     */
    public EVENT() {
        this.id=10000;
        this.eventName = "df";
        this.eventType = "d";
        this.startTime=22210;
        this.endTime=455445544;
        this.useTime=1.02;
        this.remark = "afsdf";
    }

    /*
        私有仅为当前类被访问
     */
    private int id;
    private String eventName;
    private String eventType;
    private long startTime;
    private long endTime;
    private double useTime;
    private String remark;

    /*
    封装
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public double getUseTime() {
        return useTime;
    }

    public void setUseTime(double useTime) {
        this.useTime = useTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    //有参数构造
    public EVENT(int id, String eventName, String eventType, long startTime, long endTime, double useTime, String remark) {
        this.id = id;
        this.eventName = eventName;
        this.eventType = eventType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.useTime = useTime;
        this.remark = remark;
    }
}
