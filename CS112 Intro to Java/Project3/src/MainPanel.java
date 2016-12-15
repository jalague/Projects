import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainPanel extends JPanel
{
	private ProfileDatabase socialNetwork;
	private String loggedInUser = " "; // the user or organization who is currently logged in
	private String lookedUpUser = " "; // the user  or organization we looked up
	
	// the text field and the button used to log in
    JTextField loginUserName;
    JButton loginUserButton;

	// the text field and the button used to look up somebody's profile
    JTextField searchUserName;
    JButton searchUserButton;

	// the text field and the button used to update the status
    JTextField newStatus;
    JButton changeStatusButton;

    JTextField newAnnouncement;
    JButton changeAnnouncementButton;
    
	// the text field and the button used to add a new friend
    JTextField enterNewFriend;
    JButton addFriendButton;
    
    // when clicked, should take you to the profile of the loggedUser
    JButton homeButton; 
 
    // When clicked, adds the currently logged user as a supporter for the organization
    JButton likeButton;
    
    // subpanels
    JPanel topPanel; //the blue panel on top, where we login or search for users
    JPanel userProfilePanel; // left panel, login view: shows info for the user that is logged in
    JPanel lookupProfilePanel; //left panel, lookup view: shows info of some other user that was looked up by name
    JPanel newsFeedPanel; // right panel: shows recent posts of user and friends
    JPanel bottomPanel; // shows who is logged in
    
    public MainPanel (ProfileDatabase s) {
      socialNetwork = s;
      setPreferredSize (new Dimension(700, 450));
      setBackground (Color.lightGray);
      setLayout (new BorderLayout ());
      createSubpanels() ;
   }

   /** Creates the four subpanels of the current panel **/
    public void createSubpanels() {
   
    	createTopPanel();
        createUserProfilePanel();
        createNewsFeedPanel();
        createBottomPanel();
        
        
        // FILL IN CODE
        // TODO: instead of calling showTestInfo, get a random user from
        // the social network and display their profile in the login view 
        showUserProfile("Helen");  
    	
       
        // do not change the code below
        add(topPanel, BorderLayout.NORTH);
        add(userProfilePanel, BorderLayout.CENTER);
        add(newsFeedPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
    	
    }

    /** Creates the top panel that is used for logging in and looking up a profile **/
    private void createTopPanel() {
    	
        topPanel = new JPanel();
        topPanel.setBackground (new Color(0, 0, 100));
        
        // the text field and the button for letting the user login
        loginUserName = new JTextField(5);
        topPanel.add(loginUserName);
        loginUserButton = new JButton("Login");
        loginUserButton.addActionListener (new ButtonListener());
        topPanel.add(loginUserButton);
        this.add(topPanel, BorderLayout.NORTH);
        
        // the text field and the button for looking up some user's profile
        searchUserName = new JTextField(5);
        topPanel.add(searchUserName);
        searchUserButton = new JButton("Find a profile");
        searchUserButton.addActionListener (new ButtonListener());
        topPanel.add(searchUserButton);

        this.add(topPanel, BorderLayout.NORTH);

    }
    
    /**  Creates the subpanel  on the left that displays the user's profile  **/
    private void createUserProfilePanel() {
    	
        userProfilePanel = new JPanel();
        userProfilePanel.setPreferredSize (new Dimension(300, 400));
        userProfilePanel.setLayout (new BoxLayout( userProfilePanel, BoxLayout.Y_AXIS));
        userProfilePanel.setBackground (Color.lightGray);
        
    }
    private void createUserLookProfilePanel() {
    	
        userProfilePanel = new JPanel();
        userProfilePanel.setPreferredSize (new Dimension(300, 400));
        userProfilePanel.setLayout (new BoxLayout( lookupProfilePanel, BoxLayout.Y_AXIS));
        userProfilePanel.setBackground (Color.lightGray);
        
    }
    
    /**  Creates the subpanel on the right that displays the the user's news feed  **/
    private void createNewsFeedPanel() {
    	
    	newsFeedPanel = new JPanel();
        newsFeedPanel.setPreferredSize (new Dimension(450, 400));
        newsFeedPanel.setBackground (Color.white);
        newsFeedPanel.setLayout (new BoxLayout (newsFeedPanel, BoxLayout.Y_AXIS));
 	
    }
    
    /**  The subpanel  on the bottom that displays who is logged in **/
    private void createBottomPanel() {
    	
    	 bottomPanel = new JPanel();
         JLabel currentlyLoggedIn = new JLabel("You are currently logged in as " + loggedInUser);
         bottomPanel.add(currentlyLoggedIn);    
    }
    
    /** The inner class that implements ActionListener interface
     *  Use it to handle all the button events.
     */
     private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			System.out.println("Button pressed");
			JButton b = (JButton)e.getSource();
			
			if (b.equals(loginUserButton)) { 
				// The code below will be executed when the Login button is pressed
				
				userProfilePanel.removeAll();
				String name = loginUserName.getText();
				if(!socialNetwork.find(name).getType().equals("not Found")){
				loggedInUser = name;
				if (socialNetwork.find(name).getType().equals("user")){
					showUserProfile(name);
					}
				else if(socialNetwork.find(name).getType().equals("organization")){
					showOrgProfile(name);
					}
				}
				else{	
					showUserNotFoundProfile(name);
					}

			
			}
			if(b.equals(searchUserButton)){
				//createUserLookProfilePanel();
				userProfilePanel.removeAll();
				
				String name = searchUserName.getText();
				lookedUpUser = name;
				if(!socialNetwork.find(name).getType().equals("not Found")){
				if (socialNetwork.find(name).getType().equals("user")){
					showUserLookProfile(name);
				}
				else if(socialNetwork.find(name).getType().equals("organization")){
					showOrgLookProfile(name);
				}
				}
				else{
					showUserNotFoundProfile(name);
				}
					
			}
			// TODO: write if statements for other buttons
			if (b.equals(addFriendButton)){
				String newName = enterNewFriend.getText();
				if(!loggedInUser.equals(newName)){
				socialNetwork.addFriend(loggedInUser, newName);
				}
				userProfilePanel.removeAll();
				showUserProfile(loggedInUser);
			}
			if (b.equals(likeButton)){
				userProfilePanel.removeAll();
				Profile org= socialNetwork.find(lookedUpUser);
				if(org.getSupporters().contains(loggedInUser) || loggedInUser.equals(lookedUpUser)){
					System.out.println("Already a Supporter");
				}
				else{
					socialNetwork.find(lookedUpUser).setNumberOfLikes("1");
					socialNetwork.find(lookedUpUser).addSupporters(loggedInUser);
					socialNetwork.find(loggedInUser).supports(lookedUpUser);
					
				}
				showOrgLookProfile(lookedUpUser);
			}
			if (b.equals(changeStatusButton)){
				String theStatus= newStatus.getText();
				Profile person = socialNetwork.find(loggedInUser);
				person.addPost(loggedInUser, theStatus);
				socialNetwork.addToFriendsNews(person, theStatus);
				userProfilePanel.removeAll();
				showUserProfile(loggedInUser);
			}
			if (b.equals(changeAnnouncementButton)){
				String theAnnouncement= newAnnouncement.getText();
				Profile org = socialNetwork.find(loggedInUser);
				org.addPost(loggedInUser,theAnnouncement);
				userProfilePanel.removeAll();
				socialNetwork.addToSupportersNews(org, theAnnouncement);
				showOrgProfile(loggedInUser);
				
			}
			if(b.equals(homeButton)){
				userProfilePanel.removeAll();
				showUserProfile(loggedInUser);
			}
			updateUI();
		
		}
     }

     
     /** The function that shows how to use GUI to display a user profile. 
      * Everything is hard coded. You need to replace it with your own function
      * that uses information from the profile database.
      **/
     public void showUserProfile(String name) { 
    	 Profile person=socialNetwork.find(name);
    	 loggedInUser = name;
    	 // add the name label  and image to the panel
    	 addLabel(name, "Serif", 25, userProfilePanel);
    	 addImage(person.getPicFile().trim());
    	 
    	 // add status
    	 ArrayList<Post> posts = person.getPosts();
    	 Post statusInfo =  posts.get(posts.size()-1);
    	 String status= statusInfo.getPost();
    	 addLabel(status, "Serif", 18, userProfilePanel);
    	 addStatusTextfieldAndButton("Change status");
    	 
    	 // add friends
    	 String friends = "\nFriends: " + person.getFriends();
    	 addLabel(friends, "Serif", 18, userProfilePanel);
    	 addFriendTextfieldAndButton();
    	 //show what they support
    	 
    	 String Supports = person.getSupports();
    	 addLabel("\nSupports: "+Supports, "Serif", 18, userProfilePanel);

    	 // add information to the news feed
    	 newsFeedPanel.removeAll();
	     JLabel title = new JLabel("News Feed"); 
         title.setFont(new Font("Serif", Font.PLAIN, 20));
	     newsFeedPanel.add(title);
         JLabel line = new JLabel("---------------------------------");
	     newsFeedPanel.add(line);
	     newsFeedPanel.add(new JLabel("\n"));
	    
	    
	   
	  for  (int r=person.getNewsFeed().size()-1; r>person.getNewsFeed().size()-6 && r>=0; r--){
	   
	    	 ArrayList<Post> news= person.getNewsFeed();
	    	 Post pclass = news.get(r);
	    	 String astat= pclass.getPost();
	    	 String fname= pclass.getPoster();
	    	 
	     JLabel nameFriend = new JLabel(fname);
	     nameFriend.setAlignmentX(Component.LEFT_ALIGNMENT);
         nameFriend.setFont(new Font("Serif", Font.PLAIN, 18));
         newsFeedPanel.add(nameFriend);
         JLabel friendsStatus = new JLabel(astat);
         friendsStatus.setAlignmentX(Component.LEFT_ALIGNMENT);
	     newsFeedPanel.add(friendsStatus);
	     //newsFeedPanel.add(new JLabel(" "));
	
	    }
        
		 bottomPanel.removeAll();
		 bottomPanel.add(new JLabel("Currently logged in as " + loggedInUser));
          
     }
    
     public void showUserLookProfile(String name) { 
    	 Profile person=socialNetwork.find(name);
    	 lookedUpUser = name;
    	 // add the name label  and image to the panel
    	 addLabel(name, "Serif", 25, userProfilePanel);
    	 addImage(person.getPicFile().trim());
    	 
    	 // add status
    	 ArrayList<Post> posts = person.getPosts();
    	 Post statusInfo =  posts.get(posts.size()-1);
    	 String status= "\nStatus: " + statusInfo.getPost();
    	
    	 addLabel(status, "Serif", 18, userProfilePanel);
    	 
    	 addLabel("\n", "Serif", 18, userProfilePanel);

    	 // add friends
    	 String friends = "\nFriends: " + person.getFriends();
    	 addLabel(friends, "Serif", 18, userProfilePanel);
    	 //show what they support
    	 
    	 String Supports = person.getSupports();
    	 addLabel("\nSupports: "+Supports, "Serif", 18, userProfilePanel);

    	 // add information to the news feed
    	 newsFeedPanel.removeAll();
	     addHomeButton();
	     
	    
		 bottomPanel.removeAll();
		 bottomPanel.add(new JLabel("Currently logged in as " + loggedInUser));
          
     }
     public void showOrgProfile(String name) { 
    	 Profile org=socialNetwork.find(name);
    	 lookedUpUser = name;
    	 // add the name label  and image to the panel
    	 addLabel(name, "Serif", 25, userProfilePanel);
    	 addImage(org.getPicFile().trim());
    	 
    	 // add phone number
    	 
    	 String phone = "\nPh: " + org.getPhoneNumber();
    	 addLabel(phone, "Serif", 18, userProfilePanel);
    	 //add address
    	 
    	 String address = "\n " + org.getAddress();
    	 addLabel(address, "Serif", 18, userProfilePanel);
    	 // add announcement
    	
    	 ArrayList<Post> posts = org.getPosts();
    	 Post statusInfo =  posts.get(posts.size()-1);
    	 String announcementInfo= statusInfo.getPost();
    	 
    	// addLabel(announcementInfo, "Serif", 18, userProfilePanel);
    	 addAnnouncementTextfieldAndButton("Add an Announcement");
    	 
    	 // add likes
    	 String likes = "\nLikes: " + org.getNumberOfLikes();
    	 addLabel(likes, "Serif", 18, userProfilePanel);
    	 // add supporters
    	 String supporters = "\nSupporters: " + org.getSupporters();
    	 addLabel(supporters, "Serif", 18, userProfilePanel);
    	 

    	 // add information to the news feed
    	 newsFeedPanel.removeAll();
	     JLabel title = new JLabel("Announcements"); 
         title.setFont(new Font("Serif", Font.PLAIN, 20));
	     newsFeedPanel.add(title);
         JLabel line = new JLabel("---------------------------------------");
	     newsFeedPanel.add(line);
	     newsFeedPanel.add(new JLabel("\n"));
	    
	     for  (int r=org.getNewsFeed().size()-1; r>org.getNewsFeed().size()-6 && r>=0; r--){
	  	   
	    	 ArrayList<Post> news= org.getNewsFeed();
	    	 Post pclass = news.get(r);
	    	 String astat= pclass.getPost();
	    	 
	     JLabel announce= new JLabel(astat);
         announce.setAlignmentX(Component.LEFT_ALIGNMENT);
	     newsFeedPanel.add(announce);
	     
	     }
		 bottomPanel.removeAll();
		 bottomPanel.add(new JLabel("Currently logged in as " + loggedInUser));
          
     }
     public void showOrgLookProfile(String name) { 
    	 Profile org=socialNetwork.find(name);
    	 lookedUpUser = name;
    	 // add the name label  and image to the panel
    	 addLabel(name, "Serif", 25, userProfilePanel);
    	 addImage(org.getPicFile().trim());
    	 
    	 // add phone number
    	 
    	 String phone = "\nPh: " + org.getPhoneNumber();
    	 addLabel(phone, "Serif", 18, userProfilePanel);
    	 //add address
    	 
    	 String address = "\n " + org.getAddress();
    	 addLabel(address, "Serif", 18, userProfilePanel);
    	
    	 // likes
    	 String likes = "\nLikes: " + org.getNumberOfLikes();
    	 addLabel(likes, "Serif", 18, userProfilePanel);
    	 //  supporters
    	 String supporters = "\nSupporters: " + org.getSupporters();
    	 addLabel(supporters, "Serif", 18, userProfilePanel);
    	 addLikeButton();

    	 // add information to the news feed
    	 newsFeedPanel.removeAll();
	     JLabel title = new JLabel("Announcements"); 
         title.setFont(new Font("Serif", Font.PLAIN, 20));
	     newsFeedPanel.add(title);
         JLabel line = new JLabel("------------------------------------");
	     newsFeedPanel.add(line);
	     newsFeedPanel.add(new JLabel("\n"));
	    
	     for  (int r=org.getNewsFeed().size()-1; r>org.getNewsFeed().size()-6 && r>=0; r--){
		  	   
	    	 ArrayList<Post> news= org.getNewsFeed();
	    	 Post pclass = news.get(r);
	    	 String astat= pclass.getPost();
	    	 
	     JLabel announce= new JLabel(astat);
         announce.setAlignmentX(Component.LEFT_ALIGNMENT);
	     newsFeedPanel.add(announce);
	     
	     }
        
		 bottomPanel.removeAll();
		 bottomPanel.add(new JLabel("Currently logged in as " + loggedInUser));
          
     }
     public void showUserNotFoundProfile(String name) { 
       	 addLabel("User: " +name+" not found", "Serif", 18, userProfilePanel);
    	
    	 newsFeedPanel.removeAll();
	     
	    	    
		 bottomPanel.removeAll();
		 bottomPanel.add(new JLabel("Currently logged in as " + loggedInUser));
          
     }
     /** Adds a label with the given text (name) to the panel passed as a parameter
      *  Centers the label in the center of the panel.
      */
     public void addLabel(String name, String font, int fontSize, JPanel panel) {

    	 JLabel userName = new JLabel(name);
    	 userName.setFont(new Font(font, Font.PLAIN, fontSize));
    	 userName.setAlignmentX(Component.CENTER_ALIGNMENT);
         panel.add(userName);
         
     }
     
     /** Adds an image with the given filename to the userProfilePanel */
     public void addImage(String imagefile) {
    	 File imFile = new File(imagefile);	
    	 BufferedImage myPicture;
    	 try {
    		 myPicture = ImageIO.read(imFile);
    		 JLabel picLabel = new JLabel(new ImageIcon(myPicture));
    		 picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    		 userProfilePanel.add(picLabel);
    	 }
    	 catch (IOException e) {
    		 System.out.println(e);
    	 }
     }   
     
     
     /** Adds a text field and a button for changing the status to the userProfilePanel */
      public void addStatusTextfieldAndButton(String text) {
    	 // add a textfield
    	 newStatus = new JTextField(15);
    	 newStatus.setMaximumSize( newStatus.getPreferredSize() );
    	 newStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
         userProfilePanel.add(newStatus );
         
         //add a button
         changeStatusButton = new JButton(text);
         changeStatusButton.addActionListener (new ButtonListener());
         changeStatusButton.setAlignmentX(Component.CENTER_ALIGNMENT);
         userProfilePanel.add(changeStatusButton);

      }
      public void addAnnouncementTextfieldAndButton(String text) {
     	 // add a textfield
     	 newAnnouncement = new JTextField(15);
     	newAnnouncement.setMaximumSize( newAnnouncement.getPreferredSize() );
     	newAnnouncement.setAlignmentX(Component.CENTER_ALIGNMENT);
          userProfilePanel.add(newAnnouncement );
          
          //add a button
          changeAnnouncementButton = new JButton(text);
          changeAnnouncementButton.addActionListener (new ButtonListener());
          changeAnnouncementButton.setAlignmentX(Component.CENTER_ALIGNMENT);
          userProfilePanel.add(changeAnnouncementButton);

       }
     
      /** Adds a text field and a button for adding a friend to the userProfilePanel */
      public void addFriendTextfieldAndButton() {
    	 // add a text field
    	 enterNewFriend = new JTextField(15);
    	 enterNewFriend.setMaximumSize( newStatus.getPreferredSize() );
    	 enterNewFriend.setAlignmentX(Component.CENTER_ALIGNMENT);
    	 userProfilePanel.add(enterNewFriend );
      
    	 //add a button
    	 addFriendButton = new JButton("Add a friend");
    	 addFriendButton.addActionListener (new ButtonListener());
    	 addFriendButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    	 userProfilePanel.add(addFriendButton);
      }
      
      public void addLikeButton(){
    	  likeButton = new JButton("Like");
     	 likeButton.addActionListener (new ButtonListener());
     	 likeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
     	 userProfilePanel.add(likeButton);
      }
      public void addHomeButton(){
    	  homeButton = new JButton("Go back to " + loggedInUser +"'s profile");
    	  homeButton.addActionListener (new ButtonListener());
    	  homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
     	 newsFeedPanel.add(homeButton);
      }

     
     
}

