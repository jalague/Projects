import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.FileWriter;
import java.io.FileInputStream;
import javazoom.jl.player.Player;
import javazoom.jl.decoder.JavaLayerException;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MPlayerPanel extends JPanel {
	private SongDatabase sd;
	private String current;
	private PlayerThread currentThread;
	// three subpanels
	JPanel topPanel, bottomPanel; 
	JScrollPane centerPanel;

	// boxes, textfield, check box
	JButton playButton, stopButton, exitButton, loadMp3Button, saveButton, openButton;

	// the checkbox that specifies whether the songs should be sorted by Artist (or by Title)
	JCheckBox sortBox = new JCheckBox("Sort by Artist");

	// the text field used to search for a song
	JTextField searchBox;

	int selectedSong = -1; // the index of the row that corresponds to the selected song
	private JTable table = null;
	private final JFileChooser fc = new JFileChooser(); // for opening a window to select a file

	MPlayerPanel(SongDatabase S) {
		sd =S;
		this.setLayout(new BorderLayout());
		// Create panels: top, center, bottom
		// Create the top panel that has buttons: Load mp3, Save Library, Load Library 
		// It also has a textfield to search for a song and "sort by artist" checkbox
		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1,4));

		// create buttons
		loadMp3Button = new JButton("Load mp3");
		saveButton = new JButton("Save Library");
		openButton = new JButton("Load Library");
		searchBox = new JTextField(5);
		exitButton = new JButton("Exit");
		playButton = new JButton("Play");
		stopButton = new JButton("Stop");

		// add a listener for each button
		loadMp3Button.addActionListener(new MyButtonListener());
		saveButton.addActionListener(new MyButtonListener());
		openButton.addActionListener(new MyButtonListener());
		sortBox.addActionListener(new MyButtonListener());
		exitButton.addActionListener(new MyButtonListener());
		playButton.addActionListener(new MyButtonListener());
		stopButton.addActionListener(new MyButtonListener());
		searchBox.addActionListener(new MyButtonListener());


		// add buttons, textfield and a checkbox to the top panel
		topPanel.add(loadMp3Button);
		topPanel.add(saveButton);
		topPanel.add(openButton);
		topPanel.add(searchBox);
		topPanel.add(sortBox);

		this.add(topPanel, BorderLayout.NORTH);

		// create the bottom panel and add three buttons to it
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1,3));
		bottomPanel.add(playButton);
		bottomPanel.add(stopButton);
		bottomPanel.add(exitButton);

		this.add(bottomPanel, BorderLayout.SOUTH);

		// the panel in the center that shows mp3 songs
		centerPanel = new JScrollPane();
		this.add(centerPanel, BorderLayout.CENTER );

		// file chooser: set the default directory to the current directory 
		fc.setCurrentDirectory(new File("."));

	}


	/** A inner listener class for buttons, textfield and checkbox **/
	class MyButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == loadMp3Button) {
				System.out.println("Load mp3 button");
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fc.setDialogTitle("Select a directory with mp3 songs");
				// open a window to select a directory
				int returnVal = fc.showOpenDialog(MPlayerPanel.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					sd.clearCurrent();
					current= "full";
					File dir = fc.getSelectedFile();
					String directory= dir.getPath();
					// FILL IN CODE 
					// Traverse this directory and all of its subdirectories recursively 
					// to find mp3 files
					// Then extract the song title and the artist for each mp3 file, 
					// create a Song object, add it to the collections of songs and display
					sd.traverseMp3(directory);
					sd.sortDatabaseTitle();
					ArrayList<Song> Songs= sd.getArray();
					int numSongs = Songs.size();
					
					String[][] tableElems = new String[numSongs][2];
					String[] columnNames = {"Title", "Artist"};
					for(int i =0; i<numSongs;i++){
						tableElems[i][0]=Songs.get(i).getTitle();
						tableElems[i][1]=Songs.get(i).getArtist();
					}
					
					// creating a table and adding it to the centerPanel
					table = new JTable(tableElems, columnNames );
					centerPanel.getViewport().add (table);
					updateUI();					

				}
			}
			else if (e.getSource() == saveButton) {
				// save the song catalog into a file
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fc.setDialogTitle("Save file as");
				int returnVal = fc.showSaveDialog(MPlayerPanel.this);
				
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					String fName =file.getName();
							 					
					 sd.saveLibrary(fName);
					 //System.out.println(textFile);
					
					
					//updateUI(); 
			



				}
			}

			else if (e.getSource() == openButton) {
				System.out.println("Load library button");
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fc.setDialogTitle("Select a library text file");
				// open a window to select a directory
				int returnVal = fc.showOpenDialog(MPlayerPanel.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File lib = fc.getSelectedFile();
				sd.clearCurrentLibrary();
				sd.readLibrary(lib);
				sd.sortLibraryTitle();
				current= "library";
				ArrayList<Song> Songs= sd.getLibraryArray();
				int numSongs = Songs.size();
				
				String[][] tableElems = new String[numSongs][2];
				String[] columnNames = {"Title", "Artist"};
				for(int i =0; i<numSongs;i++){
					tableElems[i][0]=Songs.get(i).getTitle();
					tableElems[i][1]=Songs.get(i).getArtist();
				}
				
				// creating a table and adding it to the centerPanel
				table = new JTable(tableElems, columnNames );
				centerPanel.getViewport().add (table);
				updateUI();		
					
				}
			}

			else if (e.getSource() == playButton) {
				if (currentThread ==null){
					selectedSong = table.getSelectedRow();
					ArrayList<Song> Songs;
					if(current.equals("search")){
						Songs= sd.getSearchArray();
					}
					else if (current.equals("full")){
					 Songs= sd.getArray();
					}
					else
						Songs= sd.getLibraryArray();
					String path= Songs.get(selectedSong).getFile();
					currentThread = new PlayerThread(path);
					currentThread.start();
				}
				

			}
			else if (e.getSource() == stopButton) {
				
				currentThread.stop();
				currentThread=null;


			}
			else if (e.getSource() == exitButton) {
				System.exit(0);
			}

			else if (e.getSource() == sortBox) {
				if(sortBox.getModel().isSelected()){
					System.out.println("Sorting");
					ArrayList<Song> array;
					if(current.equals("search"))
						array = sd.getSearchArray();
					else if(current.equals("full"))
						array = sd.getArray();
					else 
						array= sd.getLibraryArray();
				
					ArrayList<Song> Songs= sd.sortArtist(array);
					int numSongs=Songs.size();
					String[][] tableElems = new String[numSongs][2];
					String[] columnNames = {"Title", "Artist"};
					for(int i =0; i<numSongs;i++){
						tableElems[i][0]=Songs.get(i).getTitle();
						tableElems[i][1]=Songs.get(i).getArtist();
					}
								
					table = new JTable(tableElems, columnNames );
					centerPanel.getViewport().removeAll();
					centerPanel.getViewport().add (table);
					updateUI();		
					}
			
			
			else if (!sortBox.getModel().isSelected()){
				
				
				System.out.println("Sorting");
				ArrayList<Song> Songs;
				if(current.equals("search")){
					Songs = sd.getSearchArray();
				
				sd.sortSearchTitle();
				}

				else if( current.equals("full")){
					Songs = sd.getArray();
					
					sd.sortDatabaseTitle();
				}

				else{
					Songs= sd.getLibraryArray();
					
					sd.sortLibraryTitle();
				}

				
				
				int numSongs=Songs.size();
				
				String[][] tableElems = new String[numSongs][2];
				String[] columnNames = {"Title", "Artist"};
				for(int i =0; i<numSongs;i++){
					tableElems[i][0]=Songs.get(i).getTitle();
					tableElems[i][1]=Songs.get(i).getArtist();
				}
							
				table = new JTable(tableElems, columnNames );
				centerPanel.getViewport().removeAll();
				centerPanel.getViewport().add (table);
				updateUI();		
			}
	}
			else if (e.getSource() == searchBox) {
				String searchT = searchBox.getText();
				String previous;
				ArrayList<Song> results;
				if (current.equals("full")){
					previous= "f";
				}
				else{
					previous="l";
				}
				current = "search";
				if(sd.searchDatabase(searchT, previous)!=null){
					
						results=sd.searchDatabase(searchT, previous);
					
					
					int numSongs=results.size();
					String[][] tableElems = new String[numSongs][2];
					String[] columnNames = {"Title", "Artist"};
					for(int i =0; i<numSongs;i++){
						tableElems[i][0]=results.get(i).getTitle();
						tableElems[i][1]=results.get(i).getArtist();
					}
					
				
					table = new JTable(tableElems, columnNames );
					centerPanel.getViewport().removeAll();
					centerPanel.getViewport().add (table);
					updateUI();		
				}
				//else
					// display error message
			
			}
			updateUI();
		} // actionPerformed
	} // ButtonListener


	class PlayerThread extends Thread {
		Player pl;
		PlayerThread(String filename) {
		FileInputStream file;
		try {
		
			file = new FileInputStream(filename);
			pl = new Player(file);
		} 
		catch (FileNotFoundException e) {
			e.getMessage();
			}
		catch (JavaLayerException e) {
			e.getMessage();
			}
		}
		public void run() {
			try {
			pl.play();
			}
			catch(Exception e) {
			e.getMessage();
			}
		}
		
	}

}


