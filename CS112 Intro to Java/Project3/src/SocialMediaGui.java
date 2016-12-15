import javax.swing.JFrame;

public class SocialMediaGui {

	public static void main(String[] args) {
		 JFrame frame = new JFrame ("Social Network");
	      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	      ProfileDatabase mk= new ProfileDatabase();
	      mk.addProfilesFromFile("profiles.txt");
	      MainPanel panel = new MainPanel(mk);
	      frame.add(panel);
	      frame.pack();
	      frame.setVisible(true);
	}

}
