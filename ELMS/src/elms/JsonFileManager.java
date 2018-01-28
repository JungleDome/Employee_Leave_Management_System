/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elms;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WinDow_98
 */
public class JsonFileManager implements FileManager {

    private String filePath;
    private String fileName;
    private List<FAQ> M_Faq;
    private List<PublicHoliday> M_ph;
    private List<LeavePolicy> M_lp;
    private List<User> M_user;
    private List<Leave> M_leave;
    private static JsonFileManager instance;

    JsonFileManager(String _filePath, String _fileName) throws Exception {
        filePath = _filePath;
        fileName = _fileName;
        File file = new File(_filePath + _fileName);
        try {
            //Load all text from text file provided
            Scanner s = new Scanner(file);
            String loadedTextFile = "";
            while (s.hasNext()) {
                loadedTextFile += s.nextLine();
            }
            s.close();
            System.out.println(loadedTextFile);
            //Init json parser
            Gson gson = new GsonBuilder().serializeNulls().create();
            JsonParser parser = new JsonParser();
            JsonArray array = parser.parse(loadedTextFile).getAsJsonArray();
            //Converting JSON to their responding class in array form.
            M_Faq = gson.fromJson(array.get(0), new TypeToken<List<FAQ>>() {
            }.getType());
            M_ph = gson.fromJson(array.get(1), new TypeToken<List<PublicHoliday>>() {
            }.getType());
            M_lp = gson.fromJson(array.get(2), new TypeToken<List<LeavePolicy>>() {
            }.getType());
            M_user = gson.fromJson(array.get(3), new TypeToken<List<User>>() {
            }.getType());
            M_leave = gson.fromJson(array.get(4), new TypeToken<List<Leave>>() {
            }.getType());

            instance = this;
            //Check is new year?
            Date lastModified = new Date(file.lastModified());
            Calendar c = Calendar.getInstance();
            c.setTime(lastModified);
            int modifiedYear = c.get(Calendar.YEAR);
            c.setTime(new Date());
            int currentYear = c.get(Calendar.YEAR);
            if (modifiedYear < currentYear) {
                //isNewYear
                loopUserAndHoliday();
            }
        } catch (FileNotFoundException | java.lang.IllegalStateException ex) {
            throw ex;
        }
    }

    public static JsonFileManager getInstance() {
        return instance;
    }

    public Object retrieveObject(Class<?> _type, String _key, Object _value) {
        String className = _type.getSimpleName();
        switch (className) {
            case "User":
                for (User a : M_user) {
                    try {
                        Object value = User.class.getField(_key).get(a);
                        if (value.equals(_value)) {
                            return a;
                        } else {
                        }
                    } catch (NoSuchFieldException ex) {
                        return null;
                    } catch (IllegalArgumentException | IllegalAccessException ex) {
                        Logger.getLogger(JsonFileManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                Logger.getLogger(JsonFileManager.class.getName()).log(Level.FINE, "aaa");
                break;
            case "FAQ":
                for (FAQ a : M_Faq) {
                    try {
                        Object value = FAQ.class.getField(_key).get(a);
                        if (value.equals(_value)) {
                            return a;
                        } else {
                        }
                    } catch (NoSuchFieldException ex) {
                        return null;
                    }catch (IllegalArgumentException | IllegalAccessException ex) {
                        Logger.getLogger(JsonFileManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case "Leave":
                for (Leave a : M_leave) {
                    try {
                        Method[] methods = Leave.class.getMethods();
                        for (Method method : methods) {
                            if (method.getName().equalsIgnoreCase("get" + _key)) {
                                try {
                                    if (method.invoke(a).equals(_value)) {
                                        return a;
                                    } else {
                                    }
                                } catch (InvocationTargetException ex) {
                                    Logger.getLogger(JsonFileManager.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                            }
                        }
                    } catch (SecurityException ex) {
                        return null;
                    } catch (IllegalArgumentException | IllegalAccessException ex) {
                        Logger.getLogger(JsonFileManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case "LeavePolicy":
                for (LeavePolicy a : M_lp) {
                    try {
                        Object value = LeavePolicy.class.getField(_key).get(a);
                        if (value.equals(_value)) {
                            return a;
                        } else {
                        }
                    } catch (NoSuchFieldException ex) {
                        return null;
                    } catch (IllegalArgumentException | IllegalAccessException ex) {
                        Logger.getLogger(JsonFileManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case "PublicHoliday":
                for (PublicHoliday a : M_ph) {
                    try {
                        Object value = PublicHoliday.class.getField(_key).get(a);
                        if (value.equals(_value)) {
                            return a;
                        } else {
                        }
                    } catch (NoSuchFieldException ex) {
                        return null;
                    } catch (IllegalArgumentException | IllegalAccessException ex) {
                        Logger.getLogger(JsonFileManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
        }
        return null;
    }

    public List<Object> retrieveMultipleObject(Class<?> _type, String _key, Object _value) {
        String className = _type.getSimpleName();
        List<Object> tempList = new ArrayList<>();
        switch (className) {
            case "User":
                for (User a : M_user) {
                    if (_key == null) {
                        return (List) M_user;
                    } else {
                        try {
                            Object value = User.class.getField(_key).get(a);
                            if (value.equals(_value)) {
                                tempList.add(a);
                            }
                        } catch (NoSuchFieldException ex) {
                            return (List) M_user;
                        } catch (IllegalArgumentException | IllegalAccessException ex) {
                            Logger.getLogger(JsonFileManager.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                break;
            case "FAQ":
                for (FAQ a : M_Faq) {
                    if (_key == null) {
                        return (List) M_Faq;
                    } else {
                        try {
                            Object value = FAQ.class.getField(_key).get(a);
                            if (value.equals(_value)) {
                                tempList.add(a);
                            }
                        } catch (NoSuchFieldException ex) {
                            return (List) M_Faq;
                        } catch (IllegalArgumentException | IllegalAccessException ex) {
                            Logger.getLogger(JsonFileManager.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                break;

            case "Leave":
                for (Leave a : M_leave) {
                    if (_key == null) {
                        return (List) M_leave;
                    } else {
                        try {
                            Method[] methods = Leave.class.getMethods();
                            for (Method method : methods) {
                                if (method.getName().equalsIgnoreCase("get" + _key)) {
                                    try {
                                        if (method.invoke(a).equals(_value)) {
                                            tempList.add(a);
                                        } else {
                                        }
                                    } catch (InvocationTargetException ex) {
                                        Logger.getLogger(JsonFileManager.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else {
                                }
                            }
                        } catch (IllegalArgumentException | IllegalAccessException ex) {
                            Logger.getLogger(JsonFileManager.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                break;
            case "LeavePolicy":
                for (LeavePolicy a : M_lp) {
                    if (_key == null) {
                        return (List) M_lp;
                    } else {
                        try {
                            Object value = LeavePolicy.class.getField(_key).get(a);
                            if (value.equals(_value)) {
                                tempList.add(a);
                            } else {
                                return null;
                            }
                        } catch (NoSuchFieldException ex) {
                            return (List) M_lp;
                        } catch (IllegalArgumentException | IllegalAccessException ex) {
                            Logger.getLogger(JsonFileManager.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                break;
            case "PublicHoliday":
                for (PublicHoliday a : M_ph) {
                    if (_key == null) {
                        return (List) M_ph;
                    } else {
                        try {
                            Object value = PublicHoliday.class.getField(_key).get(a);
                            if (value.equals(_value)) {
                                tempList.add(a);
                            }
                        } catch (NoSuchFieldException ex) {
                            return (List) M_ph;
                        } catch (IllegalArgumentException | IllegalAccessException ex) {
                            Logger.getLogger(JsonFileManager.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                break;
        }
        return tempList;
    }

    public boolean createObject(Class<?> _type, Object _obj) {
        //TODO:Check if item exist or not.
        String className = _type.getSimpleName();
        switch (className) {
            case "User":
                User castedObj = (User) _obj;
                int latestID = 0;
                for (User user : M_user) {
                    if (user.ID > latestID) {
                        latestID = user.ID;
                    }
                }
                castedObj.ID = latestID + 1;
                M_user.add(castedObj);
                break;
            case "FAQ":
                FAQ castedObj1 = (FAQ) _obj;
                int latestID1 = 0;
                for (FAQ faq : M_Faq) {
                    if (faq.ID > latestID1) {
                        latestID1 = faq.ID;
                    }
                }
                castedObj1.ID = latestID1 + 1;
                M_Faq.add(castedObj1);
                break;
            case "Leave":
                Leave castedObj2 = (Leave) _obj;
                int latestID2 = 0;
                for (Leave l : M_leave) {
                    if (l.getID() > latestID2) {
                        latestID2 = l.getID();
                    }
                }
                castedObj2.setID(latestID2 + 1);
                M_leave.add(castedObj2);
                break;
            case "LeavePolicy":
                LeavePolicy castedObj3 = (LeavePolicy) _obj;
                int latestID3 = 0;
                for (LeavePolicy l : M_lp) {
                    if (l.ID > latestID3) {
                        latestID3 = l.ID;
                    }
                }
                castedObj3.ID = latestID3 + 1;
                M_lp.add(castedObj3);
                break;
            case "PublicHoliday":
                PublicHoliday castedObj4 = (PublicHoliday) _obj;
                int latestID4 = 0;
                for (PublicHoliday l : M_ph) {
                    if (l.ID > latestID4) {
                        latestID4 = l.ID;
                    }
                }
                castedObj4.ID = latestID4 + 1;
                M_ph.add(castedObj4);
                break;
        }
        return saveFile() == null;
    }

    public boolean updateObject(Class<?> _type, Object _obj) {
        //Search for same id.
        String className = _type.getSimpleName();
        boolean successUpdate = false;
        switch (className) {
            case "User":
                User editUser = (User) _obj;
                for (User a : M_user) {
                    if (a.ID == editUser.ID) {
                        M_user.set(M_user.indexOf(a), editUser);
                        successUpdate = true;
                    }
                }
                break;
            case "FAQ":
                FAQ editFaq = (FAQ) _obj;
                for (FAQ a : M_Faq) {
                    if (a.ID == editFaq.ID) {
                        M_Faq.set(M_Faq.indexOf(a), editFaq);
                        successUpdate = true;
                    }
                }
                break;
            case "Leave":
                Leave editLeave = (Leave) _obj;
                for (Leave a : M_leave) {
                    if (a.getID() == editLeave.getID()) {
                        M_leave.set(M_leave.indexOf(a), editLeave);
                        successUpdate = true;
                    }
                }
                break;
            case "LeavePolicy":
                LeavePolicy editLP = (LeavePolicy) _obj;
                for (LeavePolicy a : M_lp) {
                    if (a.ID == editLP.ID) {
                        M_lp.set(M_lp.indexOf(a), editLP);
                        successUpdate = true;
                    }
                }
                break;
            case "PublicHoliday":
                PublicHoliday editPH = (PublicHoliday) _obj;
                for (PublicHoliday a : M_ph) {
                    if (a.ID == editPH.ID) {
                        M_ph.set(M_ph.indexOf(a), editPH);
                        successUpdate = true;
                    }
                }
                break;
        }
        return successUpdate == true && saveFile() == null;
    }

    public boolean deleteObject(Class<?> _type, int _id) {
        String className = _type.getSimpleName();
        boolean successDelete = false;
        switch (className) {
            case "User":
                List<User> toRemove = new ArrayList<>();
                for (User usr : M_user) {
                    if (usr.ID == _id) {
                        toRemove.add(usr);
                    }
                }
                successDelete = M_user.removeAll(toRemove);
                break;
            case "FAQ":
                List<FAQ> toRemove1 = new ArrayList<>();
                for (FAQ usr : M_Faq) {
                    if (usr.ID == _id) {
                        toRemove1.add(usr);
                    }
                }
                successDelete = M_Faq.removeAll(toRemove1);
                break;
            case "Leave":
                List<Leave> toRemove2 = new ArrayList<>();
                for (Leave usr : M_leave) {
                    if (usr.getID() == _id) {
                        toRemove2.add(usr);
                    }
                }
                successDelete = M_leave.removeAll(toRemove2);
                break;
            case "LeavePolicy":
                List<LeavePolicy> toRemove3 = new ArrayList<>();
                for (LeavePolicy usr : M_lp) {
                    if (usr.ID == _id) {
                        toRemove3.add(usr);
                    }
                }
                successDelete = M_lp.removeAll(toRemove3);
                break;
            case "PublicHoliday":
                List<PublicHoliday> toRemove4 = new ArrayList<>();
                for (PublicHoliday usr : M_ph) {
                    if (usr.ID == _id) {
                        toRemove4.add(usr);
                    }
                }
                successDelete = M_ph.removeAll(toRemove4);
                break;
        }
        return successDelete == true && saveFile() == null;
    }

    private Exception saveFile() {
        //Init json parser
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.excludeFieldsWithModifiers(Modifier.STATIC);
        Gson gson = gsonBuilder.create();
        //Converting json to string
        String serializedFAQ = gson.toJson(M_Faq);
        String serializedPH = gson.toJson(M_ph);
        String serializedLP = gson.toJson(M_lp);
        String serializeduser = gson.toJson(M_user);
        String serializedleave = gson.toJson(M_leave);

        //Saving all string into text file
        try {
            PrintWriter outputFile = new PrintWriter(filePath + fileName);
            outputFile.println("[" + serializedFAQ + "," + serializedPH + "," + serializedLP + "," + serializeduser + "," + serializedleave + "]");
            outputFile.close();
            return null;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JsonFileManager.class.getName()).log(Level.SEVERE, null, ex);
            return ex;
        }
    }

    private void loopUserAndHoliday() {
        List<User> u = (List) retrieveMultipleObject(User.class, null, null);
        List<PublicHoliday> p = (List) retrieveMultipleObject(PublicHoliday.class, null, null);

        for (User _u : u) {
            for (LeavePolicy _lp : _u.lp) {
                if (_lp.amountLeft < _lp.maxCarryForward) {
                    _lp.amountLeft = _lp.amountLeft;
                } else {
                    _lp.amountLeft = _lp.maxCarryForward;
                }
                _lp.amountLeft += _lp.amountEarnedPerYear;
            }
        }

        for (PublicHoliday _p : p) {
            if (_p.repeat) {
                LocalDate ld = LocalDate.parse(_p.date);
                PublicHoliday ph = new PublicHoliday(0, _p.title, ld.plusYears(1).toString(),_p.type, _p.repeat);
                M_ph.add(ph);
            }
        }
    }
}
