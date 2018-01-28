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
public interface FileManager {
    //Ease for further development using different file parser/file type.
    //Abstract method.
    public Object retrieveObject(Class<?> _type,String _key,Object _value);
    public List<Object> retrieveMultipleObject(Class<?> _type,String _key,Object _value);
    public boolean createObject(Class<?> _type,Object _obj);
    public boolean updateObject(Class<?> _type,Object _obj);
    public boolean deleteObject(Class<?> _type,int _id);
}
