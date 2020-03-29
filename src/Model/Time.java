package Model;

import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * AFRS representation of time
 */
public class Time implements Comparable<Time>{

    /**
     * 24 hour representation
     */
    private int hour;

    /**
     * the value of mm in hh:mm
     */
    private int minute;

    /**
     * represents this time in hh:mm[a/p]
     * where hh is in 12 hour representation based on
     * whether or not the time is in am (a) or pm (p)
     */
    private String stringFormat;

    /**
     * used for separating hh:mm[a/p] into {hh, mm[a/p]}
     */
    private String TIME_DELIMITER = ":";


    /**
     * constructs a time class based on the given string
     *
     * @param time expected to be of the format hh:mm[a/p],
     *             where a is am, and p is pm
     */
    public Time(String time){
        this.stringFormat = time;

        String[] timeTokens = time.split(TIME_DELIMITER);

        //secondTimeToken is mm[a/p]
        String secondTimeToken = timeTokens[1];

        //get the adjust hh to 24 hour time
        int secondTimeTokenLength = secondTimeToken.length();
        String amOrPm = secondTimeToken.substring(secondTimeTokenLength-1);
        this.hour = Integer.parseInt(timeTokens[0]);
        if(amOrPm.equals("a") && this.hour == 12) {
            this.hour = 0;
        }
        if(amOrPm.equals("p") && this.hour != 12){
            this.hour += 12;
        }

        //get mm
        this.minute = Integer.parseInt(secondTimeToken.substring(0, secondTimeTokenLength-1));
    }


    /**
     * two Times are equal if they have the same hour and minute
     * @param obj possibly a Time to compare to
     * @return whether this and the given obj are equal Times
     */
    @Override
    public boolean equals(@Nullable Object obj){
        if(this == obj){
            return true;
        }

        if(obj instanceof Time){
            Time time = (Time) obj;
            return hour == time.hour && minute == time.minute;
        }

        return false;
    }

    /**
     * @return gets the time in total minutes since midnight
     */
    public int getTotalMinutes(){
        return hour*60 + minute;
    }

    /**
     * @return hash code based on this Time's hour and minutes
     */
    @Override
    public int hashCode(){
        return hour*60 + minute;
    }


    /**
     * Times are ordered from 12:00a to 11:59p
     * @param time the other Time
     * @return how this Time compares to the given Time
     */
    @Override
    public int compareTo(Time time){
        int thisTime = hour*60 + minute;
        int otherTime = time.hour*60 + time.minute;
        return thisTime - otherTime;
    }

    /**
     * @return this time in hh:mm[a/p] format
     */
    public String toString(){
        return stringFormat;
    }
}
