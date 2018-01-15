#include "Game.h"
#include "GraphicInterface.h"
#include "ConsoleGraphics.h"
#include "GameLogic.h"
#include "Player.h"
#include "HumanPlayer.h"
#include "StandardLogic.h"
#include <iostream>
#include <string>
#include <fstream>
#include <sstream>

using namespace std;

Game::Game(GraphicInterface *gi) : graphicProvider(gi) {};

void Game::start() {
	bool validInput = false;
	bool gameLoop = true;
	this->graphicProvider->displayMessage("Welcome to Reversi!\n");
	while(gameLoop) {
		//Prints the greetings for the menu
		this->graphicProvider->displayMessage("(1)Play against a friend\n");
		this->graphicProvider->displayMessage("(2)Quit\n");
		int option;
		cin >> option;
		switch(option) {
			case 1: { //Regular Game
				StandardLogic sl(graphicProvider);
				GameLogic *gl = &sl;
				HumanPlayer p1(gl, graphicProvider, 'X');
				Player *hp1 = &p1;
				HumanPlayer p2(gl, graphicProvider,'O');
				Player *hp2 = &p2;
				this->playOneMatch(gl,hp1, hp2);
			}break;
			case 2:{ //Breaks the game loop
				gameLoop = false;
			}break;
			default:{
				this->graphicProvider->displayMessage("Invalid option\n");
			}break;
		}
	}
}


void Game::playOneMatch(GameLogic* gl, Player* p1, Player* p2) {
	gl->startGame(p1, p2);
	while(!gl->gameEnded()) {
		gl->playNextTurn();
	}
	gl->endGame();
}

void Game::setGraphicProvider(const GraphicInterface *graphicProvider) {
	this->graphicProvider = graphicProvider;
}






