using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.EntityFrameworkCore;
using Tier3ServerDatabase.common;

namespace Tier3ServerDatabase.database{
    public class RepositoryDatabaseAdapter{
        private readonly DatabaseAdapter _context;

        //Initializing the Repository with the proper dbcontext
        public RepositoryDatabaseAdapter(DatabaseAdapter context)
        {
            _context = context;
        }

        // Method for getting a list of all the movies
        public List<Movie> GetAllMovies()
        {
            return _context.movies.ToList();
        }

        // Getting a string of all the movies
        public String GetStringMovies()
        {
            String result = "";
             foreach(Movie m in GetAllMovies())
            {
                result = result + m.ToString();
            }
            return result;
        }

        public List<Movie> GetRentedMovies()
        {
            var rentedMovies = (from m in _context.movies
                             where m.Rented == true
                             orderby m.Id
                             select m).ToList();
            return rentedMovies;
        }

        public String GetRentedStringMovies()
        {
            String result = "";
             foreach(Movie m in GetRentedMovies())
            {
                result = result + m.ToString();
            }
            return result;
        }

        //Method for adding a Movie to the database
        public bool AddMovie(Movie movie)
        {
            _context.Add(movie);
           return SaveAll();
        }

        public bool RentMovie(int id)
        {
            Movie movie = _context.movies.Single(m => m.Id.Equals(id));
            movie.Rented = true;
            return SaveAll();
        }
        //Method to save all the data inputted
        public bool SaveAll () {
            return _context.SaveChanges () > 0;
        }
    }
}