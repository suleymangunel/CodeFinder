package pckHesap;

import javax.microedition.lcdui.*;

public class Hakkinda {
    private static String copyright =
	"Copyright (c) 2006 - Süleyman GÜNEL.\n"
      + "Bu program OMTP (Otel Müþteri Takip Programý) için, "
      + "gereken lisans anahtarýný üretmek amacýyla hazýrlanmýþtýr. "
      + "Anlaþmalý bayiler ve þahýslar dýþýnda kullanýlmasý suç "
      + "unsuru teþkil etmekle birlikte biliþim suçlarý kapsamýnda "
      + "cezai yaptýrýmlarý beraberinde getirmektedir. Programýn "
      + "yazarýnýn (Süleyman GÜNEL) izni olmaksýzýn kullanýmý "
      + "bu cezai yaptýrýmlarýn kabul edildiði anlamýna gelir.";
    
    private Displayable previous;
    
    private Hakkinda () {};

    public static void showHakkinda(Display display) {

	Alert alert = new Alert("Uyarý!");
	alert.setTimeout(Alert.FOREVER);

	if (display.numColors() > 2) {
	    String icon = "/Images/OMTP.png";

	    try {
	         Image image = Image.createImage(icon);
		 alert.setImage(image);
	        } catch (java.io.IOException x) {
	    }
	}
	alert.setString(copyright);

	display.setCurrent(alert);
    }

}
