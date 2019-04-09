package org.jbpm.pvm.internal.lob;
 
import java.sql.SQLException;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jbpm.api.JbpmException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
 
public class BlobStrategyBlob implements BlobStrategy {
  
  public void set(byte[] bytes, Lob lob) {
    if (bytes!=null) {
      lob.cachedBytes = bytes;
      //lob.blob = Hibernate.createBlob(bytes); --源码(hinernate3)--seven
      //lob.blob = ((SessionFactory)SpringContextUtil.getBean("sessionFactory")).getCurrentSession().getLobHelper().createBlob(bytes);
      ApplicationContext ac =  WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());  
      SessionFactory sessionFactory = (SessionFactory)ac.getBean("sessionFactory");  
      Session session = sessionFactory.getCurrentSession();  
      lob.blob = session.getLobHelper().createBlob(bytes);
    }
  }
 
  public byte[] get(Lob lob) {
    if (lob.cachedBytes!=null) {
      return lob.cachedBytes;
    }
    
    java.sql.Blob sqlBlob = lob.blob;
    if (sqlBlob!=null) {
      try {
        return sqlBlob.getBytes(1, (int) sqlBlob.length());
      } catch (SQLException e) {
        throw new JbpmException("couldn't extract bytes out of blob", e);
      }
    } 
    return null;
  }
}