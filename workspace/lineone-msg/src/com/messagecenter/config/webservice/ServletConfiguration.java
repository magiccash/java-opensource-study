package com.messagecenter.config.webservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;

import com.messagecenter.utils.xml.XmlFile;

public class ServletConfiguration extends XmlFile {

    private static final String               DEFAULT_PATH = "web/web.xml";

    private Map <String, String>              servletMap   = new HashMap <String, String> ();

    private static final ServletConfiguration instance     = new ServletConfiguration ();

    public static ServletConfiguration getInstance () {

        return instance;
    }

    public Map <String, String> getServletMap () {

        return servletMap;
    }

    private ServletConfiguration () {

        super.path = DEFAULT_PATH;
        super.root = "web";
        init ();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected boolean init () {

        Document doc = getDocument ();
        if (doc == null) {
            logger.error ("Something wrong while parsing xml!");
            System.exit (1);
        }

        Element root = doc.getRootElement ();
        if (root == null) {
            logger.error ("Something wrong while parsing xml!");
            System.exit (1);
        }

        List <Element> servlets = root.elements ("servlet");
        for (Element servlet : servlets) {
            Element class_path = servlet.element ("class");
            String classText = class_path.getText ();
            try {
                Class < ? > c = Class.forName (classText);
                c.newInstance ();
                Element mapping = servlet.element ("mapping");
                String map_text = mapping.getText ();
                if (servletMap.containsKey (map_text)) {
                    logger.error ("The same mapping exist:" + map_text);
                    System.exit (1);
                }
                logger.info ("add servlet " + c.getName ());
                servletMap.put (map_text, c.getName ());
            }
            catch (ClassNotFoundException e) {
                logger.error (classText, e);
            }
            catch (InstantiationException e) {
                logger.error (classText + "create instance error!", e);
            }
            catch (IllegalAccessException e) {
                logger.error (classText + "create instance error!", e);
            }
        }
        return true;
    }
}
