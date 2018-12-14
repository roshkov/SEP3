﻿// <auto-generated />
using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Migrations;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using Tier3ServerDatabase.database;

namespace Tier3DatabaseServer.Migrations
{
    [DbContext(typeof(DatabaseAdapter))]
    [Migration("20181213130806_settingUpFinal")]
    partial class settingUpFinal
    {
        protected override void BuildTargetModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("ProductVersion", "2.1.4-rtm-31024")
                .HasAnnotation("Relational:MaxIdentifierLength", 128)
                .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

            modelBuilder.Entity("Tier3ServerDatabase.common.Movie", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<string>("Description")
                        .HasMaxLength(200);

                    b.Property<string>("NameDirector")
                        .HasMaxLength(60);

                    b.Property<string>("NameMainActor")
                        .HasMaxLength(60);

                    b.Property<string>("NameStudio")
                        .HasMaxLength(80);

                    b.Property<decimal>("Price")
                        .HasConversion(new ValueConverter<decimal, decimal>(v => default(decimal), v => default(decimal), new ConverterMappingHints(precision: 38, scale: 17)))
                        .HasColumnType("decimal(18,2)");

                    b.Property<string>("ReleaseDate")
                        .HasMaxLength(20);

                    b.Property<bool>("Rented")
                        .HasColumnType("bit");

                    b.Property<string>("Title")
                        .IsRequired()
                        .HasMaxLength(60);

                    b.Property<string>("YearCreation")
                        .HasMaxLength(20);

                    b.HasKey("Id");

                    b.ToTable("movies");
                });

            modelBuilder.Entity("Tier3ServerDatabase.common.Room", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<string>("Description")
                        .HasMaxLength(40);

                    b.Property<int>("Size");

                    b.HasKey("Id");

                    b.ToTable("rooms");
                });

            modelBuilder.Entity("Tier3ServerDatabase.common.ScheduledMovie", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<string>("Day")
                        .IsRequired()
                        .HasMaxLength(10);

                    b.Property<int?>("MovieId");

                    b.Property<int?>("RoomId");

                    b.Property<string>("Time")
                        .IsRequired()
                        .HasMaxLength(10);

                    b.HasKey("Id");

                    b.HasIndex("MovieId");

                    b.HasIndex("RoomId");

                    b.ToTable("schedule");
                });

            modelBuilder.Entity("Tier3ServerDatabase.common.Seat", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<bool>("Booked");

                    b.Property<int?>("MovieId");

                    b.HasKey("Id");

                    b.HasIndex("MovieId");

                    b.ToTable("seats");
                });

            modelBuilder.Entity("Tier3ServerDatabase.common.ScheduledMovie", b =>
                {
                    b.HasOne("Tier3ServerDatabase.common.Movie", "Movie")
                        .WithMany("Scheduled")
                        .HasForeignKey("MovieId");

                    b.HasOne("Tier3ServerDatabase.common.Room", "Room")
                        .WithMany("Scheduled")
                        .HasForeignKey("RoomId");
                });

            modelBuilder.Entity("Tier3ServerDatabase.common.Seat", b =>
                {
                    b.HasOne("Tier3ServerDatabase.common.ScheduledMovie", "Movie")
                        .WithMany("Seats")
                        .HasForeignKey("MovieId");
                });
#pragma warning restore 612, 618
        }
    }
}
