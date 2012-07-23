package com.messagecenter.utils.xml;

import java.io.File;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;

public abstract class XmlFile {
    //根节点名字
    protected String root;
    //xml文件路径
    protected String path;

    protected Logger logger = Logger.getLogger (this.getClass ());

    protected Document getDocument () {

        if (null == path) {
            logger.error (" path is null!");
            return null;
        }
        Document doc = null;
        File config = new File (path);
        try {
            SAXReader reader = new SAXReader ();
            reader.setEncoding ("UTF-8");
            doc = reader.read (config);
        }
        catch (Exception e) {
            logger.error (e);
            return null;
        }
        return doc;
    }

    protected boolean init () {

        // TODO Auto-generated method stub
        return false;
    }
}
