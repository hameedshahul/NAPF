package ch.post.pf.nf.ta.baseutils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jUtil {

	 static Logger log ;
	 
	 public static Logger loadLogger(Class<?> name) {
		log = LoggerFactory.getLogger(name);
		
		 return log;
	 }

}
