package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {



    // The current word
     val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get()= _word


    private val timer: CountDownTimer

    // The current score
    val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    val _gameFinished = MutableLiveData<Boolean>()
    val gameFinished:LiveData<Boolean>
    get() = _gameFinished


    val _currentTime = MutableLiveData<Long>()
    val currentTime:LiveData<Long>
        get() = _currentTime


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

        timer = object: CountDownTimer(COUNT_DOWN_TIME, ONE_SECOND){
            override fun onFinish() {

                _currentTime.value = DONE
                _gameFinished.value = true
            }

            override fun onTick(millisUntilFinished: Long) {

            _currentTime.value = millisUntilFinished / ONE_SECOND
            }

        }
        timer.start()

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
          resetList()

        }
        _word.value = wordList.removeAt(0)

    }

    override fun onCleared() {
        super.onCleared()

        timer.cancel()

    Log.i("GameViewModel","Cleared")
    }

    fun onGameFinishCompleted(){
        _gameFinished.value = false
    }

    companion object {

        //this is the when the game over
        const val DONE = 0L

        //time intervall
        const val ONE_SECOND = 1000L

        //totla time of the game
        const val COUNT_DOWN_TIME = 10000L

    }

}


