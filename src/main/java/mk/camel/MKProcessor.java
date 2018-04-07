package mk.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;

import mk.Book;




public class MKProcessor implements Processor {
//	private Log log = LoggerFactory.getLogger(MKProcessor.class); 

	public void process(Exchange in) throws Exception {
		
		
		Message inMesssage = in.getIn();
		
		Book book = (Book) inMesssage.getBody();
		System.out.println(book);
		
		
		
	}

}
