using Microsoft.EntityFrameworkCore.Migrations;

namespace Tier3DatabaseServer.Migrations
{
    public partial class ScheduleSecondTrial : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateIndex(
                name: "IX_schedule_MovieId",
                table: "schedule",
                column: "MovieId");

            migrationBuilder.CreateIndex(
                name: "IX_schedule_RoomId",
                table: "schedule",
                column: "RoomId");

            migrationBuilder.AddForeignKey(
                name: "FK_schedule_movies_MovieId",
                table: "schedule",
                column: "MovieId",
                principalTable: "movies",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_schedule_rooms_RoomId",
                table: "schedule",
                column: "RoomId",
                principalTable: "rooms",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_schedule_movies_MovieId",
                table: "schedule");

            migrationBuilder.DropForeignKey(
                name: "FK_schedule_rooms_RoomId",
                table: "schedule");

            migrationBuilder.DropIndex(
                name: "IX_schedule_MovieId",
                table: "schedule");

            migrationBuilder.DropIndex(
                name: "IX_schedule_RoomId",
                table: "schedule");
        }
    }
}
