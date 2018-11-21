using Microsoft.EntityFrameworkCore.Migrations;

namespace Tier3DatabaseServer.Migrations
{
    public partial class ScheduleSecondTrial2 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_schedule_movies_MovieId",
                table: "schedule");

            migrationBuilder.DropForeignKey(
                name: "FK_schedule_rooms_RoomId",
                table: "schedule");

            migrationBuilder.AlterColumn<int>(
                name: "RoomId",
                table: "schedule",
                nullable: true,
                oldClrType: typeof(int));

            migrationBuilder.AlterColumn<int>(
                name: "MovieId",
                table: "schedule",
                nullable: true,
                oldClrType: typeof(int));

            migrationBuilder.AddForeignKey(
                name: "FK_schedule_movies_MovieId",
                table: "schedule",
                column: "MovieId",
                principalTable: "movies",
                principalColumn: "Id",
                onDelete: ReferentialAction.Restrict);

            migrationBuilder.AddForeignKey(
                name: "FK_schedule_rooms_RoomId",
                table: "schedule",
                column: "RoomId",
                principalTable: "rooms",
                principalColumn: "Id",
                onDelete: ReferentialAction.Restrict);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_schedule_movies_MovieId",
                table: "schedule");

            migrationBuilder.DropForeignKey(
                name: "FK_schedule_rooms_RoomId",
                table: "schedule");

            migrationBuilder.AlterColumn<int>(
                name: "RoomId",
                table: "schedule",
                nullable: false,
                oldClrType: typeof(int),
                oldNullable: true);

            migrationBuilder.AlterColumn<int>(
                name: "MovieId",
                table: "schedule",
                nullable: false,
                oldClrType: typeof(int),
                oldNullable: true);

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
    }
}
