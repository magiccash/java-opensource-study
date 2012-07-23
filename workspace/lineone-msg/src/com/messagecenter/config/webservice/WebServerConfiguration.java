
package com.messagecenter.config.webservice;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

import com.messagecenter.utils.xml.XmlFile;

/**
 * 
 * @author magiccash
 *
 */
public class WebServerConfiguration extends XmlFile {

    private static WebServerConfiguration instance        = new WebServerConfiguration ();
    private static final String            DEFAULT_PATH   = "web/server.xml";
    
    private String  address;
    private int  port;
    private String default_servlet = "/*.html";
    
    public String getDefault_servlet() {
		return default_servlet;
	}

	private Map<String,String> params = new HashMap<String,String>();

    private WebServerConfiguration(){
    	super.path = DEFAULT_PATH;
    	super.root = "server";
    	init();
    }
    
    public static final WebServerConfiguration getInstance () {

        return instance;
    }

    public int getPort () {

        return port;
    }

    public String getAddress () {
        return address;

    }
    
    public Map<String,String> getParams(){
    	return Collections.unmodifiableMap(params);
    }

	@SuppressWarnings("unchecked")
    @Override
	protected boolean init() {
			if (path == null) {
				logger.error ("path is null!");
				System.exit(1);
			}
			this.params.clear();
			Document doc = getDocument();
			if (doc == null) {
				logger.error("Something wrong while parsing xml!");
				System.exit(1);
			}
			Element root = doc.getRootElement();
			if (root == null)
				System.exit(1);
			this.address = root.attributeValue("address");
			if(null == this.address || this.address.trim().length() == 0){
				logger.error("Address cannot be null!");
				System.exit(1);
			}
			String temp_port = root.attributeValue("port");
			if(null != temp_port)
				this.port = Integer.valueOf(temp_port);
			logger.info("parse server.xml address="+this.address +",port="+this.port);
			List<Element> nodes = root.elements("param");
			Attribute param_name;
			Attribute param_value;
			for(Element node:nodes){
				param_name  = node.attribute("name");
				param_value = node.attribute("value");
				if(null != param_name && param_name.getValue().trim().length() ==0)
					continue;
				if(null != param_value && param_value.getValue().trim().length() ==0)
					continue;
				this.params.put(param_name.getValue().trim(), param_value.getValue().trim());
			}
			Element dfservlet = root.element("default-servlet");
			if(dfservlet.getText()!=null)
				default_servlet = dfservlet.getText();
		return true;
	}
}
