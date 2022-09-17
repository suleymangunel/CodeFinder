package pckHesap;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.wireless.messaging.*;
import javax.microedition.io.*;

public class Hesapla extends MIDlet implements CommandListener
 {
  Form form1,form2;
  Command calcCommand,sendCommand,aboutCommand,exitCommand;
  Command backCommand,sendCommand1,exitCommand1;
  TextField Usr,Lock,Key,Telefon;
  StringItem Mesaj,SMSC;
  Display display;
  
  public Hesapla() {}
  private void initialize()
   {
    Display.getDisplay(this).setCurrent(get_form1());
   }
  public void commandAction(Command command, Displayable displayable)
   {
    if (displayable == form1)
     {
      if (command == exitCommand)
       {
        Display.getDisplay(this).setCurrent(null);
        destroyApp(true);
        notifyDestroyed();
       }
      if (command == calcCommand)
       {
        Calculate();  
       }
      if (command == sendCommand)
       {
        Display.getDisplay(this).setCurrent(get_form2());
       }
      if (command == aboutCommand){Hakkinda.showHakkinda(Display.getDisplay(this));}
     }
    else if (displayable == form2)
     {
      if (command == backCommand)
       {
        Display.getDisplay(this).setCurrent(get_form1());
       }
      if (command == exitCommand1)
       {
        Display.getDisplay(this).setCurrent(null);
        destroyApp(true);
        notifyDestroyed();
       }
      if (command == sendCommand1)
       {
        SendSMS();
       }
     }
  }
 
  private Form get_form1()
   {
    if (form1 == null)
     {
      /* form1 = new Form(null, new Item[] {get_Usr()}); */
      form1 = new Form(null);
      form1.append(get_Usr());
      form1.append(get_Key());
      form1.append(get_Lock());
      form1.append(get_Mesaj());
      form1.addCommand(get_calcCommand());
      form1.addCommand(get_sendCommand());
      form1.addCommand(get_aboutCommand());
      form1.addCommand(get_exitCommand());
      form1.setCommandListener(this);
     }
    return form1;
   }
    
  private Form get_form2()
   {
    if (form2 == null) 
     {
      /* form2 = new Form(null, new Item[] {get_stringItem2()}); */
      form2 = new Form(null);
      form2.append(get_SMSC());
      form2.append(get_Telefon());
      form2.addCommand(get_sendCommand1());
      form2.addCommand(get_backCommand());
      form2.addCommand(get_exitCommand1());
      GetSMSC();
      form2.setCommandListener(this);
     }
    return form2;
   }
    
  /* Form 1'nin nesneleri................................................ */ 
  
  private Command get_calcCommand()
   {
    if (calcCommand == null)
     {
      calcCommand = new Command("Hesapla", Command.SCREEN, 2);
     }
    return calcCommand;
   }
  
  private Command get_sendCommand()
   {
    if (sendCommand == null)
     {
      sendCommand = new Command("G\u00F6nder", Command.SCREEN, 2);
     }
    return sendCommand;
   }
    
  private Command get_aboutCommand()
   {
    if (aboutCommand == null)
     {
      aboutCommand = new Command("Hakk\u0131nda", Command.SCREEN, 2);
     }
    return aboutCommand;
   }
    
  private Command get_exitCommand()
   {
    if (exitCommand == null)
     {
      exitCommand = new Command("\u00C7\u0131k\u0131\u015F", Command.EXIT, 1);
     }
    return exitCommand;
   }
  

  /* Form 2'nin nesneleri................................................ */ 
  
  private Command get_backCommand()
   {
    if (backCommand == null)
     {
      backCommand = new Command("Geri", Command.SCREEN, 2);
     }
    return backCommand;
   }

  private Command get_sendCommand1()
   {
    if (sendCommand1 == null)
     {
      sendCommand1 = new Command("G\u00F6nder", Command.SCREEN, 2);
     }
    return sendCommand1;
   }
    
  private Command get_exitCommand1()
   {
    if (exitCommand1 == null)
     {
      exitCommand1 = new Command("\u00C7\u0131k\u0131\u015F", Command.EXIT, 1);
     }
    return exitCommand1;
   }
    
    
  /* Veri alanlarý........................................................*/
  
  private TextField get_Usr()
   {
    if (Usr == null)
     {
      Usr = new TextField("Kullanýcý adý:", "", 30, TextField.ANY);
     }
    return Usr;
   }
   
  private TextField get_Key()
   {
    if (Key == null)
     {
      Key = new TextField("Kod:", "", 30, TextField.ANY);
     }
    return Key;
   }

  private TextField get_Lock()
   {
    if (Lock == null)
     {
      Lock = new TextField("Anahtar:", "", 30, TextField.ANY);
     }
    return Lock;
   }
  
  private TextField get_Telefon()
   {
    if (Telefon == null)
     {
      Telefon = new TextField("Telefon:", "", 30, TextField.NUMERIC);
     }
    return Telefon;
   }
    
  private StringItem get_Mesaj()
   {
    if (Mesaj == null)
     {
      Mesaj=new StringItem("*Not:","Kodu çözmek için 'Seçenekler'den 'Hesapla'yý seçin.");
     }
    return Mesaj;
   }

  private StringItem get_SMSC()
   {
    if (SMSC == null)
     {
      SMSC=new StringItem("Mesaj Merkezi:","");
     }
    return SMSC;
   }
 
  public void startApp()
   {
    initialize();
   }

  public void Calculate()
  {
   int kc,i,j=0,hh,h,kch;
   char[] i1,i2,Lck;
   int hi4bit,low4bit,d;
   char Kar[]={'ö','ç','þ','ý','ð','ü','Ö','Ç','Þ','Ý','Ð','Ü'};
   int Kod[]={246,231,254,253,240,252,214,199,222,221,208,220};
   if(Usr.size()>20)
    {
     i1=new char[Usr.size()];
     i2=new char[Key.size()];
     Lck=new char[i1.length];
     Usr.getChars(i1);
     Key.getChars(i2);
    }
   else
    {
     i1=new char[Key.size()];
     i2=new char[Usr.size()];
     Lck=new char[i1.length];
     Key.getChars(i1);
     Usr.getChars(i2);
    }
   kc=i2.length;
   for(i=(i1.length-1);i>=0;i--)
    {
     kc--;if (kc<0) {kc=i2.length-1;}
     kch=(int) i2[kc];
     h=(int) i1[i];
     for(d=0;d<12;d++) {if(i1[i]==Kar[d]) h=Kod[d];};
     for(d=0;d<12;d++) {if(i2[kc]==Kar[d]) kch=Kod[d];};
     hi4bit=h & 0xF0;
     low4bit=h & 0x0F;
     hh=(hi4bit>>>4)+(low4bit<<4);
     hh+=kch;
     if (hh>255){hh-=256;}
     if (hh>90)
      {
       if (hh>=91 && hh<=116){hh=(65+Math.abs(91-hh));}
       if (hh>=117 && hh<=142){hh=(65+Math.abs(117-hh));}
       if (hh>=143 && hh<=168){hh=(65+Math.abs(143-hh));}
       if (hh>=169 && hh<=194){hh=(65+Math.abs(169-hh));}
       if (hh>=195 && hh<=220){hh=(65+Math.abs(195-hh));}
       if (hh>=221 && hh<=246){hh=(65+Math.abs(221-hh));}
       if (hh>=247){hh= (65+Math.abs(247-hh));}
      }
     if (hh<65)
      {
       if (hh>=39 && hh<=64){hh=(90-(64-hh));}
       if (hh>=13 && hh<=38){hh=(90-(38-hh));}
       if (hh<=12){hh=(90-(12-hh));}
      }
     Lck[j]=(char) hh;j++;
    }
   Lock.setChars(Lck, 0, i1.length);
  }

  public void SendSMS()
   {
    try
     {
      MessageConnection clientConn;
      TextMessage tmsg;
      String url,msg,addr;
      msg=Lock.getString();
      addr=Telefon.getString();
      url="sms://"+addr;
      clientConn = (MessageConnection)Connector.open(url);
      tmsg =(TextMessage)clientConn.newMessage(MessageConnection.TEXT_MESSAGE);
      tmsg.setPayloadText(msg);
      clientConn.send(tmsg);
     }
    catch (Exception exc)
     {
      exc.printStackTrace();
     }
   }
  
  public void GetSMSC()
  {
   String smsc;
   smsc=System.getProperty("wireless.messaging.sms.smsc");
   SMSC.setText(smsc);
  }
  
  public void pauseApp() {}
  public void destroyApp(boolean unconditional) {}
}
