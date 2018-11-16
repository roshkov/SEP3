using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Migrations;

namespace Tier3DatabaseServer.Migrations
{
    public partial class Movie : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "movies",
                columns: table => new
                {
                    Title = table.Column<string>(maxLength: 20, nullable: false),
                    YearCreation = table.Column<string>(maxLength: 20, nullable: true),
                    ReleaseDate = table.Column<string>(maxLength: 20, nullable: true),
                    Price = table.Column<decimal>(type: "decimal(18,2)", nullable: false),
                    NameStudio = table.Column<string>(maxLength: 20, nullable: true),
                    NameDirector = table.Column<string>(maxLength: 20, nullable: true),
                    Description = table.Column<string>(maxLength: 40, nullable: true),
                    NameMainActor = table.Column<string>(maxLength: 20, nullable: true),
                    Rented = table.Column<bool>(type: "bit", nullable: false),
                    Id = table.Column<int>(nullable: false)
                        .Annotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_movies", x => x.Id);
                });
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "movies");
        }
    }
}
