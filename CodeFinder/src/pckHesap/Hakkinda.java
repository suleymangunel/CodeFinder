package pckHesap;

import javax.microedition.lcdui.*;

public class Hakkinda {
    private static String copyright =
	"Copyright (c) 2006 - S�leyman G�NEL.\n"
      + "Bu program OMTP (Otel M��teri Takip Program�) i�in, "
      + "gereken lisans anahtar�n� �retmek amac�yla haz�rlanm��t�r. "
      + "Anla�mal� bayiler ve �ah�slar d���nda kullan�lmas� su� "
      + "unsuru te�kil etmekle birlikte bili�im su�lar� kapsam�nda "
      + "cezai yapt�r�mlar� beraberinde getirmektedir. Program�n "
      + "yazar�n�n (S�leyman G�NEL) izni olmaks�z�n kullan�m� "
      + "bu cezai yapt�r�mlar�n kabul edildi�i anlam�na gelir.";
    
    private Displayable previous;
    
    private Hakkinda () {};

    public static void showHakkinda(Display display) {

	Alert alert = new Alert("Uyar�!");
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
