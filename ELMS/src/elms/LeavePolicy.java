/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elms;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author WinDow_98
 */
public class LeavePolicy {
    /**************
     *            *
     * Properties * 
     *            *
     **************/
    public int ID;
    public String name;
    public int initialValue;
    public int amountLeft;
    public int maxCarryForward;
    public int amountEarnedPerYear;

    /***************
     *             *
     * Constructor * 
     *             *
     ***************/
    public LeavePolicy(int _ID) {
        LeavePolicy lp = getPolicy(_ID);
        ID = lp.ID;
        name = lp.name;
        initialValue = lp.initialValue;
        amountLeft = lp.amountLeft;
        maxCarryForward = lp.maxCarryForward;
        amountEarnedPerYear = lp.amountEarnedPerYear;
    }
    
    public LeavePolicy(String _name) {
        LeavePolicy lp = getPolicy(_name);
        ID = lp.ID;
        name = lp.name;
        initialValue = lp.initialValue;
        amountLeft = lp.amountLeft;
        maxCarryForward = lp.maxCarryForward;
        amountEarnedPerYear = lp.amountEarnedPerYear;
    }
    
    LeavePolicy(int _leaveID, String _name,int _initialValue,int _amountLeft,int _maxCarryForward, int _amountEarnedPerYear) {
        ID = _leaveID;
        name = _name;
        initialValue = _initialValue;
        amountLeft = _amountLeft;
        maxCarryForward = _maxCarryForward;
        amountEarnedPerYear = _amountEarnedPerYear;
    }
    
    /*************
     *           *
     * Functions * 
     *           *
     *************/
    private LeavePolicy getPolicy(int ID) {
        LeavePolicy lp = (LeavePolicy) JsonFileManager.getInstance().retrieveObject(LeavePolicy.class, "ID", ID);
        return lp;
    }
    
    private LeavePolicy getPolicy(String _name) {
        LeavePolicy lp = (LeavePolicy) JsonFileManager.getInstance().retrieveObject(LeavePolicy.class, "name", _name);
        return lp;
    }

    public static List<LeavePolicy> getAllPolicy() {
        List<LeavePolicy> lp = (List) JsonFileManager.getInstance().retrieveMultipleObject(LeavePolicy.class, null, null);
        return lp;
    }
    
    public static List<String> getAllPolicyName() {
        List<LeavePolicy> lp = (List) JsonFileManager.getInstance().retrieveMultipleObject(LeavePolicy.class, null, null);
        List<String> name = new ArrayList<>();
        for (LeavePolicy _lp : lp) {
            name.add(_lp.name);
        }
        return name;
    }
    
    public static List<LeavePolicy> createUserLeave() {
        List<LeavePolicy> dbLP = getAllPolicy();
        List<LeavePolicy> userLP = new ArrayList<>();
        for (LeavePolicy _dbLP: dbLP) {
            LeavePolicy tempLP = new LeavePolicy(_dbLP.ID,_dbLP.name,0,_dbLP.initialValue,_dbLP.maxCarryForward,_dbLP.amountEarnedPerYear);
            userLP.add(tempLP);
        }
        return userLP;
    }
    
    /******************
     *                *
     * CRUD Operation *
     *                *
     ******************/
    public static boolean createLeavePolicy(String _name,int _initialValue,int _maxCarryForward, int _amountEarnedPerYear) {
        LeavePolicy lp = new LeavePolicy(0, _name,_initialValue,0, _maxCarryForward, _amountEarnedPerYear);
        return JsonFileManager.getInstance().createObject(LeavePolicy.class, lp);
    }

    public static boolean editLeavePolicy(int _id,String _name,int _initialValue,int _maxCarryForward, int _amountEarnedPerYear) {
        LeavePolicy lp = new LeavePolicy(_id, _name,_initialValue,0, _maxCarryForward, _amountEarnedPerYear);
        return JsonFileManager.getInstance().updateObject(LeavePolicy.class, lp);
    }

    public static boolean deleteLeavePolicy(int _id) {
        return JsonFileManager.getInstance().deleteObject(LeavePolicy.class, _id);
    }
    
    @Override
    public String toString() {
        return "[ID = "+ID + ",Name = "+ name +",InitVal = " + initialValue +",AmountLeft = " + amountLeft +"]";
    }
}
