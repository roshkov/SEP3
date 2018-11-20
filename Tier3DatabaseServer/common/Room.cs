using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Newtonsoft.Json;
namespace Tier3ServerDatabase.common {
    public class Room{
        private int id;
        private int size;
        private string description;
        [Key]
        [Required (ErrorMessage="Id {0} is required")]
        public int Id { get => id; set => id = value; }
        [Required (ErrorMessage="Size {0} is required")]
        public int Size { get => size; set => size = value; }
        [StringLength (40,MinimumLength=2,
        ErrorMessage="Description Should be minimum 2 characters and a maximum of 40 characters")]
        [DataType(DataType.Text)]
        public string Description { get => description; set => description = value; }

        [JsonConstructor]
        public Room(int id, int size, string description)
        {
            Id = id;
            Size = size;
            Description = description;
        }
    }
}