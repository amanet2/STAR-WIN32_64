#include "gameControl.h"

//getters
map * gameControl::theMap(){return _theMap;}	//the map of islands
ship * gameControl::playerShip(){return _playerShip;}	//the ship
island * gameControl::currentIsland(){return _currentIsland;}	

vector<question*>* gameControl::questions()
{
	return _questions;
} 

void gameControl::initializeQuestions()
{
	_questions->push_back(new question(MATH,"How many degrees farenheit is -3C?","27","30","15","21"));
	_questions->push_back(new question(MATH,"Who was never president?","Alexander Hamilton","Grover Cleveland","Rutherford B Hayes","William Taft"));
	_questions->push_back(new question(MATH,"What is (11011) modulo (00100) in base 2?","00011","10110","00101","01001"));
}
//current island the player's ship is on

//constructor
gameControl::gameControl()
{
	_theMap = NULL;
	_playerShip = NULL;
	_currentIsland = NULL;
	_inLoop = -1;
	_randomNum = -1;
	
	_questions = new std::vector<question*>();

	srand(time(NULL));
}

//initialize the map 
void gameControl::initializeMap()	//call first
{	//generate a BinTree of maps
	//always have a head with two children, a fork at the beginning will always draw the player in
	//besides that, have at least one element in depth 3 islands
	if(_theMap == NULL)
		_theMap = new map();
	else
	{
		//clear map for new config
		theMap()->_head = NULL;
		theMap()->islands()->clear();
		theMap()->_depth1islands->clear();
		theMap()->_depth2islands->clear();
		theMap()->_depth3islands->clear();
	}
	
	_theMap->_head = new island(0,7,1);	//head will be town 1 always
		theMap()->head()->_parentIsland = NULL;
		theMap()->head()->_locked = 0;
		theMap()->islands()->push_back(theMap()->head());

	//add all necessary islands (constraint)
	addNewIsland(1,3,2);	//town2
	addNewIsland(1,11,3);	//town3
	
	//randomize remaining spots: must have at least one depth 2 and one depth 3 island
	//let's have 8 permutations
	srand(time(NULL));
	int perm=rand()%8+1;
	
	//when assigning town numbers, can't use 1,2,3 or any number >11
	if(perm == 1)
	{
		addNewIsland(2,1,5);
		addNewIsland(3,0,7);
		addNewIsland(2,5,9);
		addNewIsland(3,6,10);
	}
	else if(perm == 2)
	{
		addNewIsland(2,5,4);
		addNewIsland(3,4,10);
		addNewIsland(2,13,6);
		addNewIsland(2,9,8);
		addNewIsland(3,10,7);
	}
	else if(perm == 3)
	{
		addNewIsland(2,13,7);
		addNewIsland(3,12,11);
		addNewIsland(2,1,8);
		addNewIsland(3,2,9);
		addNewIsland(2,5,5);
	}
	else if(perm == 4)
	{
		addNewIsland(2,1,6);
		addNewIsland(3,0,9);
		addNewIsland(2,9,4);
		addNewIsland(3,8,7);
	}
	else if(perm == 5)
	{
		addNewIsland(2,5,11);
		addNewIsland(2,1,10);
		addNewIsland(3,4,8);
		addNewIsland(3,0,6);
		addNewIsland(2,13,9);
	}
	else if(perm == 6)
	{
		addNewIsland(2,9,8);
		addNewIsland(2,13,4);
		addNewIsland(3,12,7);
		addNewIsland(2,5,11);
		addNewIsland(3,4,5);
	}
	else if(perm == 7)
	{
		addNewIsland(2,1,9);
		addNewIsland(2,9,10);
		addNewIsland(2,13,11);
		addNewIsland(3,0,4);
		addNewIsland(3,8,5);
		addNewIsland(3,12,6);
	}
	else if(perm == 8)
	{
		addNewIsland(2,1,10);
		addNewIsland(2,5,6);
		addNewIsland(2,9,4);
		addNewIsland(2,13,5);
		addNewIsland(3,0,8);
		addNewIsland(3,12,11);
	}
	
	//place fountain of youth on last island
	srand(time(NULL));
	theMap()->_depth3islands->at(rand()%(theMap()->_depth3islands->size()))->_interactives->push_back(new site(FOUNTAIN_OF_YOUTH,0,0));	

}

void gameControl::addNewIsland(int depth, int position, int number)
{
	if(depth == 0)
	{
		std::cout << "\nAlready have a head island by default, you goober!\n";
	}
	else if(depth == 1)
	{
		if(theMap()->_depth1islands->size() < 2)
		{
			if(position == 3 || position == 11)
			{
					if(!(theMap()->containsIsland(1,position)))
					{
						//add island
						if(position == 3)
						{
							theMap()->head()->_leftNeighbor = new island(1,3,number);
							theMap()->head()->leftNeighbor()->_parentIsland = theMap()->head();
							theMap()->islands()->push_back(theMap()->head()->_leftNeighbor);
							theMap()->_depth1islands->push_back(theMap()->head()->_leftNeighbor);
						}
						else if(position == 11)
						{
							theMap()->head()->_rightNeighbor = new island(1,11,number);
							theMap()->head()->rightNeighbor()->_parentIsland = theMap()->head();
							theMap()->islands()->push_back(theMap()->head()->_rightNeighbor);
							theMap()->_depth1islands->push_back(theMap()->head()->_rightNeighbor);
						}
					}
					else std::cout << "\nThat island already exists\n";
			}
			else
			{
				std::cout<<"\nDepth 1 islands must be position 3 or 11\n";
			}
		}
		else std::cout<<"\nDepth 1 is full already\n";
	}
	else if(depth == 2)
	{
		if(theMap()->_depth2islands->size() < 4)
		{
			if(position == 1 || position == 5 || position == 9 || position == 13)
			{
					if(!(theMap()->containsIsland(depth,position)))
					{
						//add island	
						if(position == 1)
						{
							theMap()->head()->leftNeighbor()->_leftNeighbor = new island(depth,position,number);
							theMap()->head()->leftNeighbor()->leftNeighbor()->_parentIsland = theMap()->head()->leftNeighbor();
							theMap()->islands()->push_back(theMap()->head()->leftNeighbor()->leftNeighbor());
							theMap()->_depth2islands->push_back(theMap()->head()->leftNeighbor()->leftNeighbor());
						}
						else if(position==5)
						{
							theMap()->head()->leftNeighbor()->_rightNeighbor = new island(depth,position,number);
							theMap()->head()->leftNeighbor()->rightNeighbor()->_parentIsland = theMap()->head()->leftNeighbor();
							theMap()->islands()->push_back(theMap()->head()->leftNeighbor()->rightNeighbor());
							theMap()->_depth2islands->push_back(theMap()->head()->leftNeighbor()->rightNeighbor());
						}
						else if(position == 9)
						{
							theMap()->head()->rightNeighbor()->_leftNeighbor = new island(depth,position,number);
							theMap()->head()->rightNeighbor()->leftNeighbor()->_parentIsland = theMap()->head()->rightNeighbor();
							theMap()->islands()->push_back(theMap()->head()->rightNeighbor()->leftNeighbor());
							theMap()->_depth2islands->push_back(theMap()->head()->rightNeighbor()->leftNeighbor());							
						}
						else if(position == 13)
						{
							theMap()->head()->rightNeighbor()->_rightNeighbor = new island(depth,position,number);
							theMap()->head()->rightNeighbor()->rightNeighbor()->_parentIsland = theMap()->head()->rightNeighbor();
							theMap()->islands()->push_back(theMap()->head()->rightNeighbor()->rightNeighbor());	
							theMap()->_depth2islands->push_back(theMap()->head()->rightNeighbor()->rightNeighbor());	
						}
					}
					else std::cout << "\nThat island already exists\n";
			}
			else
			{
				std::cout<<"\nDepth 2 islands must be position 1, 5, 9, or 13\n";
			}
		}
		else std::cout<<"\nDepth 2 is full already\n";
	}
	else if(depth == 3)
	{
		if(theMap()->_depth3islands->size() < 4)
		{
			if(position == 0 || position == 2 || position == 4 || position == 6 || position == 8 || position == 10 || position == 12 || position == 14 )
			{
					if(!(theMap()->containsIsland(depth,position)))
					{
						//add island	
						if(position == 0)
						{
							theMap()->head()->leftNeighbor()->leftNeighbor()->_leftNeighbor = new island(depth,position,number);
							theMap()->head()->leftNeighbor()->leftNeighbor()->leftNeighbor()->_parentIsland = theMap()->head()->leftNeighbor()->leftNeighbor();
							theMap()->islands()->push_back(theMap()->head()->leftNeighbor()->leftNeighbor()->leftNeighbor());
							theMap()->_depth3islands->push_back(theMap()->head()->leftNeighbor()->leftNeighbor()->leftNeighbor());
						}
						else if(position == 4)
						{
							theMap()->head()->leftNeighbor()->rightNeighbor()->_leftNeighbor = new island(depth,position,number);
							theMap()->head()->leftNeighbor()->rightNeighbor()->leftNeighbor()->_parentIsland = theMap()->head()->leftNeighbor()->rightNeighbor();
							theMap()->islands()->push_back(theMap()->head()->leftNeighbor()->rightNeighbor()->leftNeighbor());
							theMap()->_depth3islands->push_back(theMap()->head()->leftNeighbor()->rightNeighbor()->leftNeighbor());
						}
						else if(position == 8)
						{
							theMap()->head()->rightNeighbor()->leftNeighbor()->_leftNeighbor = new island(depth,position,number);
							theMap()->head()->rightNeighbor()->leftNeighbor()->leftNeighbor()->_parentIsland = theMap()->head()->rightNeighbor()->leftNeighbor();
							theMap()->islands()->push_back(theMap()->head()->rightNeighbor()->leftNeighbor()->leftNeighbor());
							theMap()->_depth3islands->push_back(theMap()->head()->rightNeighbor()->leftNeighbor()->leftNeighbor());
						}
						else if(position == 12)
						{
							theMap()->head()->rightNeighbor()->rightNeighbor()->_leftNeighbor = new island(depth,position,number);
							theMap()->head()->rightNeighbor()->rightNeighbor()->leftNeighbor()->_parentIsland = theMap()->head()->rightNeighbor()->rightNeighbor();
							theMap()->islands()->push_back(theMap()->head()->rightNeighbor()->rightNeighbor()->leftNeighbor());
							theMap()->_depth3islands->push_back(theMap()->head()->rightNeighbor()->rightNeighbor()->leftNeighbor());
						}
						else if(position == 2)
						{
							theMap()->head()->leftNeighbor()->leftNeighbor()->_rightNeighbor = new island(depth,position,number);
							theMap()->head()->leftNeighbor()->leftNeighbor()->rightNeighbor()->_parentIsland = theMap()->head()->leftNeighbor()->leftNeighbor();
							theMap()->islands()->push_back(theMap()->head()->leftNeighbor()->leftNeighbor()->rightNeighbor());
							theMap()->_depth3islands->push_back(theMap()->head()->leftNeighbor()->leftNeighbor()->rightNeighbor());
						}
						else if(position == 6)
						{
							theMap()->head()->leftNeighbor()->rightNeighbor()->_rightNeighbor = new island(depth,position,number);
							theMap()->head()->leftNeighbor()->rightNeighbor()->rightNeighbor()->_parentIsland = theMap()->head()->leftNeighbor()->rightNeighbor();
							theMap()->islands()->push_back(theMap()->head()->leftNeighbor()->rightNeighbor()->rightNeighbor());
							theMap()->_depth3islands->push_back(theMap()->head()->leftNeighbor()->rightNeighbor()->rightNeighbor());
						}
						else if(position == 10)
						{
							theMap()->head()->rightNeighbor()->leftNeighbor()->_rightNeighbor = new island(depth,position,number);
							theMap()->head()->rightNeighbor()->leftNeighbor()->rightNeighbor()->_parentIsland = theMap()->head()->rightNeighbor()->leftNeighbor();
							theMap()->islands()->push_back(theMap()->head()->rightNeighbor()->leftNeighbor()->rightNeighbor());
							theMap()->_depth3islands->push_back(theMap()->head()->rightNeighbor()->leftNeighbor()->rightNeighbor());
						}
						/*else if(position == 14)
						{
							theMap()->head()->rightNeighbor()->rightNeighbor()->_rightNeighbor = new island(depth,position,number);
							theMap()->head()->rightNeighbor()->rightNeighbor()->rightNeighbor()->_parentIsland = theMap()->head()->rightNeighbor()->rightNeighbor();
							theMap()->islands()->push_back(theMap()->head()->rightNeighbor()->rightNeighbor()->rightNeighbor());
							theMap()->_depth3islands->push_back(theMap()->head()->rightNeighbor()->rightNeighbor()->rightNeighbor());
						}*/
					}
					else std::cout << "\nThat island already exists\n";
			}
			else
			{
				std::cout<<"\nDepth 3 islands must be position 0, 4, 8, 12\n";
			}
		}
		else std::cout<<"\nDepth 3 is full already\n";
	}
}

//initialize the player's ship
void gameControl::initializeShip()	//call second
{
	_playerShip = new ship(0,0);	//new ship
	_currentIsland = theMap()->head();	//player starts at home
}

//welcome screen is the first screen, and takes you to the home
//screen by default
void gameControl::welcomeScreen()
{
	std::cout << "Welcome to the game, sailor!\n";
	std::cout << "As of right now, you are in\n";
	std::cout << "Command of " << _playerShip->theCrew()->size() << " loyal sailors!\n";
	std::cout << "Your ship has " << _playerShip->health() << " health.\n";
	std::cout << "Try to make it to the fountain of youth!\n";
	std::cout << "GOTT MIT UNS\n";
	std::cout << "-\n-\n-\n-\n-\n-\n";

	homeScreen();	//call homescreen method
}

void gameControl::victoryScreen()
{
      std::cout << "\nO     O  OOO  O   O   O     O  OOO O   O\n";
	std::cout << " O   O  O   O O   O   O  O  O   O  OO  O\n";
	std::cout << "  O O   O   O O   O   O O O O   O  O O O\n";
	std::cout << "   O    O   O O   O   OO   OO   O  O  OO\n";
	std::cout << "   O     OOO   OOO    O     O  OOO O   O\n";

	std::cout << "\nYou have accomplished your goal and found the founatin of youth!  Press any key to continue:\n";
	std::getline(std::cin,_strChoice);
	
	menuControl takeMeHomeController = menuControl();	
		takeMeHomeController.mainMenu();
}

void gameControl::deathScreen()
{
      std::cout << "\nO     O  OOO  O   O   OOOO   OOO  OOOO  OOOO \n";
	std::cout << " O   O  O   O O   O   O   O   O   O     O   O\n";
	std::cout << "  O O   O   O O   O   O   O   O   OOO   O   O\n";
	std::cout << "   O    O   O O   O   O   O   O   O     O   O\n";
	std::cout << "   O     OOO   OOO    OOOO   OOO  OOOO  OOOO\n";

	std::cout << "\nYou ran out of supplies and have died!  Press any key to continue:\n";
	std::getline(std::cin,_strChoice);
	
	menuControl takeMeHomeController = menuControl();	
		takeMeHomeController.mainMenu();
}

//home screen can be called on any island, because it uses the
//"current island" var which must be updated every time player moves
void gameControl::homeScreen()
{
	_inLoop = 1;
	while(_inLoop == 1)
	{
			//check for death
		if(playerShip()->food() <= 0 || playerShip()->health() <= 0) deathScreen();

		std::cout << "SHIP HEALTH: " << playerShip()->health() << '\n';
		std::cout << "SHIP FOOD: " << playerShip()->food() << " kcal" << '\n';
		std::cout << "SHIP GOLD: " <<playerShip()->gold() << " coins" << '\n';
		std::cout << "SAILORS LEFT: " << playerShip()->theCrew()->size() << '\n';
		std::cout << "SITES ON ISLAND: " << currentIsland()->interactives()->size() << '\n';

		std::cout << "NEIGHBORING ISLANDS\n===\n";
		
		if(currentIsland()->parentIsland() != NULL)
		{
			if(currentIsland()->parentIsland()->locked() == 1)
				std::cout << "[LOCKED]\n";
			else std::cout << "Parent\n";
		}
		else std::cout << "[NO PARENT]\n";

		if(currentIsland()->rightNeighbor() != NULL)
		{
			if(currentIsland()->rightNeighbor()->locked() == 1)
				std::cout << "[R LOCKED]\n";
			else std::cout << "Right\n";
		}
		else std::cout << "[NO RIGHT]\n";

		if(currentIsland()->leftNeighbor() != NULL)
		{
			if(currentIsland()->leftNeighbor()->locked() == 1)
				std::cout << "[L LOCKED]\n";
			else std::cout << "Left\n";
		}
		else std::cout << "[NO LEFT]\n";

		std::cout << "\n.\n.\nEnter Command ([q]uit/[r|l]right|left/[b]ack island/[i]nvestigate/[p]ort/[m]ap):\n";
		std::getline(std::cin,_strChoice);

		//quit on q
		if(_strChoice.compare("q") == 0) return;
		else if(_strChoice.compare("l") == 0)
		{
			moveToIsland(currentIsland()->leftNeighbor());
		}
		else if(_strChoice.compare("r") == 0)
		{
			moveToIsland(currentIsland()->rightNeighbor());
		}
		else if(_strChoice.compare("b") == 0)
		{
			moveToIsland(currentIsland()->parentIsland());
		}
		else if(_strChoice.compare("i") == 0)
		{
			if(currentIsland()->interactives() != NULL)
			{
				investigateScreen();
			}
		}
		else if(_strChoice.compare("p") == 0)
		{
			repairScreen();
		}
		else if(_strChoice.compare("m")==0)
		{
			if(theMap() != NULL)
			{
				bigMapScreen();
			}
		}
		else if(_strChoice.compare("anq")==0)
		{
			if(questions() != NULL)
			{
				addNewQuestionScreen();
			}
		}
	}
}

void gameControl::addNewQuestionScreen()
{
	std::cout<<"\nENTER NEW QUESTION(S)\n";
	//print maps
	_inLoop = 1;
	while(_inLoop == 1)
	{
		cout << "\nEnter in form [prompt],[answer],[option1],[o2],[o3] (b to go back):\n";
		
		std::getline(std::cin,_strChoice);

		if(_strChoice.compare("b") == 0) return;

		std::vector<std::string> v;

		std::istringstream buf(_strChoice);

		for(std::string token; getline(buf, token, ','); )
			v.push_back(token);

		
		if(v.size() != 5) std::cout << "\nBad input, try again\n";
		else
		{
			std::cout << "\nAdded new question!\n";
			addNewQuestion(v.at(0),v.at(1),v.at(2),v.at(3),v.at(4));
		}
	}
}

void gameControl::printSites(island * isle)
{
int ind;
			for(ind = 0;ind < isle->interactives()->size();ind++)
			{
				if(isle->interactives()->at(ind)->type() == TREASURE) std:: cout << "\n[" << (ind+1) << "] Treasure Chest\n";
				else if(isle->interactives()->at(ind)->type() == FOUNTAIN_OF_YOUTH){
					std::cout << "\n[" << (ind+1) << "] $$$ The Fountain of Youth! $$$\n";	
				}
				else if(isle->interactives()->at(ind)->type() == TRADER) std::cout << "\n["<<(ind+1)<<"] Trader\n";
				else if(isle->interactives()->at(ind)->type() == MAP)
				{
					std::cout << "\n[" << (ind+1) << "] Map to new area\n";
				}
			}
}

void gameControl::bigMapScreen()
{
	std::cout<<"\nPRINT MAPS\n";
	//print maps
	_inLoop = 1;
	while(_inLoop == 1)
	{
		int ind;
		for(ind = 0; ind < theMap()->islands()->size();ind++)
		{
			std::cout << "\nNUMBER: "<<(theMap()->islands()->at(ind)->number()) << "\n";
			std::cout << "\nDEPTH: "<<(theMap()->islands()->at(ind)->depth()) << "\n";
			std::cout<<  "\nPOSITION: "<< theMap()->islands()->at(ind)->position()<<"\n";
		}
		
		std::cout<<"\nEnter m to go back:\n";
		
		std::getline(std::cin,_strChoice);
		if(_strChoice.compare("m") == 0) return;
	}
}

void gameControl::investigateSite(site * s)
{
	if(s->visited() == 0)
	{
		if(s->type() == MAP)
		{
			if(askQuestionScreen() == 1)
			{
				std::cout << "\nYou read a map to show you the way on your journey!\n";

				unlockIsland();
			}
		}
		else if(s->type() == TREASURE)
		{	
			if(askQuestionScreen() == 1)
			{
				std::cout << "\nYou discover hidden treasure!\n";
				rewardGold(1);
				s->_visited = 1;
			}
		}
		else if(s->type() == TRADER)
		{
			traderScreen();
		}
		else if(s->type() == FOUNTAIN_OF_YOUTH)
		{
			victoryScreen();
		}
	}
	else std::cout << "\nAlready visited that site\n";
}

void gameControl::investigateScreen()
{
	std::cout << "\nThe investigation will cost you 5000 kcals in supplies!\n";
	useFood(5000);
	
	_inLoop = 1;
	while(_inLoop == 1)
	{
		std::cout << "\nInvestigating Island\n"<<currentIsland()->number()<<"\n===\n";

		if(currentIsland()->interactives()->size() == 0)
			std::cout << "no sites to explore\n";
		else{
			printSites(currentIsland());
	
			std::cout << "\nEnter number of site you want to explore (b to go back):\n";

			std::getline(std::cin,_strChoice);
			if(_strChoice.compare("b") == 0) return;
			else if(atoi(_strChoice.c_str()) <= currentIsland()->interactives()->size() && atoi(_strChoice.c_str()) > 0)
			{
				investigateSite(currentIsland()->interactives()->at(atoi(_strChoice.c_str())-1));
			}
			else std::cout << "\nNot the correct option\n";
		}

		
	}
}

void gameControl::unlockIsland()
{
	if(currentIsland()->rightNeighbor() != NULL)
		currentIsland()->rightNeighbor()->_locked = 0;
					
	if(currentIsland()->leftNeighbor() != NULL)
		currentIsland()->leftNeighbor()->_locked = 0;
}

void gameControl::rewardGold(int mod)
{
	_randomNum = rand()%50 + 25;

	_randomNum = _randomNum*(rand()%3*mod+mod);

	playerShip()->_gold += _randomNum;
	std::cout << "\n+" << _randomNum <<" gold\n";
	std::cout << "SHIP GOLD: " <<playerShip()->gold() << " coins" << '\n';
}

void gameControl::enemySpottedScreen()
{
	//enemy ship, -30 health if wrong choice
	_inLoop = 1;
	while(_inLoop == 1)
	{
		std::cout << "\nEnemy ship has been spotted!\n";
		std::cout << "Press 1 to take evasive action first, or 2 to move into attack position first:\n";
		_randomNum = rand()%2+1;
	
		std::getline(std::cin,_strChoice);


		if(_strChoice.compare("2") == 0 || _strChoice.compare("1") == 0)
		{
			if(_randomNum == atoi(_strChoice.c_str()))
			{
				std::cout << "\nYou make a successful battle with the enemy ship!\n";
				damageShip(10);
				rewardGold(3);
				addFood(5000);
			}
			else
			{
				std::cout << "\nYou limp away from a lost battle!\n";
				damageShip(30);
			}
			return;
		}
		else std::cout << "\nPlease enter 1 or 2\n";
	}
}

void gameControl::stormScreen()
{
	_inLoop = 1;
	while(_inLoop == 1)
	{
		std::cout << "\nA storm is closing in on your vessel\n";
		std::cout << "Press 1 to tie down sails first or 2 to seal all hatches first:\n";

		_randomNum = rand()%2+1;

		std::getline(std::cin,_strChoice);

		
		if(_strChoice.compare("1") == 0 || _strChoice.compare("2") == 0)
		{
			if(atoi(_strChoice.c_str()) == _randomNum)
			{
				std::cout << "\nYou barely weather the storm\n";
				useFood(5000);
				damageShip(10);
			}
			else
			{
				std::cout << "\nYour priorities were misplaced, and your ship suffers damage as a result!\n";
				damageShip(30);
			}
			return;
		}
		else std::cout << "\nPlease pick 1 or 2\n";
	}
}

//move to new island if available
void gameControl::moveToIsland(island * moveTo)
{
	if(moveTo != NULL)
	{
		if(moveTo->locked() == 1) return;

		std::cout << "\nThe voyage will cost you 5000 kcals in supplies!\n";
		useFood(5000);

		_randomNum = rand()%4+1;	//random number of 1 to 4
		
		if(_randomNum == 1)
		{
			enemySpottedScreen();	
		}
		else if(_randomNum == 2)
		{
			//storm, -20 health if wrong choice
			stormScreen();
		}

		_currentIsland = moveTo;
	}
	else std::cout << "\nnothing there captain!\n";
}

void gameControl::repairScreen()
{
	_inLoop = 1;
	while(_inLoop == 1)
	{
		std::cout << "\nRestore ship health for 500 gold? [y/n]:\n";
		std::getline(std::cin,_strChoice);

		if(_strChoice.compare("y") == 0)
		{
			repairShip(500);
			return;
		}
		else if(_strChoice.compare("n")==0)
		{
			return;
		}
		else std::cout << "\nNot valid!\n";
		
	}
}

void gameControl::traderScreen()
{
	_inLoop = 1;
	while(_inLoop == 1)
	{
		std::cout << "\nBuy Supplies? [y/n]:\n";
		std::getline(std::cin,_strChoice);

		if(_strChoice.compare("y") == 0)
		{
			while(_inLoop == 1){
				std::cout << "\nBuy ration pack? (+100 kcal/-100 gold):\n";
				std::getline(std::cin,_strChoice);
				if(_strChoice.compare("y") == 0)
				{
					buySupplies(100);
				}
				else if(_strChoice.compare("n")==0)
				{
					break;
				}
				else std::cout << "\nNot valid!\n";
			}
			
		}
		else if(_strChoice.compare("n")==0)
		{
			return;
		}
		else std::cout << "\nNot valid!\n";
		
	}
}

question * gameControl::selectRandomQuestion()
{
	int numQuestions = questions()->size();
	
	//cout << numQuestions << " quesitons in file\n";
		srand(time(NULL));
	int qToChoose = rand()%numQuestions+1;

	return questions()->at(qToChoose-1);
}

int gameControl::askQuestionScreen()
{
	//question * q = new question();
	_inLoop = 1;

		question * qq = selectRandomQuestion();

		//question * qq = new question(MATH,"What is the best country in the world?","America","China","Russia","Germany");

		string randomPlaces[] = {"nil","nil","nil","nil"};
		
		srand(time(NULL));

		int rando = rand()%4+1;
			randomPlaces[rando-1] = qq->_questionAnswer;


		//cout << "\n" << rando << "\n";

		if(rando == 1){
				randomPlaces[1]=qq->_option2;
				randomPlaces[2]=qq->_option1;
				randomPlaces[3]=qq->_option3;
		}
		else if(rando == 2){
				randomPlaces[0]=qq->_option1;
				randomPlaces[2]=qq->_option2;
				randomPlaces[3]=qq->_option3;
		}
		else if(rando == 3){
				randomPlaces[0]=qq->_option3;
				randomPlaces[1]=qq->_option2;
				randomPlaces[3]=qq->_option1;
		}
		else if(rando == 4){
				randomPlaces[0]=qq->_option1;
				randomPlaces[1]=qq->_option3;
				randomPlaces[2]=qq->_option2;
		}
			
		

		//scramble order of presentation
	while(_inLoop == 1)
	{
		std::cout << "QUESTION: " << qq->questionText() <<"\n";
		std::cout << "OPTIONS\n============================\n";
	
		std::cout << "1: " << randomPlaces[0] <<"\n";
		std::cout << "2: " << randomPlaces[1] <<"\n";
		std::cout << "3: " << randomPlaces[2] <<"\n";
		std::cout << "4: " << randomPlaces[3] <<"\n";

		std::cout << "\nEnter the # of your answer (b to go back):\n";

		std::getline(std::cin,_strChoice);

		if(_strChoice.compare("b") == 0) return 0;
		
		if(_strChoice.compare("d") == 0){}
		else if(atoi(_strChoice.c_str()) > 0 && atoi(_strChoice.c_str()) <= 4)
		{
			if(qq->answerQuestion(randomPlaces[atoi(_strChoice.c_str())-1]) == 1)
			{
				std::cout <<"\nright answer\n";
				return 1;
				_inLoop = 0;
			}
			else{ std::cout << "\nWrong answer\n"; return 0;}
		}
		else{ std::cout << "\nWrong answer\n"; return 0;}
	}

	//delete(qq);

	return 0;
}

void gameControl::addNewQuestion(string prompt,string ans,string op1,string op2, string op3){
	questions()->push_back(new question(MATH,prompt,ans,op1,op2,op3));
}

void gameControl::useGold(int mod)
{
	if(playerShip()->gold() >= mod)
	{
		playerShip()->_gold -= mod;
		std::cout << "\n-" << mod <<" gold\n";
		std::cout << "SHIP GOLD: " <<playerShip()->gold() << " coins" << '\n';
	}
}

void gameControl::buySupplies(int costOfGold)
{
	if(playerShip()->gold() < costOfGold)
	{
		std::cout <<"\nYou too broke!\n";
		return;
	}
	else
	{
		useGold(costOfGold);
		addFood(100*costOfGold);
	}
}

void gameControl::damageShip(int mod)
{
	playerShip()->_health -= mod;
	std::cout << "-"<<mod<<"hp\n";
		std::cout << "SHIP HEALTH: " << playerShip()->health() << '\n';
}

void gameControl::repairShip(int costOfGold)
{
	if(playerShip()->gold() < costOfGold)
	{
		std::cout <<"\nYou too broke!\n";
		return;
	}
	else
	{
		playerShip()->_health = 100;
		useGold(costOfGold);
		std::cout << "SHIP HEALTH: " << playerShip()->health() << '\n';
	}
}

void gameControl::addFood(int mod)
{
	playerShip()->_food += mod;
	std::cout<<"+"<<mod<<"kCals of food\n";
		std::cout << "SHIP FOOD: " << playerShip()->food() << " kcal" << '\n';
	
}

void gameControl::useFood(int mod)
{
	playerShip()->_food -= mod;
	std::cout <<"-"<<mod<<"kCals of food\n";

		std::cout << "SHIP FOOD: " << playerShip()->food() << " kcal" << '\n';
}
