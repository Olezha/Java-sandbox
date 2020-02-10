package edu.olezha.sandbox.problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Meeting implements Comparable<Meeting> {

    // These integers represent the number of 30-minute blocks past 9:00am
    private int startTime;
    private int endTime;

    public Meeting(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime   = endTime;
    }

    /**
     * @param meetings a list of multiple meeting time ranges
     * @return a list of condensed ranges
     */
    public static List<Meeting> mergeRanges(List<Meeting> meetings) {
        if (meetings.size() == 0) return meetings;
        Collections.sort(meetings);

        Meeting lastMeeting = meetings.get(0);
        List<Meeting> result = new ArrayList<>();
        for (Meeting meeting : meetings) {
            if (lastMeeting.endTime >= meeting.startTime) {
                lastMeeting.endTime = Math.max(lastMeeting.endTime, meeting.endTime);
            } else {
                result.add(lastMeeting);
                lastMeeting = meeting;
            }
        }
        result.add(lastMeeting);

        return result;
    }

    @Override
    public int compareTo(Meeting o) {
        return startTime - o.startTime;
    }

    @Override
    public String toString() {
        return "Meeting(" + startTime + ", " + endTime + ')';
    }

    public static void main(String[] args) {
        Meeting meeting = new Meeting(2, 3);  // meeting from 10:00 – 10:30 am
        Meeting meeting1 = new Meeting(6, 9);  // meeting from 12:00 – 1:30 pm
        List<Meeting> meetings = mergeRanges(new ArrayList<Meeting>() {{
            add(meeting);
            add(meeting1);
            add(new Meeting(12, 14));
            add(new Meeting(3, 4));
            add(new Meeting(5, 10));
            add(new Meeting(4, 5));
        }});
        System.out.println(meetings);

        System.out.println(mergeRanges(new ArrayList<Meeting>() {{
            add(new Meeting(0, 1));
            add(new Meeting(3, 5));
            add(new Meeting(4, 8));
            add(new Meeting(10, 12));
            add(new Meeting(9, 10));
        }}));
    }
}
