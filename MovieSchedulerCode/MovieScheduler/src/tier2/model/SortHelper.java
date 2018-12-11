package tier2.model;

import java.util.ArrayList;

import common.Movie;
import common.Room;
import common.ScheduledMovie;

public class SortHelper {
	private static SortHelper instance = null;
	
	private SortHelper() {
		
	}
	
	 public static SortHelper getInstance() {
	      if(instance == null) {
	         instance = new SortHelper();
	      }
	      return instance;
	   }
	 
	 public ArrayList<ScheduledMovie> sortByValue(ArrayList<ScheduledMovie> movieList)
	 {
		 boolean sorted;
		 do {
			 sorted =true;
			 for(int i = 0; i < movieList.size()- 1; i++)
			 {
				 if(movieList.get(i).getTime().compareTo(movieList.get(i+1).getTime()) < 0)
				 {
				 ScheduledMovie aux = movieList.get(i);
				 movieList.set(i, movieList.get(i+1));
				 movieList.set(i+1, aux);
				 sorted =false;
				 }
			 }
		 }while(sorted == false);
		 
		return movieList;
		 
	 }
	 
	 public static void main(String[] args)
	 {
		 Movie movie = new Movie("add", "add", "add", 0, "add", "add", "add", "add");
		 Room room = new Room(20, "add");
		 ScheduledMovie movie1 = new ScheduledMovie("20:00", "Monday", movie, room);
		 ScheduledMovie movie2 = new ScheduledMovie("10:00", "Monday", movie, room);
		 ScheduledMovie movie3 = new ScheduledMovie("20:00", "Monday", movie, room);
		 ScheduledMovie movie4 = new ScheduledMovie("16:00", "Monday", movie, room);
		 ArrayList<ScheduledMovie> movies = new ArrayList<ScheduledMovie>();
		 movies.add(movie1);
		 movies.add(movie2);
		 movies.add(movie3);
		 movies.add(movie4);
		 SortHelper sorter = SortHelper.getInstance();
		 movies = sorter.sortByValue(movies);
		 System.out.println(movies.get(0).toString());
		 System.out.println(movies.get(1).toString());
		 System.out.println(movies.get(2).toString());
		 System.out.println(movies.get(3).toString());
	 }
}
