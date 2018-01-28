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
public class PublicHoliday {
    /**************
     *            *
     * Properties * 
     *            *
     **************/
    public int ID;
    public String title;
    public String date;
    public String type;
    public boolean repeat;

    /***************
     *             *
     * Constructor * 
     *             *
     ***************/
    public PublicHoliday(int _holidayID, String _title, String _date,String _type,boolean _repeat) {
        ID = _holidayID;
        title = _title;
        date = _date;
        type = _type;
        repeat = _repeat;
    }
    
    /*************
     *           *
     * Functions * 
     *           *
     *************/
    public static PublicHoliday getHoliday(int _ID) {
        return (PublicHoliday) JsonFileManager.getInstance().retrieveObject(PublicHoliday.class,"ID",_ID);
    }
    
    public static List<PublicHoliday> getAllPublicHoliday() {
        List<PublicHoliday> ph = (List)JsonFileManager.getInstance().retrieveMultipleObject(PublicHoliday.class, null, null);
        return ph;
    }
    
    /******************
     *                *
     * CRUD Operation *
     *                *
     ******************/
    public static boolean createPublicHoliday(String _name, String _date, String _type, boolean _repeat) {
        PublicHoliday ph = new PublicHoliday(0, _name,  _date,_type, _repeat);
        return JsonFileManager.getInstance().createObject(PublicHoliday.class, ph);
    }

    public static boolean editPublicHoliday(int _id, String _name, String _date, String _type, boolean _repeat) {
        PublicHoliday ph = new PublicHoliday(_id, _name,  _date,_type, _repeat);
        return JsonFileManager.getInstance().updateObject(PublicHoliday.class, ph);
    }

    public static boolean deletePublicHoliday(int _id) {
        return JsonFileManager.getInstance().deleteObject(PublicHoliday.class, _id);
    }
    
}
