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
        hour = Integer.parseInt(timeTokens[0]);
        if(amOrPm.equals("p")){
            hour += 12;
        }

        minute = Integer.parseInt(secondTimeToken.substring(0, secondTimeTokenLength-2));

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

    @Override
    public int compareTo(Time time){
        int thisTime = hour*60 + minute;
        int otherTime = time.hour*60 + minute;
        return thisTime - otherTime;
    }

    public String toString(){
        return stringFormat;
    }
}