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
public class FAQ {
    /**************
     *            *
     * Properties *
     *            *
     **************/
    public int ID;
    public String question;
    public String answer;

    /***************
     *             *
     * Constructor *
     *             *
     ***************/
    public FAQ(int _FAQID, String _question, String _answer) {
        ID = _FAQID;
        question = _question;
        answer = _answer;
    }

    /*************
     *           *
     * Functions *
     *           *
     *************/
    public static FAQ getFAQ(int _ID) {
        FAQ faq = (FAQ) JsonFileManager.getInstance().retrieveObject(FAQ.class, "ID", _ID);
        return faq;
    }
    
    public static List<FAQ> getAllFAQ() {
        List<FAQ> listfaq = (List) JsonFileManager.getInstance().retrieveMultipleObject(FAQ.class, null, null);
        return listfaq;
    }
    
    /******************
     *                *
     * CRUD Operation *
     *                *
     ******************/
    public static boolean createFAQ(String _question, String _answer) {
        FAQ faq = new FAQ(0, _question, _answer);
        return JsonFileManager.getInstance().createObject(FAQ.class, faq);
    }

    public static boolean editFAQ(int _id, String _question, String _answer) {
        FAQ faq = new FAQ(_id, _question, _answer);
        return JsonFileManager.getInstance().updateObject(FAQ.class, faq);
    }

    public static boolean deleteFAQ(int _id) {
        return JsonFileManager.getInstance().deleteObject(FAQ.class, _id);
    }

}
