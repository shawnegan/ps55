package pkgMain;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import pkgExcpetion.BookException;
import pkgLibrary.Book;
import pkgLibrary.Catalog;

public class XMLReader {

	public static void main(String[] args) {

		Catalog cat = null;
		
		//	Read the XML catalog into 'cat'
		cat = ReadCatalog();
		
		//	Increase the price, set cost of each book
		IncreasePrice(cat,0.10);
		
		//	Write the XML file from 'cat' object
		WriteXMLFile(cat);

	}


	
	
	private static Catalog ReadCatalog() {
		Catalog cat = ReadXMLFile();
		
		System.out.println("cat ID " + cat.getId());
		System.out.println("Book count: " + cat.getBooks().size());

		return cat;		
	}
	
	private static Catalog IncreasePrice(Catalog cat, double PriceIncrease)
	{
		for (Book b : cat.getBooks()) {
			double newPrice = (b.getPrice() * PriceIncrease) + b.getPrice();			
			b.setPrice(Math.round(newPrice * 100.0) / 100.0);
			b.setCost(Math.round(newPrice * 80.0) / 100.00);
		}
		
		return cat;
	}
	
	private static void WriteXMLFile(Catalog cat) {
		try {

			String basePath = new File("").getAbsolutePath();
			basePath = basePath + "/src/main/resources/XMLFiles/Books.xml";
			File file = new File(basePath);

			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(cat, file);
			jaxbMarshaller.marshal(cat, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	private static Catalog ReadXMLFile() {

		Catalog cat = null;

		String basePath = new File("").getAbsolutePath();
		basePath = basePath + "/src/main/resources/XMLFiles/Books.xml";
		File file = new File(basePath);

		System.out.println(file.getAbsolutePath());
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			cat = (Catalog) jaxbUnmarshaller.unmarshal(file);
			System.out.println(cat);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return cat;

	}

}
