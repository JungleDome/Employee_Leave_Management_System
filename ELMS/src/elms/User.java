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
public class User {
    /**************
     *            *
     * Properties * 
     *            *
     **************/
    private static User instance;
    public int ID;
    public String username;
    public String password;
    public String position;
    public List<LeavePolicy> lp;

    /***************
     *             *
     * Constructor * 
     *             *
     ***************/
    public User(String _username, String _password) {
        username = _username;
        password = _password;
    }

    public User(int _userID, String _username, String _password, String _position) {
        ID = _userID;
        username = _username;
        password = _password;
        position = _position;
    }

    public User(int _userID, String _username, String _password, String _position, List<LeavePolicy> _lp) {
        ID = _userID;
        username = _username;
        password = _password;
        position = _position;
        lp = _lp;
    }

    /*************
     *           *
     * Functions * 
     *           *
     *************/
    public static User getInstance() {
        if (instance != null) {
            return instance;
        } else {
            return null;
        }
    }

    public static boolean login(String username, String password) {
        User tempUser = (User) JsonFileManager.getInstance().retrieveObject(User.class, "username", username);
        if (tempUser!=null&&tempUser.password.equals(password)) {
            instance = tempUser;
            return true;
        } else {
            return false;
        }
    }    

    public static void logout() {
        instance = null;
    }
    
    public static void addLeave(int _userID,int _policyID) {
        User u = getUserInfo(_userID);
        for (LeavePolicy lp :u.lp) {
            if (lp.ID==_policyID) {
                System.out.println(u);
                lp.amountLeft++;
            }
        }
        editUser(u.ID, u.username, u.password, u.position, u.lp);
    }
    
    public static void addLeave(int _userID,String _policyName) {
        User u = getUserInfo(_userID);
        for (LeavePolicy lp :u.lp) {
            if (lp.name.equalsIgnoreCase(_policyName)) {
                lp.amountLeft++;
            }
        }
        editUser(u.ID, u.username, u.password, u.position, u.lp);
    }
    
    public static void reduceLeave(int _userID,int _policyID,int _duration) {
        User u = getUserInfo(_userID);
        for (LeavePolicy lp :u.lp) {
            if (lp.ID==_policyID) {
                lp.amountLeft-=_duration;
            }
        }
        editUser(u.ID, u.username, u.password, u.position, u.lp);
    }
    
    public static void reduceLeave(int _userID,String _policyName, int _duration) {
        User u = getUserInfo(_userID);
        for (LeavePolicy lp :u.lp) {
            if (lp.name.equalsIgnoreCase(_policyName)) {
                lp.amountLeft-=_duration;
            }
        }
        editUser(u.ID, u.username, u.password, u.position, u.lp);
    }
    
//    public static void changeLeaveType(int _userID,int _oldPolicyID,int _newPolicyID) {
//        User u = getUserInfo(_userID);
//        for (LeavePolicy lp :u.lp) {
//            if (lp.ID==_oldPolicyID) {
//                lp.amountLeft++;
//            } else if (lp.ID==_newPolicyID) {
//                lp.amountLeft--;
//            }
//        }
//        editUser(u.ID, u.username, u.password, u.position, u.lp);
//    }
    
    public static void changeLeaveType(int _userID,String _oldPolicyName,String _newPolicyName) {
        User u = getUserInfo(_userID);
        for (LeavePolicy lp :u.lp) {
            if (lp.name.equalsIgnoreCase(_oldPolicyName)) {
                lp.amountLeft++;
            } else if (lp.name.equalsIgnoreCase(_newPolicyName)) {
                lp.amountLeft--;
            }
        }
        editUser(u.ID, u.username, u.password, u.position, u.lp);
    }
    
    public static List<User> getAllLecturer() {
        return (List) JsonFileManager.getInstance().retrieveMultipleObject(User.class,"Position","L");
    }

    public static User getUserInfo(int _userID) {
        User user = (User) JsonFileManager.getInstance().retrieveObject(User.class, "ID", _userID);
        return user;
    }

    public static User getUserInfo(String _username) {
        User user = (User) JsonFileManager.getInstance().retrieveObject(User.class, "username", _username);
        return user;
    }
    
    /******************
     *                *
     * CRUD Operation *
     *                *
     ******************/
    public static boolean createUser(String _username, String _password, String _position) {
        User registerUser = new User(0, _username, _password, _position, LeavePolicy.createUserLeave());
        return JsonFileManager.getInstance().createObject(User.class, registerUser);
    }

    public static boolean editUser(int _userID, String _username, String _password, String _position, List<LeavePolicy> _lp) {
        User editUser = new User(_userID, _username, _password, _position,_lp);
        return JsonFileManager.getInstance().updateObject(User.class, editUser);
    }

    public static boolean deleteUser(int _id) {
        return JsonFileManager.getInstance().deleteObject(User.class, _id);
    }

    @Override
    public String toString() {
        return "User[ID=" + ID + ",username=" + username + ",password=" + password + ",position=" + position + "LP=" + lp +"]";
    }
}
