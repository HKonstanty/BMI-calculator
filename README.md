# BMI-calculator
Simple application on Android to count BMI with Kotlin.

## Table of Contents
* [General Info](#general-information)
* [Technologies Used](#technologies-used)
* [Features](#features)
* [Screenshots](#screenshots)
* [Setup](#setup)
* [Project Status](#project-status)
* [Room for Improvement](#room-for-improvement)
* [Contact](#contact)


## General Information
The application was created in order to calculate BMI based on user's height and weight. It allows to introduce data in european and british units. 
After calculations, tha app generates an information about the result and gives lifestyle suggestions. The app provides results history with a use of database. 


## Technologies Used
- Kotlin data class
- SQLiteOpenHelper to store BMI history in memory
- Kotlin version 1.4.10
- RecyclerView-v7 version 28.0.0
- Sdk version 31


## Features
List the ready features here:
- Calculating BMI based on weight and height in european and british units
- Suggested actions based on BMI
- Running BMI history


## Screenshots
Application screen overview

### Data entry form
<p align="left">
	<img src="./screenshot/Screenshot_calculator.png" width="250">
</p>

### Suggested actions screen
<p align="left">
	<img src="./screenshot/Screenshot_details.png" width="250">
</p>

### BMI history
<p align="left">
	<img src="./screenshot/Screenshot_history.png" width="250">
</p>


## Setup
1. Download the samples by cloning this repository
2. In the welcome screen of Android Studio, select "Open an Existing project"
3. Select one of the sample directories from this repository

Alternatively, use the `gradlew build` command to build the project directly


## Project Status
Project is: _complete_


## Room for Improvement
Room for improvement:
- application icon

To do:
- Suggested actions based on BMI history


## Contact
Created by [@HKonstanty](https://github.com/HKonstanty/HKonstanty) - feel free to contact me!