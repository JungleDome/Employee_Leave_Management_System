/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elms;

import java.util.List;

/**
 *
 * @author WinDow_98
 */
public class Leave {

    //Basic encapsulation by using getter and setter
    /**************
     *            *
     * Properties * 
     *            *
     **************/
    private int ID;
    private String title;
    private int type;
    private String startDate;
    private String endDate;
    private int duration;
    private String status;
    private String description;
    private int userID;

    /***************
     *             *
     * Constructor * 
     *             *
     ***************/
    public Leave(int _leaveID, String _title, int _type, String _startDate, String _endDate, int _duration, String _status, String _description, int _userID) {
        ID = _leaveID;
        title = _title;
        type = _type;
        startDate = _startDate;
        endDate = _endDate;
        duration = _duration;
        status = _status;
        description = _description;
        userID = _userID;
    }

    /*********************
     *                   *
     * Getters & Setters * 
     *                   *
     *********************/
    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public int getType() {
        return type;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getDuration() {
        return duration;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public int getUserID() {
        return userID;
    }

    public void setID(int _ID) {
        ID = _ID;
    }

    private void setStatus(String _status) {
        status = _status;
    }

    /*************
     *           *
     * Functions * 
     *           *
     *************/
    public User getUser() {
        return (User) JsonFileManager.getInstance().retrieveObject(User.class, "ID", this.getUserID());
    }

    public static List<Leave> getUnprocessedLeave() {
        return (List) JsonFileManager.getInstance().retrieveMultipleObject(Leave.class, "status", "Pending");
    }

    public static Leave getCertainLeave(int _leaveID) {
        return (Leave) JsonFileManager.getInstance().retrieveObject(Leave.class, "ID", _leaveID);
    }

    //Method overloading using different parameter datatype.
    public static List<Leave> getUserLeave(int _userID) {
        return (List) JsonFileManager.getInstance().retrieveMultipleObject(Leave.class, "userID", _userID);
    }

    public static List<Leave> getUserLeave(String _username) {
        User u = User.getUserInfo(_username);
        return (List) JsonFileManager.getInstance().retrieveMultipleObject(Leave.class, "userID", u.ID);
    }

    public static boolean setLeaveStatus(int _leaveID, String _status) {
        Leave a = getCertainLeave(_leaveID);
        a.setStatus(_status);
        return JsonFileManager.getInstance().updateObject(Leave.class, a);
    }

    /******************
     *                *
     * CRUD Operation * 
     *                *
     ******************/
    public static boolean createLeave(String _title, int _type, String _startDate, String _endDate, int _duration, String _status, String _description, int _userID) {
        Leave a = new Leave(0, _title, _type, _startDate, _endDate, _duration, _status, _description, _userID);
        return JsonFileManager.getInstance().createObject(Leave.class, a);
    }

    public static boolean editLeave(int _leaveID, String _title, int _type, String _startDate, String _endDate, int _duration, String _status, String _description, int _userID) {
        Leave a = new Leave(_leaveID, _title, _type, _startDate, _endDate, _duration, _status, _description, _userID);
        return JsonFileManager.getInstance().updateObject(Leave.class, a);
    }

    public static boolean deleteLeave(int _leaveID) {
        return JsonFileManager.getInstance().deleteObject(Leave.class, _leaveID);
    }

    @Override
    public String toString() {
        return "Leave[ID=" + ID + ",title=" + title + ",description=" + description + ",startDate=" + startDate + ",endDate=" + endDate + ",duration=" + duration + "status=" + status + ",userID=" + userID + "]";
    }
}
