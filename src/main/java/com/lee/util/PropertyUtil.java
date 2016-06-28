package com.lee.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class PropertyUtil
{
  Properties props = new Properties();
  InputStream in = null;
  
  public PropertyUtil(String file)
  {
    try
    {
      this.in = getClass().getClassLoader().getResourceAsStream(file);
      this.props.load(this.in);
      this.in.close();
    }
    catch (Exception localException) {}
  }
  
  public String getValue(String name)
  {
    return this.props.getProperty(name);
  }
  
  public Map<String, String> getAllValue(Map<String, String> mp)
  {
    Iterator<Map.Entry<Object, Object>> it = this.props.entrySet().iterator();
    while (it.hasNext())
    {
      Map.Entry<Object, Object> entry = (Map.Entry)it.next();
      mp.put((String)entry.getKey(), (String)entry.getValue());
    }
    return mp;
  }
  
  public Map<String, String> getAllValue()
  {
    Map<String, String> mp = new HashMap();
    Iterator<Map.Entry<Object, Object>> it = this.props.entrySet().iterator();
    while (it.hasNext())
    {
      Map.Entry<Object, Object> entry = (Map.Entry)it.next();
      mp.put((String)entry.getKey(), (String)entry.getValue());
    }
    return mp;
  }
  
  public static String getConfigValue(String key){
	  PropertyUtil p = new PropertyUtil("properties.properties");
	  return p.getValue(key);
  }
}
