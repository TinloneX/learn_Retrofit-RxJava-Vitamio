package org.tinlone.demo.rxjavasample.bean;

public class CallLogBean {
    String toName;
    String toPhone;
    String callTime;
    String callType;
    String dateTime;

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getToPhone() {
        return toPhone;
    }

    public void setToPhone(String toPhone) {
        this.toPhone = toPhone;
    }

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return toName + "\n" +
                "电话：" + toPhone + "                       " + callType + "\n" +
                "通话时长：" + callTime + "         " + dateTime;
    }
}
