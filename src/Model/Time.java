package Model;

public class Time implements Comparable<Time>{

    private int hour;
    private int minute;
    private String stringFormat;

    private static String TIME_DELIMITER = ":";

    /**
     * constructs a time class based on the given string
     *
     * @param time expected to be of the format hh:mm[a/p],
     *             where a is am, and p is pm
     */
    public Time(String time){
        String[] timeTokens = time.split(TIME_DELIMITER);

        String secondTimeToken = timeTokens[1];
        int secondTimeTokenLength = secondTimeToken.length();
        String amOrPm = secondTimeToken.substring(secondTimeTokenLength-1);
        this.hour = Integer.parseInt(timeTokens[0]);
        if(amOrPm.equals("a") && this.hour == 12) {
            this.hour = 0;
        }
        if(amOrPm.equals("p") && this.hour != 12){
            this.hour += 12;
        }

        this.minute = Integer.parseInt(secondTimeToken.substring(0, secondTimeTokenLength-1));

        this.stringFormat = time;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }

        if(obj instanceof Time){
            Time time = (Time) obj;
            return hour == time.hour && minute == time.minute;
        }

        return false;
    }

    public int getTotalMinutes(){
        return hour*60 + minute;
    }


    @Override
    public int hashCode(){
        return hour*60 + minute;
    }

    @Override
    public int compareTo(Time time){
        int thisTime = hour*60 + minute;
        int otherTime = time.hour*60 + time.minute;
        return thisTime - otherTime;
    }

    public String toString(){
        return stringFormat;
    }
}
