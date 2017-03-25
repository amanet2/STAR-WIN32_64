#include "menuControl.h"

menuControl::menuControl(){_inMenu = 1; _choice = 0;}

void menuControl::mainMenu()	//main menu of game
{
	while(_inMenu == 1)
	{
		std::cout << "MAIN MENU" << "\n===\n";
		std::cout << "1. New Game" << '\n';
		std::cout << "2. EXIT" << '\n';
	
		std::getline(std::cin,_strChoice);
	
		_choice = atoi(_strChoice.c_str());

		if(_choice == 1)
		{
			newGameMenu();
		}
		else if(_choice == 2)
		{
			cout << "\nTHANK YOU FOR PLAYING!\n";
			exit(0);
		}
		else std::cout << "Enter a number and press return/enter...\n";
	}
}

void menuControl::newGameMenu()
{	//new game menu
	while(_inMenu == 1)
	{
		std::cout << "New GAME" << "\n===\n";
		std::cout << "1. Begin New Game" << '\n';
		std::cout << "2. EXIT TO MAIN MENU" << '\n';
	
		std::getline(std::cin,_strChoice);
	
		_choice = atoi(_strChoice.c_str());

		if(_choice == 1)
		{
			//initialize new game and get this shit going
			gameControl * gameController = new gameControl();
							//create the map/tree of islands
				gameController->initializeMap();
				//create the ship/crew		
				gameController->initializeShip();
				gameController->initializeQuestions();
				
				//actually start the game by 
				//displaying welcome screen
				gameController->welcomeScreen();

				
/*
	Game is "played" via the game controller bringing up a variety of screens, displaying various info about generated objects or giving the player to change object parameters.  
we must be sure that a screen does not return us to a place we wold not want to go				
*/	
		}
		else if(_choice == 2) mainMenu();
		else std::cout << "Enter a number and press return/enter...\n";
	}
}
