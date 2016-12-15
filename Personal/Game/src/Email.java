

import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random; 
public class Email {
	public Session session;
	Properties props;
	
	String[] quotes= {"'Chugga Chugga Chew Chew'", "'I got the bug to play some Chugg'", "'I dugged it, so I Chugged it'","'Fugg it, just Chugg it'"};
	Random r = new Random();
	public Email(){
		final String username = "youchugg@gmail.com";
		final String password = "monkeychugg";

		 this.props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		this.session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
	}
	
	public void sendOpeningEmail( HashMap<String,String> numbers, int duration, int delay){
		try {
			
			for(String name: numbers.keySet()){
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("youchugg@gmail.com"));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(numbers.get(name)));
				message.setSubject("Prepare, you're about to Chugg");
				
				message.setText("\n\n Every " +delay+ " minutes one of you will be chosen, at random, to Chugg a random drink!"+"\n\n This will only last for " + duration+" min" + "\n\n Let the game begin!");

			
				Transport.send(message);
				System.out.println("Message sent to: " + name);
				}
			
		
		System.out.println("Done with opening Email");
		
		} catch (MessagingException e) {
			throw new RuntimeException(e);
			
		}
	}
	
	
	public boolean sendChuggEmail(String textMessage,  String theName, HashMap<String,String> numbers, int drinknum){
		

		try {
			
			for(String name: numbers.keySet()){
				try{
					TimeUnit.SECONDS.sleep(2);
					}
					catch(Exception u){
						System.out.println("Exception in sleep");
					}	
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("youchugg@gmail.com"));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(numbers.get(name)));
				message.setSubject("The Chugger: " + theName );
				int rand=r.nextInt(4);
				message.setText("\n\n Chugg"+ "\n\n"+ textMessage + "\n\n"+ quotes[rand]);
//"\n\n Drink number: "+drinknum 
			
			Transport.send(message);
			System.out.println("Message sent to: " + name);
			
			
			}
			
		
		System.out.println("Done with Drink Email " + drinknum);
		return true;
		} catch (MessagingException e) {
			throw new RuntimeException(e);
			
		}
		
		
	}

	public void sendHypeEmail(HashMap<String, String> numbers) {
		try {
			
			for(String name: numbers.keySet()){
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("youchugg@gmail.com"));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(numbers.get(name)));
				message.setSubject("Prepare, you're about to Chugg");
				
				message.setText("\n\n Get Ready! \n\n Someone is about to Chugg in 1 minute!");

			
				Transport.send(message);
				System.out.println("Message sent to: " + name);
				}
			
		
		System.out.println("Done with Hype Email");
		
		} catch (MessagingException e) {
			throw new RuntimeException(e);
			
		}
		
	}
	

	
}
/**	 
String test = " Drink number "+ i;
final String username = "youchugg@gmail.com";
final String password = "monkeychugg";

Properties props = new Properties();
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable", "true");
props.put("mail.smtp.host", "smtp.gmail.com");
props.put("mail.smtp.port", "587");

Session session = Session.getInstance(props,
new javax.mail.Authenticator() {
protected PasswordAuthentication getPasswordAuthentication() {
	return new PasswordAuthentication(username, password);
}
});

try {
int i=1;

for(int x=0;x<gameTime;x+=delay){
	rn= new RandomNum(drinksEx,mixersEx, numbersEx);
	String to= rn.randomNumber();
	 Drink theMix= rn.ranodmChugg();
	 
String test = " Drink number "+ i;
Message message = new MimeMessage(session);
message.setFrom(new InternetAddress("youchugg@gmail.com"));
message.setRecipients(Message.RecipientType.TO,
	InternetAddress.parse(to+"@tmomail.net"));
message.setSubject(test);
i++;





	message.setText("\n\n You're drink is..."
	+ "\n\n"+ theMix.toString() + "\n\n Chugg!");
	
	
	

Transport.send(message);
System.out.println("Message sent to: " + to);
try{
TimeUnit.MINUTES.sleep(delay);
}
catch(Exception e){
	System.out.println("Exception in sleep");
}
}
System.out.println("Done");

} catch (MessagingException e) {
throw new RuntimeException(e);

**/
