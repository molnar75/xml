package beadando;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XSLTapp {
	private static final String INPUT_XSL_FILE_PATH = "data/megrendeles.xsl";
	private static final String INPUT_XML_FILE_PATH = "data/megrendeles.xml";
	public static void main(String args[]){
		TransformerFactory transformerFactory=TransformerFactory.newInstance();
		StreamSource xslStream=new StreamSource(INPUT_XSL_FILE_PATH);
		
		StreamSource in=new StreamSource(INPUT_XML_FILE_PATH );
		StreamResult out=new StreamResult("eredmeny.html");
		StreamResult outConsol=new StreamResult(System.out);
		try{
			Transformer transformer=transformerFactory.newTransformer(xslStream);

			transformer.transform(in, out);
			transformer.transform(in, outConsol);
		}catch(TransformerException e){
			e.printStackTrace();
		}
	}

}
