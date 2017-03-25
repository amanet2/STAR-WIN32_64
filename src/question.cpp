#include "question.h"

//test string with question's defined answer return 0 for incorrect 1 for correct

int question::answerQuestion(string s){
	if(s.compare(questionAnswer()) == 0)
		return 1;
	else return 0;
}

/*GETTER*/
questionType question::type(){
	return _type;
}

string question::questionText(){
	return _questionText;
}

string question::questionAnswer(){
	return _questionAnswer;
}

/*CONSTRUCTORS*/
question::question(){
	_type = MATH;
	_questionText = "2+3";
	_questionAnswer = "5";
}

question::question(questionType qT,string q, string ans, string op1, string op2, string op3){
	_type = qT;
	_questionText = q;
	_questionAnswer = ans;

	_option1 = op1;
	_option2 = op2;
	_option3 = op3;	
}

question::~question(){
	_questionText.clear();
	_questionAnswer.clear();
}
