package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {



    // The current word
     val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get()= _word


    // The current score
    val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    val _gameFinished = MutableLiveData<Boolean>()

    val gameFinished:LiveData<Boolean>
    get() = _gameFinished



    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>


     fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }
    init {
        Log.i("GameViewModel","ViewModel class initialised")


        resetList()
        nextWord()
        _score.value = 0
        _gameFinished.value = false
    }

    /** Methods for buttons presses **/

     fun onSkip() {
        _score.value = score.value?.minus(1)
        nextWord()
    }

     fun onCorrect() {
        _score.value = score.value?.plus(1)
        nextWord()
    }

    /**
     * Moves to the next word in the list
     */
     fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            _gameFinished.value = true
        } else {
            _word.value = wordList.removeAt(0)
        }

    }

    override fun onCleared() {
        super.onCleared()

    Log.i("GameViewModel","Cleared")
    }

    fun onGameFinishCompleted(){
        _gameFinished.value = false
    }


}

