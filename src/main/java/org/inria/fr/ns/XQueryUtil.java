package org.inria.fr.ns;


import net.xqj.exist.ExistXQDataSource;

import javax.xml.xquery.*;

public class XQueryUtil {
    private XQDataSource xqs;
    private XQConnection conn;
    private XQPreparedExpression xqpe;
    private XQResultSequence rs;

    public void connect() throws XQException {
        xqs = new ExistXQDataSource();
        xqs.setProperty("serverName", "localhost");
        xqs.setProperty("port", "8080");
        conn = xqs.getConnection();
    }
    public void setXQueryReq(String req) throws XQException {
        xqpe = conn.prepareExpression(req);
    }

    public XQResultSequence getResult() throws XQException {
        rs = xqpe.executeQuery();
        return rs;
    }
    public void closeConn() throws XQException {
        conn.close();
    }
}

