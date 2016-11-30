package kr.ac.hansung.spring.csemall;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Mainapp {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("kr/ac/hansung/spring/csemall/baens/beans.xml");
		OfferDAO offerDAO = (OfferDAO) context.getBean("offerDAO");// id는Beans.xml에  있는id
		System.out.println("The record count is "+offerDAO.getRowcount());
		
		List<Offer> offerList = offerDAO.getOffers();
		for(Offer offer : offerList){
			 System.out.println(offer);
		}
		
		Offer offer=new Offer("ysm","ysm@hansung.ac.kr","an instructor of spring framework");
		if(offerDAO.insert(offer)){
			System.out.println("Object insert successfully");
			
		}
		else{
			System.out.println("Object insertion failed");
		}
		
		offer = offerDAO.getOffer("ysm");
		System.out.println(offer);
		offer.setName("Yang soo min");
		
		if(offerDAO.update(offer)){
			System.out.println("update with object =" +offer);
		}
		else
			System.out.println("updata Faild");
		
		if(offerDAO.delete(offer.getId())){
			System.out.println("Delete with objcet=" +offer);
		}
		else
			System.out.println("Delete Faild");
		context.close();

	}

}
